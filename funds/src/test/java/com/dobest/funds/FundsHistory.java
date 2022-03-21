package com.dobest.funds;

import com.alibaba.fastjson.JSON;
import com.dobest.funds.entity.FundsHistoryEntity;
import com.dobest.funds.entity.FundsInfoEntity;
import com.dobest.funds.service.FundsService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @ClassName: FundsHistory
 * @Author chengfei
 * @DateTime 2021/8/28 0:15
 **/
@SpringBootTest
public class FundsHistory {
    @Autowired
    private FundsService fundsService;

    @Test
    public void fundsHistory() {
        List<FundsInfoEntity> fundsInfos = fundsService.queryFundsIds();
        for (FundsInfoEntity fundsInfo : fundsInfos) {
            int length  = -1;
            for (int i = 0;  length !=0; i++) {
                String fundsId = fundsInfo.getFundsId();
                HttpResponse<JsonNode> response = Unirest.post("https://fundmobapi.eastmoney.com/FundMNewApi/FundMNHisNetList")
                        .header("Content-Type", "application/x-www-form-urlencoded")
                        .body("product=EFund&appVersion=6.4.7&serverVersion=6.4.7&FCODE="+ fundsId +
                                "&pageSize=1000&deviceid=d58d5379a7a4096e073a0ff2342dc22f%7C%7Ciemi_tluafed_me&version=6.4.7&userId=f29c98a101ee4ee598b0d9ddb880c4f2&cToken=ek61eqqfjkua-uukqq1ef1euhne6aefh.12&MobileKey=d58d5379a7a4096e073a0ff2342dc22f%7C%7Ciemi_tluafed_me" +
                                "&pageIndex="+i+"&appType=ttjj&plat=Android&IsShareNet=true&uToken=-uakeuj6u1fr1dfh-h1rjja-1j1dh-jn0a06a6e0.12&passportid=1073635213473476")
                        .asJson();
                org.json.JSONObject body = response.getBody().getObject();
                JSONArray datas = body.getJSONArray("Datas");
                length = datas.length();
                ArrayList<FundsHistoryEntity> historyEntities = new ArrayList<>();
                datas.forEach(data->{
                    FundsHistoryEntity historyEntity = new FundsHistoryEntity();
                    String jsonStr = data.toString();
                    jsonStr = jsonStr.replace("--","0");
                    com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(jsonStr);
                    historyEntity.setFundsId(fundsId);
                    historyEntity.setName(fundsInfo.getName());
                    historyEntity.setPrice(jsonObject.getDoubleValue("DWJZ"));
                    historyEntity.setPriceAll(jsonObject.getDoubleValue("LJJZ"));
                    double dailyPercent = jsonObject.getDoubleValue("JZZZL");
                    historyEntity.setPriceDailyPercent(dailyPercent);
                    if (dailyPercent>0){
                        historyEntity.setPriceUpDown(1);
                    }else if (dailyPercent<0){
                        historyEntity.setPriceUpDown(-1);
                    }else{
                        historyEntity.setPriceUpDown(0);
                    }
                    historyEntity.setNAVTYPE(jsonObject.getString("NAVTYPE"));
                    historyEntity.setRATE(jsonObject.getString("RATE"));
                    historyEntity.setMUI(jsonObject.getString("MUI"));
                    historyEntity.setSYI(jsonObject.getString("SYI"));
                    historyEntity.setDataDate(jsonObject.getString("FSRQ"));

                    historyEntities.add(historyEntity);
                });
                if (length!=0) {
                    fundsService.insertFundsHistory(historyEntities);
                }
            }
        }

    }

}
