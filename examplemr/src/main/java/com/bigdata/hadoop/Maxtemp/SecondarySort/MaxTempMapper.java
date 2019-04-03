package com.bigdata.hadoop.Maxtemp.SecondarySort;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by daoyao on 2019/3/28 13:35
 */
public class MaxTempMapper extends Mapper<LongWritable, Text, ComboKey, NullWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        System.out.println("MaxTempMapper.amp");
        String line = value.toString();
        String[] arr = line.split(" ");
        ComboKey keyOut = new ComboKey();
        keyOut.setYear(Integer.parseInt(arr[0]));
        keyOut.setTemp(Integer.parseInt(arr[1]));
        context.write(keyOut,NullWritable.get());
    }
}
