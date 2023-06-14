package com.andyadc.abatis.test;

import cn.hutool.core.util.IdcardUtil;
import org.junit.jupiter.api.Test;

public class TT {

    @Test
    public void test001() {
        int age = IdcardUtil.getAgeByIdCard("342501200509148765");
        System.out.println(age);
    }
}
