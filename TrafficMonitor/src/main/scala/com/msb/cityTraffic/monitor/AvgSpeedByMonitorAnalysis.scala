package com.msb.cityTraffic.monitor

import java.util.Properties

import com.msb.cityTraffic.utils.{AvgSpeedInfo, GlobalConstants, JdbcReadDataSource, MonitorInfo, TrafficInfo, WriteDataSink}
import org.apache.flink.api.common.functions.AggregateFunction
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.TimeCharacteristic
import org.apache.flink.streaming.api.datastream.BroadcastStream
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.streaming.api.scala.function.WindowFunction
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer
import org.apache.flink.util.Collector

/**
 * @todo: 卡口拥堵情况监控
 * @date: 2021/3/7 11:31
 */
object AvgSpeedByMonitorAnalysis {

  def main(args: Array[String]): Unit = {
    val streamEnv: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment
    import org.apache.flink.streaming.api.scala._

    streamEnv.setStreamTimeCharacteristic(TimeCharacteristic.EventTime)
    streamEnv.setParallelism(1)

    val props = new Properties()
    props.setProperty("bootstrap.servers","hadoop101:9092,hadoop102:9092,hadoop103:9092")
    props.setProperty("group.id","msb_001")

    //创建一个Kafka的Source
//    val stream: DataStream[TrafficInfo] = streamEnv.addSource(
//      new FlinkKafkaConsumer[String]("t_traffic_msb", new SimpleStringSchema(), props).setStartFromEarliest() //从第一行开始读取数据
//    )
    val stream: DataStream[TrafficInfo] = streamEnv.socketTextStream("node01",8888)
      .map(line => {
        var arr = line.split(",")
        new TrafficInfo(arr(0).toLong, arr(1), arr(2), arr(3), arr(4).toDouble, arr(5), arr(6))
      }) //引入Watermark，并且延迟时间为5秒
      .assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor[TrafficInfo](Time.seconds(5)) {
        override def extractTimestamp(element: TrafficInfo) = element.actionTime
      })

    stream.keyBy(_.monitorId)
      .timeWindow(Time.minutes(5),Time.minutes(2))
      .aggregate(  //设计一个累加器：二元组(车的平均速度,车辆的数量)
        new AggregateFunction[TrafficInfo,(Double,Long),(Double,Long)] {
          override def createAccumulator() = (0,0)
          override def add(value: TrafficInfo, acc: (Double, Long)) ={ (acc._1+value.speed,acc._2+1) }
          override def getResult(acc: (Double, Long)) = acc
          override def merge(a: (Double, Long), b: (Double, Long)) = {(a._1+b._1,a._2+b._2)}
        },
        (k:String, w:TimeWindow,input: Iterable[(Double,Long)],out: Collector[AvgSpeedInfo]) =>{
          //其实这个input迭代器就一条聚合后的数据
          val list: List[(Double, Long)] = input.iterator.toList
          list.foreach(println)
          val acc: (Double, Long) = input.last //最后一条数据
          var avg :Double  =(acc._1/acc._2).formatted("%.2f").toDouble
          out.collect(new AvgSpeedInfo(w.getStart,w.getEnd,k,avg,acc._2.toInt))
        }
      )
      .print()
//      .addSink(new WriteDataSink[AvgSpeedInfo](classOf[AvgSpeedInfo]))

    streamEnv.execute()

  }
}
