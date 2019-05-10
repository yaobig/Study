package com.bigdata.hivedemo;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by daoyao on 2019/4/28 20:27
 */
public class TestData {
    @Test
    public void test(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy/MM/dd hh:mm:ss");
        System.out.println(sdf.format(date));
    }
}
