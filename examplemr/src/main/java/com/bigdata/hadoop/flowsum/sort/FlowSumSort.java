package com.bigdata.hadoop.flowsum.sort;

import com.bigdata.hadoop.flowsum.FlowBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Created by daoyao
 */
public class FlowSumSort {
    public static class FlowSumSortMapper extends Mapper<LongWritable, Text, FlowBean, Text>{
        Text v = new Text();
        FlowBean k = new FlowBean();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] fields = line.split("\\s+");
            String phoneNum = fields[0];
            long upFlow = Long.parseLong(fields[1]);
            long downFlow = Long.parseLong(fields[2]);
            k.set(upFlow,downFlow);
            v.set(phoneNum);
            context.write(k,v);
        }
    }

    public static class FlowSumSortReducer extends Reducer<FlowBean, Text, Text, FlowBean>{
        @Override
        protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            context.write(values.iterator().next(),key);
        }
    }

    public static void main(String[] args)throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(FlowSumSort.class);
        job.setMapperClass(FlowSumSortMapper.class);
        job.setReducerClass(FlowSumSortReducer.class);
        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);
        FileInputFormat.setInputPaths(job,"E:\\BigTest\\output");
        FileOutputFormat.setOutputPath(job,new Path("E:\\BigTest\\outputsort"));

        boolean b = job.waitForCompletion(true);
        System.exit(b?0:1);
    }
}
