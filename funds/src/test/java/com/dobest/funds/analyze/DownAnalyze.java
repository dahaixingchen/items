package com.dobest.funds.analyze;

import com.dobest.funds.entity.FundsHistoryEntity;
import com.dobest.funds.entity.FundsInfoEntity;
import com.dobest.funds.entity.analyze.DownDataEntity;
import com.dobest.funds.service.AnalyzeService;
import com.dobest.funds.service.FundsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description:
 * @ClassName: MyFunds
 * @Author chengfei
 * @DateTime 2021/8/11 15:35
 **/
@SpringBootTest
public class DownAnalyze {
    @Autowired
    private AnalyzeService analyzeService;

    @Test
    public void down3DaysAnalyze() {
        List<FundsHistoryEntity> fundsInfos = analyzeService.queryFundsIds20210801();
        //按照funds_id分组，分完组后按照data_date排序，
        fundsInfos.stream().collect(Collectors.groupingBy(FundsHistoryEntity::getFundsId))
                .forEach((key, values) -> {
                    List<FundsHistoryEntity> sortedData = values.stream().sorted(Comparator.comparing(FundsHistoryEntity::getDataDate).reversed())
                            .filter(d->d.getPriceUpDown() != 0).collect(Collectors.toList());
                    String name = sortedData.get(0).getName();
                    double  upDownPercent = 0;
                    int  upDownDays = 0;
                    String  dataDate = "";
                    for (int i = 0; i < sortedData.size(); i++) {
                        if (sortedData.get(i).getPriceUpDown() > 0) {
                            if (i+1 == sortedData.size()){
                                upDownPercent += sortedData.get(i).getPriceDailyPercent();
                                upDownDays += sortedData.get(i).getPriceUpDown();
                                dataDate = dataDate+","+sortedData.get(i).getDataDate();
                                DownDataEntity result = new DownDataEntity(key, name, upDownPercent, upDownDays, dataDate);
                                System.out.println(result);
                            }else {
                                if (sortedData.get(i+1).getPriceUpDown() > 0){
                                    upDownPercent += sortedData.get(i).getPriceDailyPercent();
                                    upDownDays += sortedData.get(i).getPriceUpDown();
                                    dataDate = dataDate+","+sortedData.get(i).getDataDate();
                                }else if (sortedData.get(i+1).getPriceUpDown() < 0){
                                    upDownPercent += sortedData.get(i).getPriceDailyPercent();
                                    upDownDays += sortedData.get(i).getPriceUpDown();
                                    dataDate = dataDate+","+sortedData.get(i).getDataDate();
                                    DownDataEntity result = new DownDataEntity(key, name, upDownPercent, upDownDays, dataDate);
                                    System.out.println(result);
                                    upDownPercent = 0;
                                    upDownDays = 0;
                                    dataDate = "";
                                }
                            }
                        }else if(sortedData.get(i).getPriceUpDown() < 0) {
                            if (i+1 == sortedData.size()) {
                                upDownPercent += sortedData.get(i).getPriceDailyPercent();
                                upDownDays += sortedData.get(i).getPriceUpDown();
                                dataDate = dataDate + "," + sortedData.get(i).getDataDate();
                                DownDataEntity result = new DownDataEntity(key, name, upDownPercent, upDownDays, dataDate);
                                System.out.println(result);
                            }else{
                                if (sortedData.get(i+1).getPriceUpDown() < 0){
                                    upDownPercent += sortedData.get(i).getPriceDailyPercent();
                                    upDownDays += sortedData.get(i).getPriceUpDown();
                                    dataDate = dataDate+","+sortedData.get(i).getDataDate();
                                }else if (sortedData.get(i+1).getPriceUpDown() > 0){
                                    upDownPercent += sortedData.get(i).getPriceDailyPercent();
                                    upDownDays += sortedData.get(i).getPriceUpDown();
                                    dataDate = dataDate+","+sortedData.get(i).getDataDate();
                                    DownDataEntity result = new DownDataEntity(key, name, upDownPercent, upDownDays, dataDate);
                                    System.out.println(result);
                                    upDownPercent = 0;
                                    upDownDays = 0;
                                    dataDate = "";
                                }
                            }
                        }
                    }
                });
    }
}
