package com.dobest.funds.test;

import java.time.LocalDate;

/**
 * @Description:
 * @ClassName: Test
 * @Author chengfei
 * @DateTime 2021/8/24 21:19
 **/
public class Test {
    @org.junit.jupiter.api.Test
    public void dataTest(){
        System.out.println(LocalDate.now());
        System.out.println(LocalDate.now().plusDays(-1).toString());
    }
}
