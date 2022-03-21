package com.dobest.funds.entity;

import lombok.Data;

/**
 * @Description:
 * @ClassName: StockHoldingsEntity
 * @Author chengfei
 * @DateTime 2021/8/7 14:26
 **/
@Data
public class StockHoldingsEntity {
    private Long id;
    private String fundsId;
    private String stockId;
    private String name;
    private double holdingsPercent;
    private double holdingsOldPercent;
    private String TEXCH;
    private String ISINVISBL;
    private String PCTNVCHGTYPE;
    private String NEWTEXCH;
    private String INDEXCODE;
    private String industry;
    private String dataDate;
    private String createTime;
}
