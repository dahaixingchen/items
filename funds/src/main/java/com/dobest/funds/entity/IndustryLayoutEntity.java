package com.dobest.funds.entity;

import lombok.Data;

/**
 * @Description:
 * @ClassName: IndustryLayoutEntity
 * @Author chengfei
 * @DateTime 2021/8/29 22:43
 **/
@Data
public class IndustryLayoutEntity {
    private Long id;
    private String industryCode;
    private String industryName;
    private Double priceDailyPercent;
    private Double priceWeeklyPercent;
    private Double priceMonthlyPercent;
    private Double price3monthPercent;
    private Double priceYearPercent;
    private Double priceCurrentYearPercent;
    private Double industryPrice;
    private Double industry3minSpeed;
    private Double industry5minSpeed;
    private Double industryFundWeekly;
    private Double industryFundDaily;
    private String TYPE;
    private String LZTS;
    private String LDTS;
    private String JLCTS;
    private String IndexCode;
    private String JLRTS;
    private Double fundsPriceWeeklyPercent;
    private Double fundsPriceMonthlyPercent;
    private Double fundsPrice3monthPercent;
    private Double fundsPriceYearPercent;
    private Double fundsPriceCurrentYearPercent;
    private String fundsId;
    private String fundsName;
    private Double similarity;
    private String dataDate;
    private String updateTime;
    private String createTime;
}
