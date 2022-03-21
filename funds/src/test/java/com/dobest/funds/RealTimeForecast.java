package com.dobest.funds;

import com.alibaba.fastjson.JSON;
import com.dobest.funds.service.FundsService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @ClassName: RealTimeForecast
 * @Author chengfei
 * @DateTime 2021/8/11 13:56
 **/
@SpringBootTest
public class RealTimeForecast {
    @Autowired
    private FundsService fundsService;
    @Test
    public void forecast(){
        HttpResponse<JsonNode> response = Unirest.post("https://fundmobapi.eastmoney.com/FundMNewApi/FundMNValuationList")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Host", "fundmobapi.eastmoney.com")
                .body("appType=ttjj&Sort=desc&product=EFund&gToken=ceaf-274a74c60001d86c368ed3205cd29e&version=6.4.6&onFundCache=3&ctoken=-cuf8j1qc1ej1ckjkuaqnd6uq1jcdu--.12&ESTABDATE=&deviceid=d58d5379a7a4096e073a0ff2342dc22f%7C%7Ciemi_tluafed_me&ENDNAV=&FundType=0&BUY=true&pageIndex=200&RLEVEL_SZ=&RISKLEVEL=&DISCOUNT=&utoken=8hkajr6j1uckuf8-6aejh1kj1jnkh-q8fc951d4c.12&CompanyId=&SortColumn=GSZZL&pageSize=30&MobileKey=d58d5379a7a4096e073a0ff2342dc22f%7C%7Ciemi_tluafed_me&TOPICAL=&plat=Android&ISABNORMAL=true")
                .asJson();
        org.json.JSONObject body = response.getBody().getObject();
        JSONArray datas = body.getJSONArray("Datas");
        for (int i = 0; (i < 10000 && datas.length()!=0); i++) {

            fundsService.insertForecast(datas);
            response = Unirest.post("https://fundmobapi.eastmoney.com/FundMNewApi/FundMNValuationList")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Host", "fundmobapi.eastmoney.com")
                    .body("appType=ttjj&Sort=desc&product=EFund&gToken=ceaf-274a74c60001d86c368ed3205cd29e&version=6.4.6&onFundCache=3&ctoken=-cuf8j1qc1ej1ckjkuaqnd6uq1jcdu--.12&ESTABDATE=&deviceid=d58d5379a7a4096e073a0ff2342dc22f%7C%7Ciemi_tluafed_me&ENDNAV=&FundType=0&BUY=true" +
                            "&pageIndex="+i+"&RLEVEL_SZ=&RISKLEVEL=&DISCOUNT=&utoken=8hkajr6j1uckuf8-6aejh1kj1jnkh-q8fc951d4c.12&CompanyId=&SortColumn=GSZZL&pageSize=30&MobileKey=d58d5379a7a4096e073a0ff2342dc22f%7C%7Ciemi_tluafed_me&TOPICAL=&plat=Android&ISABNORMAL=true")
                    .asJson();
            body = response.getBody().getObject();
            datas = body.getJSONArray("Datas");
        }
    }
}
