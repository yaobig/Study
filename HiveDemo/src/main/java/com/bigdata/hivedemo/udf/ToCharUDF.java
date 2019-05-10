package com.bigdata.hivedemo.udf;

import org.apache.hadoop.hive.ql.exec.UDF;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by daoyao on 2019/4/28 16:50
 */
public class ToCharUDF extends UDF {
    public String rvalute(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy/MM/dd hh:mm:ss");
        return sdf.format(date);
    }
}
