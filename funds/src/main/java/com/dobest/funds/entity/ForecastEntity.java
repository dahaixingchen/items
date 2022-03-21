package com.dobest.funds.entity;

import lombok.Data;

/**
 * @Description:
 * @ClassName: ForecastEntity
 * @Author chengfei
 * @DateTime 2021/8/11 14:12
 **/
@Data
public class ForecastEntity {
    private Long id;

    private String  fundsId;
    private String  name;
    private Double forecastPrice;
    private Double forecastPercent;
    private String ISBUY;
    private String LISTTEXCH;
    private String ISLISTTRADE;
    private String dataDate;
    private String createTime;
}
