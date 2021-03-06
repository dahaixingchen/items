package com.fei.elasticsearch.data.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fei.elasticsearch.data.bo.TicketVo;
import com.fei.elasticsearch.data.bo.URLBo;
import com.fei.elasticsearch.data.service.AnalysisTicket;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: AnalysisTIcketPort
 * @Author chengfei
 * @Date 2020/10/6 12:30
 * @Description: TODO
 **/
@Service
public class AnalysisTicketPort implements AnalysisTicket {

    @Override
    public List<TicketVo> getTicket(URLBo urlBo) {
        HttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(urlBo.getUrl());
        httpPost.setHeader("Referer",urlBo.getReferer());
        httpPost.setHeader("User-Agent",urlBo.getUserAgent());
        httpPost.setHeader("Cookie",urlBo.getCookie());
        HttpEntity entity = null;
        String dataJson = null;
        List<TicketVo> ticketVos = new ArrayList<>();
        try {
            entity = httpclient.execute(httpPost).getEntity();
            if (entity != null){
                dataJson = EntityUtils.toString(entity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //解析得到的数据，它是一个json串
        if (urlBo.getMark() == null){
            this.url91(urlBo, dataJson, ticketVos);
        }else if (urlBo.getMark() != null && !"".equals(urlBo.getMark())
                && "qianmu".equals(urlBo.getMark())){
        }
        return ticketVos;
    }


    private void url91(URLBo urlBo, String dataJson, List<TicketVo> ticketVos) {
        try {
        JSONObject jsonObject = JSONObject.parseObject(dataJson);
        JSONObject data = jsonObject.getJSONObject("Data");
        JSONObject stockNumsDict = data.getJSONObject("StockNumsDict");
        JSONArray goods = new JSONArray();


            goods  = JSONArray.parseArray(data.getString("Goods"));

        for (int i = 0; i < goods.size(); i++) {
            TicketVo ticketVo = new TicketVo();
            JSONObject jsonObject1 = goods.getJSONObject(i);
            ticketVo.setUrl(urlBo.getUrlWeb());
            ticketVo.setName(jsonObject1.getString("Name"));
            ticketVo.setDescribe(jsonObject1.getString("Summary"));
            ticketVo.setPrice(Double.parseDouble(jsonObject1.getString("Price")));
            String spId = jsonObject1.getString("SpId");
            ticketVo.setNum((Integer)stockNumsDict.get(spId));
            ticketVos.add(ticketVo);
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
