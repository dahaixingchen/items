package com.dobest.funds.entity;

import lombok.Data;

/**
 * @Description:
 * @ClassName: FundsInfo
 * @Author chengfei
 * @DateTime 2021/8/5 21:50
 **/
@Data
public class FundsInfoEntity {
    private Long id;

    private String  fundsId;
    private String  name;
    private Integer type;
    private Integer BFUNDTYPE;
    private String  FEATURE;
    private Double  price;
    private String  HLDWJZ;
    private Double  priceAll;
    private String  FTYI;
    private String  TEYI;
    private String  TFYI;
    private Double  priceDailyPercent;
    private Double  priceWeeklyPercent;
    private Double  priceMonthlyPercent;
    private Double  price3MonthlyPercent;
    private Double  price6MonthlyPercent;
    private Double  priceYearPercent;
    private Double  price2YearPercent;
    private Double  price3year_percent;
    private Double  price5yearPercent;
    private Double  priceCurrentYearPercent;
    private Double  priceAllPercent;
    private String  ZJL;
    private String  TARGETYIELD;
    private String  CYCLE;
    private String  KFR;
    private String  LEVERAGE;
    private String  BAGTYPE;
    private Boolean BUY;
    private String  LISTTEXCH;
    private String  NEWTEXCH;
    private String  ISLISTTRADE;
    private Double  commonEarnings1year;
    private Double  commonEarnings2year;
    private Double  commonEarnings3year;
    private Double  commonEarnings5year;
    private Double  smartEarnings1year;
    private Double  smartEarnings2year;
    private Double  smartEarnings3year;
    private Double  smartEarnings5year;
    private Double  targetEarnings1year;
    private Double  targetEarnings2year;
    private Double  targetEarnings3year;
    private Double  targetEarnings5year;
    private Double  moveEarnings1year;
    private Double  moveEarnings2year;
    private Double  moveEarnings3year;
    private Double  moveEarnings5year;
    private Double  totalAssets;
    private String  SALEVOLUME;
    private String  PV_Y;
    private String  DTCOUNT_Y;
    private String  ORGSALESRANK;
    private String  ISABNORMAL;
    private String  dataDate;
    private String  createTime;
    private String  updateTime;

}
