package com.dobest.funds.controller;

import com.dobest.funds.schedule.FundsInfoSchedule;
import com.dobest.funds.schedule.IndustryLayoutSchedule;
import com.dobest.funds.schedule.OperateFundsSchedule;
import com.dobest.funds.schedule.RealTimeForecastSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: FundsInfoController
 * @Author chengfei
 * @DateTime 2022/5/2 11:59
 * @TODO:
 **/
@RestController
@RequestMapping("/funds")
public class FundsScheduleController {

    @Autowired
    FundsInfoSchedule fundsInfoSchedule;

    @GetMapping("info")
    public void fundsInfo() {
        fundsInfoSchedule.run();
    }

    @Autowired
    IndustryLayoutSchedule industryLayoutSchedule;

    @GetMapping("industry")
    public void fundsIndustry() {
        industryLayoutSchedule.industryLayout();
    }

    @Autowired
    OperateFundsSchedule operateFundsSchedule;

    @GetMapping("operate")
    public void fundsOperate() {
        operateFundsSchedule.updateMyFunds();
    }

    @Autowired
    RealTimeForecastSchedule realTimeForecastSchedule;

    @GetMapping("realTimeForecast")
    public void realTimeForecast() {
        realTimeForecastSchedule.forecast();
    }
}
