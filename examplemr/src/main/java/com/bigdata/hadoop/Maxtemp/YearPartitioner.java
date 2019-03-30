package com.bigdata.hadoop.Maxtemp;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by daoyao on 2019/3/29 9:49
 */
public class YearPartitioner extends Partitioner<IntWritable,IntWritable> {
    public int getPartition(IntWritable year, IntWritable temp, int parts) {
        int y = year.get() - 1970;
        if (y < 33){
            return 0;
        }else if (y >= 33 && y < 66){
            return 1;
        }else {
            return 2;
        }
    }
}
