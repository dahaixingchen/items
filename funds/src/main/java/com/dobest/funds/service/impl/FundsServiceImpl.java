package com.dobest.funds.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dobest.funds.entity.ForecastEntity;
import com.dobest.funds.entity.FundsHistoryEntity;
import com.dobest.funds.entity.FundsInfoEntity;
import com.dobest.funds.entity.IndustryEntity;
import com.dobest.funds.entity.IndustryLayoutEntity;
import com.dobest.funds.entity.StockHoldingsEntity;
import com.dobest.funds.mapper.FundsMapper;
import com.dobest.funds.service.FundsService;
import org.json.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @ClassName: FundsServiceImpl
 * @Author chengfei
 * @DateTime 2021/8/5 22:08
 **/
@Service
public class FundsServiceImpl implements FundsService {

    @Resource
    private FundsMapper fundsMapper;

    @Override
    public void insertData(JSONObject data) {

        FundsInfoEntity fundsEntity = new FundsInfoEntity();
        fundsEntity.setFundsId(data.getString("FCODE"));
        fundsEntity.setName(data.getString("SHORTNAME"));
        fundsEntity.setType(data.getIntValue("FUNDTYPE"));
        fundsEntity.setBFUNDTYPE(data.getIntValue("BFUNDTYPE"));
        fundsEntity.setFEATURE(data.getString("FEATURE"));
        fundsEntity.setPrice(data.getDoubleValue("LJJZ"));
        fundsEntity.setHLDWJZ(data.getString("HLDWJZ"));
        fundsEntity.setPriceAll(data.getDoubleValue("DWJZ"));
        fundsEntity.setFTYI(data.getString("FTYI"));
        fundsEntity.setTEYI(data.getString("TEYI"));
        fundsEntity.setTFYI(data.getString("TFYI"));
        fundsEntity.setPriceDailyPercent(data.getDoubleValue("RZDF"));
        fundsEntity.setPriceWeeklyPercent(data.getDoubleValue("SYL_Z"));
        fundsEntity.setPriceMonthlyPercent(data.getDoubleValue("SYL_Y"));
        fundsEntity.setPrice3MonthlyPercent(data.getDoubleValue("SYL_3Y"));
        fundsEntity.setPrice6MonthlyPercent(data.getDoubleValue("SYL_6Y"));
        fundsEntity.setPriceYearPercent(data.getDoubleValue("SYL_1N"));
        fundsEntity.setPrice2YearPercent(data.getDoubleValue("SYL_2N"));
        fundsEntity.setPrice3year_percent(data.getDoubleValue("SYL_3Y"));
        fundsEntity.setPrice5yearPercent(data.getDoubleValue("SYL_5Y"));
        fundsEntity.setPriceCurrentYearPercent(data.getDoubleValue("SYL_JN"));
        fundsEntity.setPriceAllPercent(data.getDoubleValue("SYL_LN"));
        fundsEntity.setZJL(data.getString("ZJL"));
        fundsEntity.setTARGETYIELD(data.getString("TARGETYIELD"));
        fundsEntity.setCYCLE(data.getString("CYCLE"));
        fundsEntity.setKFR(data.getString("KFR"));
        fundsEntity.setLEVERAGE(data.getString("LEVERAGE"));
        fundsEntity.setBAGTYPE(data.getString("BAGTYPE"));
        fundsEntity.setBUY(data.getBooleanValue("BUY"));
        fundsEntity.setLISTTEXCH(data.getString("LISTTEXCH"));
        fundsEntity.setNEWTEXCH(data.getString("NEWTEXCH"));
        fundsEntity.setISLISTTRADE(data.getString("ISLISTTRADE"));
        fundsEntity.setCommonEarnings1year(data.getDoubleValue("PTDT_Y"));
        fundsEntity.setCommonEarnings2year(data.getDoubleValue("PTDT_TWY"));
        fundsEntity.setCommonEarnings3year(data.getDoubleValue("PTDT_TRY"));
        fundsEntity.setCommonEarnings5year(data.getDoubleValue("PTDT_FY"));
        fundsEntity.setSmartEarnings1year(data.getDoubleValue("MBDT_Y"));
        fundsEntity.setSmartEarnings2year(data.getDoubleValue("MBDT_TWY"));
        fundsEntity.setSmartEarnings3year(data.getDoubleValue("MBDT_TRY"));
        fundsEntity.setSmartEarnings5year(data.getDoubleValue("MBDT_FY"));
        fundsEntity.setTargetEarnings1year(data.getDoubleValue("YDDT_Y"));
        fundsEntity.setTargetEarnings2year(data.getDoubleValue("YDDT_TWY"));
        fundsEntity.setTargetEarnings3year(data.getDoubleValue("YDDT_TRY"));
        fundsEntity.setTargetEarnings5year(data.getDoubleValue("YDDT_FY"));
        fundsEntity.setMoveEarnings1year(data.getDoubleValue("DWDT_Y"));
        fundsEntity.setMoveEarnings2year(data.getDoubleValue("DWDT_TWY"));
        fundsEntity.setMoveEarnings3year(data.getDoubleValue("DWDT_TRY"));
        fundsEntity.setMoveEarnings5year(data.getDoubleValue("DWDT_FY"));
        fundsEntity.setTotalAssets(data.getDoubleValue("ENDNAV"));
        fundsEntity.setSALEVOLUME(data.getString("SALEVOLUME"));
        fundsEntity.setPV_Y(data.getString("PV_Y"));
        fundsEntity.setDTCOUNT_Y(data.getString("DTCOUNT_Y"));
        fundsEntity.setORGSALESRANK(data.getString("ORGSALESRANK"));
        fundsEntity.setISABNORMAL(data.getString("ISABNORMAL"));
        fundsEntity.setDataDate(data.getString("FSRQ"));
        fundsEntity.setUpdateTime(LocalDateTime.now().toString());

        fundsMapper.insertData(fundsEntity);

        FundsHistoryEntity historyEntity = new FundsHistoryEntity();
        historyEntity.setFundsId(fundsEntity.getFundsId());
        historyEntity.setName(fundsEntity.getName());
        historyEntity.setPrice(fundsEntity.getPrice());
        historyEntity.setPriceAll(fundsEntity.getPriceAll());
        Double priceDailyPercent = fundsEntity.getPriceDailyPercent();
        historyEntity.setPriceDailyPercent(priceDailyPercent);
        if (priceDailyPercent>0){
            historyEntity.setPriceUpDown(1);
        }else if (priceDailyPercent<0){
            historyEntity.setPriceUpDown(-1);
        }else{
            historyEntity.setPriceUpDown(0);
        }
        historyEntity.setDataDate(fundsEntity.getDataDate());

        fundsMapper.insertOneFundsHistory(historyEntity);
        fundsMapper.insertOneFundsHistory20210801(historyEntity);

    }

