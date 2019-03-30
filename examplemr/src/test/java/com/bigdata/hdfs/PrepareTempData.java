package com.bigdata.hdfs;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Created by daoyao on 2019/3/29 14:37
 */
public class PrepareTempData {
    /**
     * 随机生成 100 以内的随机数输出到文件中
     */
    @Test
    public void makeData()throws IOException {
        FileWriter fw = new FileWriter("E:/BigTest/temp.txt");
        for (int i =0; i < 6000; i++){
            int year = 1970 + new Random().nextInt(100);
            int temp = -30 + new Random().nextInt(100);
            fw.write("" + year + " " + temp +"\r\n");
        }
        fw.close();
    }
}
