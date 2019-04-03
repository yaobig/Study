package com.bigdata.hadoop.Maxtemp.SecondarySort;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by daoyao on 2019/3/28 13:35
 */
public class MaxTempReducer extends Reducer<ComboKey, NullWritable,IntWritable,IntWritable> {
    @Override
    protected void reduce(ComboKey key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
        System.out.println("MaxTempReducer.reduce" + key);
        int year = key.getYear();
        int temp = key.getTemp();
        context.write(new IntWritable(year),new IntWritable(temp));
    }
}
