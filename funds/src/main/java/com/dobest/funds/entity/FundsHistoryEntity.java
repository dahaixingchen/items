package com.dobest.funds.entity;

import lombok.Data;

/**
 * @Description:
 * @ClassName: FundsHistoryEntity
 * @Author chengfei
 * @DateTime 2021/8/28 9:59
 **/
@Data
public class FundsHistoryEntity {
    private Long id;

    private String  fundsId;
    private String  name;
    private Double  price;
    private Double  priceAll;
    private Double  priceDailyPercent;
    private Integer  priceUpDown;
    private String  NAVTYPE;
    private String  RATE;
    private String  MUI;
    private String  SYI;
    private String  dataDate;
    private String  createTime;
    private String  updateTime;
}
