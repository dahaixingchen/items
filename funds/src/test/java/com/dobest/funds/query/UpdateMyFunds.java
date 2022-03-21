package com.dobest.funds.query;

import com.dobest.funds.service.OperateFundsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

/**
 * @Description:
 * @ClassName: FundsInfo
 * @Author chengfei
 * @DateTime 2021/8/7 10:51
 **/
@SpringBootTest
public class UpdateMyFunds {
    @Autowired
    private OperateFundsService queryFundsService;

    @Test
    public void updateMyFunds(){
//        queryFundsService.updateMyFunds();
        String string = LocalDate.now().toString();
        System.out.println(string);
    }
}
