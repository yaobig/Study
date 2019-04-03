package com.bigdata.hadoop.Maxtemp.SecondarySort;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 自定义分区类
 * Created by daoyao on 2019/3/31 14:29
 */
public class YearPartitioner extends Partitioner<ComboKey, NullWritable> {
    public int getPartition(ComboKey key, NullWritable nullWritable, int numPartitions) {
        System.out.println("YearPartitioner.getPartition" + key);
        int year = key.getYear();
        return year % numPartitions;
    }
}
