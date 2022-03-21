package com.dobest.funds.schedule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dobest.funds.entity.IndustryLayoutEntity;
import com.dobest.funds.service.FundsService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @Description:
 * @ClassName: IndustryLayout
 * @Author chengfei
 * @DateTime 2021/8/28 13:50
 **/
@Component
@Slf4j
public class IndustryLayoutSchedule {

    @Autowired
    private FundsService fundsService;

    @Scheduled(cron = "0 20 15 * * ?")
    public void industryLayout(){
        HttpResponse<JsonNode> response = Unirest.get("https://h5.1234567.com.cn/AggregationStaticService/getFundThemeListAggr?product=EFund" +
                "&appVersion=6.4.7&ServerVersion=6.4.7&PassportID=1073635213473476&RankItems=ZDF&version=6.4.7" +
                "&deviceid=d58d5379a7a4096e073a0ff2342dc22f%7C%7Ciemi_tluafed_me&uid=1073635213473476&RankVectors=desc" +
                "&UserId=f29c98a101ee4ee598b0d9ddb880c4f2&plat=Android&UToken=-uakeuj6u1fr1dfh-h1rjja-1j1dh-jn0a06a6e0.12&category=2" +
                "&CToken=ek61eqqfjkua-uukqq1ef1euhne6aefh.12")
                .asJson();
        org.json.JSONObject body = response.getBody().getObject();
        JSONArray datas = body.getJSONArray("data");
        datas.forEach(data->{
            IndustryLayoutEntity layoutEntity = new IndustryLayoutEntity();
            JSONObject jsonObject = JSON.parseObject(data.toString());
            layoutEntity.setIndustryCode(jsonObject.getString("BKCode"));
            layoutEntity.setIndustryName(jsonObject.getString("BKName"));
            layoutEntity.setPriceDailyPercent(jsonObject.getDoubleValue("ZDF"));
            layoutEntity.setPriceWeeklyPercent(jsonObject.getDoubleValue("SYL_W"));
            layoutEntity.setPriceMonthlyPercent(jsonObject.getDoubleValue("SYL_M"));
            layoutEntity.setPrice3monthPercent(jsonObject.getDoubleValue("SYL_Q"));
            layoutEntity.setPriceYearPercent(jsonObject.getDoubleValue("SYL_1N"));
            layoutEntity.setPriceCurrentYearPercent(jsonObject.getDoubleValue("SYL_JN"));
            layoutEntity.setIndustryPrice(jsonObject.getDoubleValue("NewPrice"));
            layoutEntity.setIndustry3minSpeed(jsonObject.getDoubleValue("ZS_3"));
            layoutEntity.setIndustry5minSpeed(jsonObject.getDoubleValue("ZS_5"));
            layoutEntity.setIndustryFundWeekly(jsonObject.getDoubleValue("Cinflow_W"));
            layoutEntity.setIndustryFundDaily(jsonObject.getDoubleValue("Cinflow"));
            layoutEntity.setTYPE(jsonObject.getString("TYPE"));
            layoutEntity.setLZTS(jsonObject.getString("LZTS"));
            layoutEntity.setLDTS(jsonObject.getString("LDTS"));
            layoutEntity.setJLCTS(jsonObject.getString("JLCTS"));
            layoutEntity.setIndexCode(jsonObject.getString("IndexCode"));
            layoutEntity.setJLRTS(jsonObject.getString("JLRTS"));
            JSONObject relativeFund = jsonObject.getJSONObject("RelativeFund");
            if (relativeFund != null &&!relativeFund.isEmpty()){
                layoutEntity.setFundsPriceWeeklyPercent(relativeFund.getDoubleValue("SYL_Z"));
                layoutEntity.setFundsPriceMonthlyPercent(relativeFund.getDoubleValue("SYL_Y"));
                layoutEntity.setFundsPrice3monthPercent(relativeFund.getDoubleValue("SYL_3Y"));
                layoutEntity.setFundsPriceYearPercent(relativeFund.getDoubleValue("SYL_1N"));
                layoutEntity.setFundsPriceCurrentYearPercent(relativeFund.getDoubleValue("SYL_JN"));
                layoutEntity.setFundsId(relativeFund.getString("FCode"));
                layoutEntity.setFundsName(relativeFund.getString("ShortName"));
                layoutEntity.setSimilarity(relativeFund.getDoubleValue("SON_1N"));

                layoutEntity.setDataDate(LocalDate.now().toString());
            }

            fundsService.insertIndustryLayout(layoutEntity);
        });

    }
}
