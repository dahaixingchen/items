package com.dobest.funds;

import com.dobest.funds.service.OperateFundsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @ClassName: UpdateMyFunds
 * @Author chengfei
 * @DateTime 2021/8/24 20:39
 **/
@SpringBootTest
public class OperateFunds {
    @Autowired
    private OperateFundsService queryFundsService;

    @Test
    public void updateMyFunds(){
        queryFundsService.updateMyFunds();
    }
}
