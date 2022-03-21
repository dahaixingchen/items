package com.dobest.funds.entity.analyze;

import lombok.Data;

/**
 * @Description:
 * @ClassName: DownDataEntity
 * @Author chengfei
 * @DateTime 2021/8/30 21:56
 **/
@Data
public class DownDataEntity {
    private Long id;

    private String  fundsId;
    private String  name;
    private Double  upDownPercent;
    private Integer  upDownDays;
    private String  dataDate;
    private String  createTime;
    private String  updateTime;

    public DownDataEntity(String fundsId, String name, Double upDownPercent, Integer upDownDays, String dataDate) {
        this.fundsId = fundsId;
        this.name = name;
        this.upDownPercent = upDownPercent;
        this.upDownDays = upDownDays;
        this.dataDate = dataDate;
    }
}
