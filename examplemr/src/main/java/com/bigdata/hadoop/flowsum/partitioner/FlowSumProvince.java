package com.bigdata.hadoop.flowsum.partitioner;


import com.bigdata.hadoop.flowsum.FlowBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
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
public class FlowSumProvince {
    public static class FlowSumProvinceMapper extends Mapper<LongWritable, Text, Text, FlowBean> {
        Text k = new Text();
        FlowBean v = new FlowBean();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] flieds = line.split("\\s+");
            String phoneNum = flieds[0];

            long upFlow = Long.parseLong(flieds[flieds.length-3]);
            long downFlow = Long.parseLong(flieds[flieds.length-2]);
            k.set(phoneNum);
            v.set(upFlow,downFlow);
            context.write(k,v);
        }
    }

    public static class FlowSumProvinceReducer extends Reducer<Text, FlowBean, Text, FlowBean> {
        FlowBean v = new FlowBean();

        @Override
        protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
            long upFlowCount = 0;
            long downFlowCount = 0;
            for (FlowBean flowBean : values){
                upFlowCount += flowBean.getUpFlow();
                downFlowCount += flowBean.getDownFlow();
            }
            v.set(upFlowCount,downFlowCount);
            context.write(key,v);
        }
    }

    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(FlowSumProvince.class);
        job.setMapperClass(FlowSumProvinceMapper.class);
        job.setReducerClass(FlowSumProvinceReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        job.setNumReduceTasks(6);
        // 如果业务有需要， 就可以设置 combiner 组件
        job.setCombinerClass(FlowSumProvinceReducer.class);
        job.setPartitionerClass(ProvincePartitioner.class);

        FileInputFormat.setInputPaths(job,"E:\\BigTest\\input");
        FileOutputFormat.setOutputPath(job,new Path("E:\\BigTest\\outputsortProvince"));

        boolean b = job.waitForCompletion(true);
        System.exit(b?0:1);
    }
}
