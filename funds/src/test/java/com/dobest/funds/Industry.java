package com.dobest.funds;

import com.alibaba.fastjson.JSON;
import com.dobest.funds.entity.FundsInfoEntity;
import com.dobest.funds.entity.IndustryEntity;
import com.dobest.funds.service.FundsService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Industry {

    private final static String DATA_DATE = "2021-06-30";

    @Autowired
    private FundsService fundsService;

    @Test
    void contextLoads() {
        List<FundsInfoEntity> fundsInfos = fundsService.queryFundsIds();

        fundsInfos.forEach(fundsInfo->{
            String fundsId = fundsInfo.getFundsId();
            HttpResponse<JsonNode> response = Unirest.get("https://fundmobapi.eastmoney.com/FundMNewApi/FundMNSectorAllocation" +
                    "?product=EFund&appVersion=6.4.6&serverVersion=6.4.6&" +
                    "FCODE="+ fundsId +"&deviceid=d58d5379a7a4096e073a0ff2342dc22f%7C%7Ciemi_tluafed_me&version=6.4.6" +
                    "&userId=f29c98a101ee4ee598b0d9ddb880c4f2" +
                    "&DATE="+DATA_DATE+"&cToken=-cuf8j1qc1ej1ckjkuaqnd6uq1jcdu--.12&MobileKey=d58d5379a7a4096e073a0ff2342dc22f%7C%7Ciemi_tluafed_me&OSVersion=10&plat=Android&uToken=8hkajr6j1uckuf8-6aejh1kj1jnkh-q8fc951d4c.12&passportid=1073635213473476")
                    .asJson();
            JSONObject body = response.getBody().getObject();
            JSONArray datas = body.getJSONArray("Datas");



            List<IndustryEntity> industryList = new ArrayList<>();
            datas.forEach(data->{
                IndustryEntity industryEntity = new IndustryEntity();
                String jsonStr = data.toString();
                jsonStr = jsonStr.replace("--","0");
                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(jsonStr);
                double price = jsonObject.getDoubleValue("SZ");
                industryEntity.setFundsId(fundsId);
                industryEntity.setName(jsonObject.getString("HYMC"));
                industryEntity.setPrice(price);
                industryEntity.setPricePercent(jsonObject.getDoubleValue("ZJZBL"));
                industryEntity.setDataDate(jsonObject.getString("FSRQ"));
                if (price != 0){
                    industryList.add(industryEntity);
                }
            });
            if (industryList != null && industryList.size() > 0){
                fundsService.insertIndustry(industryList);
            }
        });
    }
}
