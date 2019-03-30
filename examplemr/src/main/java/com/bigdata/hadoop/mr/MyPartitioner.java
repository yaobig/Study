package com.bigdata.hadoop.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by daoyao
 * 自定义分区，解决数据倾斜
 */
public class MyPartitioner extends Partitioner <Text, IntWritable> {
    public int getPartition(Text text, IntWritable intWritable, int numPartitions) {
        return 0;
    }
}

