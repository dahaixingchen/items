package com.alibaba.dubbo.demo.impl

import redis.clients.jedis.util.Pool
import redis.clients.jedis.{Jedis, JedisPool}

object RedisUtil {
  private[this] var jedisPool: Pool[Jedis] = _

  def init(host: String, port: Int): Unit = {
    jedisPool = new JedisPool(host, port)
  }

  def updateHot(dbIndex:Int,key: String, itemID:String): Boolean = {
    try {
      val jedis = jedisPool.getResource
      jedis.select(dbIndex)
      jedis.zincrby(key,1,itemID)
      jedis.close()
      true
    } catch {
      case e: Exception => {
        false
      }
    }
  }

  def insertValue(dbIndex:Int,key: String, field:String, value:String): Boolean = {
    try {
      val jedis = jedisPool.getResource
      jedis.select(dbIndex)
      jedis.hset(key,field,value)
      jedis.close()
      true
    } catch {
      case e: Exception => {
        false
      }
    }
  }

  def insertRecommend(dbIndex:Int,key: String, value:String): Boolean = {
    try {
      val jedis = jedisPool.getResource
      jedis.select(dbIndex)
      jedis.sadd(key,value)
      jedis.close()
      true
    } catch {
      case e: Exception => {
        false
      }
    }
  }



  def getRecordCount(dbIndex:Int,key: String): Int = {
    try {
      val jedis = jedisPool.getResource
      jedis.select(dbIndex)
      val count = jedis.hlen(key)
      jedis.close()
      count.toInt
    } catch {
      case e: Exception => {
        e.printStackTrace()
        -1
      }
    }
  }


  def getRecord(dbIndex:Int,key: String,field:String): String = {
      val jedis = jedisPool.getResource
      jedis.select(dbIndex)
      val value = jedis.hget(key,field)
      jedis.close()
      value
  }

  //来redis中查询这个用户的推荐列表   set
  def getMembers(dbIndex:Int,key: String)={
    val jedis = jedisPool.getResource
    jedis.select(dbIndex)
    val set = jedis.smembers(key)
    jedis.close()
    set
  }
}
