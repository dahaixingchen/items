package com.dobest.funds.service;

import com.alibaba.fastjson.JSONObject;
import com.dobest.funds.entity.FundsHistoryEntity;
import com.dobest.funds.entity.FundsInfoEntity;
import com.dobest.funds.entity.IndustryEntity;
import com.dobest.funds.entity.IndustryLayoutEntity;
import com.dobest.funds.entity.StockHoldingsEntity;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public interface FundsService {

    /**
     * @param arrayList
     * @Todo: 所有的基金
     * @return:
     * @DateTime: 2021/8/7 14:55
     */
    void insertData(JSONObject arrayList);

    /**
     * @param
     * @Todo: 拿到所有基金的代码id
     * @return:{@link java.util.List<java.lang.String>}
     * @DateTime: 2021/8/7 14:55
     */
    List<FundsInfoEntity> queryFundsIds();

    /**
     * @param industryList
     * @Todo: 基金资金占各个行业的比例
     * @return:
     * @DateTime: 2021/8/7 14:54
     */
    void insertIndustry(List<IndustryEntity> industryList);

    /**
     * @param stockHoldingsList
     * @Todo: 基金持股占比
     * @return:
     * @DateTime: 2021/8/7 14:54
     */
    void insertStockHoldings(List<StockHoldingsEntity> stockHoldingsList);

    void insertForecast(JSONArray jsonObject);

    void insertFundsHistory(List<FundsHistoryEntity> historyEntities);

    void insertIndustryLayout(IndustryLayoutEntity layoutEntity);

}
