package com.dobest.funds.service.impl;

import com.dobest.funds.mapper.OperateFundsMapper;
import com.dobest.funds.service.OperateFundsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * @Description:
 * @ClassName: QueryFundsServiceImpl
 * @Author chengfei
 * @DateTime 2021/8/21 11:00
 **/
@Service
@Slf4j
public class OperateFundsServiceImpl implements OperateFundsService {
    @Resource
    private OperateFundsMapper queryFundsMapper;

    @Override
    public void updateMyFunds() {
        LocalDate today = LocalDate.now();
        String yesterday = today.plusDays(-1).toString();
        log.info(String.format("更新"+yesterday+"的数据"));
        queryFundsMapper.updateMyFunds(yesterday);
    }
}
