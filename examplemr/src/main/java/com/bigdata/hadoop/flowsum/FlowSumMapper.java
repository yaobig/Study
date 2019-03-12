package com.bigdata.hadoop.flowsum;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by daoyao
 *  在 mr 程序中， 也可以使用我们自定义的类型作为 mr 的数据类型， 前提是需要实现 hadoop 的序列化机制 Writable
 */
public class FlowSumMapper extends Mapper<LongWritable, Text,Text,FlowBean> {
    private Text k = new Text();
    private FlowBean v = new FlowBean();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] fields = line.split("\t");
        String phoneNum = fields[1];
        long upFlow = Long.parseLong(fields[fields.length-3]);
        long downFlow = Long.parseLong(fields[fields.length-2]);
        k.set(phoneNum);
        v.set(upFlow,downFlow);
        context.write(k,v);
    }
}
