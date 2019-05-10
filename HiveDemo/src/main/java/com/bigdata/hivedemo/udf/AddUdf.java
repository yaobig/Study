package com.bigdata.hivedemo.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * 自定义
 * Created by daoyao on 2019/4/27 16:35
 */
@Description(name = "myadd",
        value = "myadd(int a , int b) ==> return a + b ",
        extended = "Example:\n"
                + " myadd(1,1) ==> 2 \n"
                + " myadd(1,2,3) ==> 6;")
public class AddUdf extends UDF {
    public int evaluate(int a, int b){
        return a + b;
    }
    public int evaluate(int a ,int b , int c) {
        return a + b + c;
    }
}