    @Override
    public List<FundsInfoEntity> queryFundsIds() {
        return fundsMapper.queryFundsIds();
    }

    @Override
    public void insertIndustry(List<IndustryEntity> industryList) {
        fundsMapper.insertIndustry(industryList);

    }

    @Override
    public void insertStockHoldings(List<StockHoldingsEntity> stockHoldingsList) {
        fundsMapper.insertStockHoldings(stockHoldingsList);
    }

    @Override
    public void insertForecast(JSONArray datas) {
        ArrayList<ForecastEntity> arrayList = new ArrayList<>();
        datas.forEach(data -> {
            String jsonStr = data.toString();
            jsonStr = jsonStr.replace("--", "0");
            com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(jsonStr);

            ForecastEntity forecastEntity = new ForecastEntity();
            forecastEntity.setFundsId(jsonObject.getString("FCODE"));
            forecastEntity.setName(jsonObject.getString("SHORTNAME"));
            forecastEntity.setForecastPrice(jsonObject.getDoubleValue("GSZ"));
            forecastEntity.setForecastPercent(jsonObject.getDoubleValue("GSZZL"));
            forecastEntity.setISBUY(jsonObject.getString("ISBUY"));
            forecastEntity.setLISTTEXCH(jsonObject.getString("LISTTEXCH"));
            forecastEntity.setISLISTTRADE(jsonObject.getString("ISLISTTRADE"));
            forecastEntity.setDataDate(jsonObject.getString("GZTIME"));
            arrayList.add(forecastEntity);
        });
        fundsMapper.insertForecast(arrayList);
    }

    @Override
    public void insertFundsHistory(List<FundsHistoryEntity> historyEntities) {
        System.out.println("数据总量"+historyEntities.size());
        fundsMapper.insertFundsHistory(historyEntities);

    }

    @Override
    public void insertIndustryLayout(IndustryLayoutEntity layoutEntity) {
        fundsMapper.insertIndustryLayout(layoutEntity);
    }
}
