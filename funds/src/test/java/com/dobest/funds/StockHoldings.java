package com.dobest.funds;

import com.alibaba.fastjson.JSON;
import com.dobest.funds.entity.FundsInfoEntity;
import com.dobest.funds.entity.IndustryEntity;
import com.dobest.funds.entity.StockHoldingsEntity;
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
class StockHoldings {

    private final static String DATA_DATE = "2021-06-30";

    @Autowired
    private FundsService fundsService;

    @Test
    void contextLoads() {
        List<FundsInfoEntity> fundsIds = fundsService.queryFundsIds();

        fundsIds.forEach(fundsInfo->{
            String fundsId = fundsInfo.getFundsId();
            HttpResponse<JsonNode> response = Unirest.get("https://fundmobapi.eastmoney.com/FundMNewApi/FundMNInverstPosition?product=EFund&appVersion=6.4.6&serverVersion=6.4.6" +
                    "&FCODE="+ fundsId +"&deviceid=d58d5379a7a4096e073a0ff2342dc22f%7C%7Ciemi_tluafed_me&version=6.4.6&userId=f29c98a101ee4ee598b0d9ddb880c4f2" +
                    "&DATE="+DATA_DATE+"&cToken=-cuf8j1qc1ej1ckjkuaqnd6uq1jcdu--.12&MobileKey=d58d5379a7a4096e073a0ff2342dc22f%7C%7Ciemi_tluafed_me&appType=ttjj&OSVersion=10&plat=Android&uToken=8hkajr6j1uckuf8-6aejh1kj1jnkh-q8fc951d4c.12&passportid=1073635213473476")
                    .asJson();
            JSONObject body = response.getBody().getObject();
            JSONObject datas = body.getJSONObject("Datas");
            JSONArray fundStocks = datas.getJSONArray("fundStocks");

            List<StockHoldingsEntity> stockHoldingsList = new ArrayList<>();
            fundStocks.forEach(data->{
                StockHoldingsEntity stockHoldingsEntity = new StockHoldingsEntity();
                String jsonStr = data.toString();
                jsonStr = jsonStr.replace("--","0");
                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(jsonStr);
                stockHoldingsEntity.setFundsId(fundsId);
                stockHoldingsEntity.setStockId(jsonObject.getString("GPDM"));
                stockHoldingsEntity.setName(jsonObject.getString("GPJC"));
                stockHoldingsEntity.setHoldingsPercent(jsonObject.getDoubleValue("JZBL"));
                stockHoldingsEntity.setHoldingsOldPercent(jsonObject.getDoubleValue("PCTNVCHG"));
                stockHoldingsEntity.setTEXCH(jsonObject.getString("TEXCH"));
                stockHoldingsEntity.setISINVISBL(jsonObject.getString("ISINVISBL"));
                stockHoldingsEntity.setPCTNVCHGTYPE(jsonObject.getString("PCTNVCHGTYPE"));
                stockHoldingsEntity.setNEWTEXCH(jsonObject.getString("NEWTEXCH"));
                stockHoldingsEntity.setINDEXCODE(jsonObject.getString("INDEXCODE"));
                stockHoldingsEntity.setIndustry(jsonObject.getString("INDEXNAME"));
                stockHoldingsEntity.setDataDate(DATA_DATE);
                stockHoldingsList.add(stockHoldingsEntity);
            });
            if (stockHoldingsList != null && stockHoldingsList.size() > 0){
                fundsService.insertStockHoldings(stockHoldingsList);
            }
        });
    }
}
