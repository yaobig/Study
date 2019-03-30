package com.bigdata.hadoop.Maxtemp;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * Created by daoyao on 2019/3/28 13:35
 */
public class MaxTempApp {

    public static void main(String[] args)throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 设置 job 的各种属性
        job.setJobName("MaxTempApp");                      // 作业名称类
        job.setJarByClass(MaxTempApp.class);                // 搜索类
        job.setInputFormatClass(TextInputFormat.class);  // 设置输入格式

        // 调用分区类
        job.setPartitionerClass(YearPartitioner.class);

        // 添加输入路径
        FileInputFormat.addInputPath(job,new Path(args[0]));
        // 添加输出路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(MaxTempMapper.class);
        job.setReducerClass(MaxTempReducer.class);

        job.setNumReduceTasks(3);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        job.waitForCompletion(true);
    }
}
