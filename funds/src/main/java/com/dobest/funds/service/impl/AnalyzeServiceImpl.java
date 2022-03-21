package com.dobest.funds.service.impl;

import com.dobest.funds.entity.FundsHistoryEntity;
import com.dobest.funds.mapper.AnalyzeMapper;
import com.dobest.funds.service.AnalyzeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @ClassName: AnalyzeService
 * @Author chengfei
 * @DateTime 2021/8/30 21:27
 **/
@Service
public class AnalyzeServiceImpl implements AnalyzeService {

    @Resource
    AnalyzeMapper analyzeMapper;

    @Override
    public List<FundsHistoryEntity> queryFundsIds20210801() {
        return analyzeMapper.queryFundsIds20210801();
    }
}
