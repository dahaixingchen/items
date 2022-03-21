package com.dobest.funds.mapper;

import com.dobest.funds.entity.ForecastEntity;
import com.dobest.funds.entity.FundsHistoryEntity;
import com.dobest.funds.entity.FundsInfoEntity;
import com.dobest.funds.entity.IndustryEntity;
import com.dobest.funds.entity.IndustryLayoutEntity;
import com.dobest.funds.entity.StockHoldingsEntity;

import java.util.List;

public interface FundsMapper {
    void insertData(FundsInfoEntity fundsEntitie);

    List<FundsInfoEntity> queryFundsIds();

    void insertIndustry(List<IndustryEntity> industryList);

    void insertStockHoldings(List<StockHoldingsEntity> stockHoldingsList);

    void insertForecast(List<ForecastEntity> forecastEntity);

    void insertFundsHistory(List<FundsHistoryEntity> historyEntities);

    void insertOneFundsHistory(FundsHistoryEntity historyEntitie);

    void insertIndustryLayout(IndustryLayoutEntity layoutEntity);

    void insertOneFundsHistory20210801(FundsHistoryEntity historyEntity);
}
