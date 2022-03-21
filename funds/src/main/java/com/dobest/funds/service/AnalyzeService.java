package com.dobest.funds.service;

import com.dobest.funds.entity.FundsHistoryEntity;

import java.util.List;

/**
 * @Description:
 * @ClassName: AnalyzeService
 * @Author chengfei
 * @DateTime 2021/8/30 21:27
 **/
public interface AnalyzeService {
    List<FundsHistoryEntity> queryFundsIds20210801();
}
