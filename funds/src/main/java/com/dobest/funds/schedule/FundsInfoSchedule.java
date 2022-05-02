package com.dobest.funds.schedule;

import com.alibaba.fastjson.JSON;
import com.dobest.funds.service.FundsService;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @ClassName: FundsInfoSchedule
 * @Author chengfei
 * @DateTime 2021/8/6 17:59
 **/
@Component
@Slf4j
public class FundsInfoSchedule {
    @Autowired
    private FundsService fundsService;

    @Scheduled(cron = "0 0 8 * * ?")
    public void run(){

        HttpResponse<JsonNode> response = Unirest.post("https://fundmobapi.eastmoney.com/FundMNewApi/FundMNRank")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Host", "fundmobapi.eastmoney.com")
                .body("appType=ttjj&Sort=desc&product=EFund&gToken=ceaf-274a74c60001d86c368ed3205cd29e&version=6.4.6" +
                        "&DataConstraintType=0&onFundCache=3&ctoken=-cuf8j1qc1ej1ckjkuaqnd6uq1jcdu--.12&ESTABDATE=&deviceid=d58d5379a7a4096e073a0ff2342dc22f%7C%7Ciemi_tluafed_me&ENDNAV=&FundType=0&BUY=true" +
                        "&pageIndex=0&RLEVEL_SZ=&RISKLEVEL=&DISCOUNT=&utoken=8hkajr6j1uckuf8-6aejh1kj1jnkh-q8fc951d4c.12&CompanyId=&SortColumn=SYL_Y" +
                        "&pageSize=30&MobileKey=d58d5379a7a4096e073a0ff2342dc22f%7C%7Ciemi_tluafed_me&TOPICAL=&plat=Android&ISABNORMAL=true")
                .asJson();
        org.json.JSONObject body = response.getBody().getObject();
        System.out.println(body);

        JSONArray datas = body.getJSONArray("Datas");

        //i是基金的页数
        for (int i = 0; (i < 10000 && datas.length()!=0); i++) {
            datas.forEach(data->{
                String jsonStr = data.toString();
                jsonStr = jsonStr.replace("--","0");
                com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(jsonStr);

                fundsService.insertData(jsonObject);
            });
            log.info("爬到的数据，data:{}",datas);
            response = Unirest.post("https://fundmobapi.eastmoney.com/FundMNewApi/FundMNRank")
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Host", "fundmobapi.eastmoney.com")
                    .body("appType=ttjj&Sort=desc&product=EFund&gToken=ceaf-274a74c60001d86c368ed3205cd29e&version=6.4.6" +
                            "&DataConstraintType=0&onFundCache=3&ctoken=-cuf8j1qc1ej1ckjkuaqnd6uq1jcdu--.12&ESTABDATE=&deviceid=d58d5379a7a4096e073a0ff2342dc22f%7C%7Ciemi_tluafed_me&ENDNAV=&FundType=0&BUY=true" +
                            "&pageIndex="+ i +"&RLEVEL_SZ=&RISKLEVEL=&DISCOUNT=&utoken=8hkajr6j1uckuf8-6aejh1kj1jnkh-q8fc951d4c.12&CompanyId=&SortColumn=SYL_Y" +
                            "&pageSize=30&MobileKey=d58d5379a7a4096e073a0ff2342dc22f%7C%7Ciemi_tluafed_me&TOPICAL=&plat=Android&ISABNORMAL=true")
                    .asJson();
            body = response.getBody().getObject();
            datas = body.getJSONArray("Datas");
        }


    }
}
