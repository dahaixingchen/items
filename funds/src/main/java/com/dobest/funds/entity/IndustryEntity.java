package com.dobest.funds.entity;

import lombok.Data;

/**
 * @Description:
 * @ClassName: IndustryEntity
 * @Author chengfei
 * @DateTime 2021/8/7 10:36
 **/
@Data
public class IndustryEntity {
    private Long id;
    private String fundsId;
    private String name;
    private double price;
    private double pricePercent;
    private String dataDate;
    private String createTime;
}
