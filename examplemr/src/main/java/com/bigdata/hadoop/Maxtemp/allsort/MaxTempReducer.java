package com.bigdata.hadoop.Maxtemp.allsort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by daoyao on 2019/3/28 13:35
 */
public class MaxTempReducer extends Reducer<IntWritable,IntWritable,IntWritable,IntWritable> {
    @Override
    protected void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int max = Integer.MIN_VALUE;
        for (IntWritable iw : values){
            max = max > iw.get() ? max : iw.get();
        }
        context.write(key,new IntWritable(max));
    }
}
