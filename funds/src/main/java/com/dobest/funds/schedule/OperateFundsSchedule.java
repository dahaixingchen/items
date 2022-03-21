package com.dobest.funds.schedule;

import com.dobest.funds.service.OperateFundsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @ClassName: UpdateMyFunds
 * @Author chengfei
 * @DateTime 2021/8/24 20:39
 **/
@Component
@Slf4j
public class OperateFundsSchedule {
    @Autowired
    private OperateFundsService queryFundsService;

    @Scheduled(cron = "0 20 8 * * ?")
//    @Scheduled(cron = "0/3 * * * * ?")
    public void updateMyFunds(){
        queryFundsService.updateMyFunds();
    }
}
