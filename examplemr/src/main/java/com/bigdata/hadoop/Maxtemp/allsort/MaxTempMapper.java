package com.bigdata.hadoop.Maxtemp.allsort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by daoyao on 2019/3/28 13:35
 */
public class MaxTempMapper extends Mapper<IntWritable, IntWritable,IntWritable, IntWritable> {
    @Override
    protected void map(IntWritable key, IntWritable value, Context context) throws IOException, InterruptedException {
        context.write(key,value);
    }
}
