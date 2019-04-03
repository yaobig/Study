package com.bigdata.hadoop.Maxtemp.SecondarySort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFilter;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.InputSampler;
import org.apache.hadoop.mapreduce.lib.partition.TotalOrderPartitioner;

/**
 * Created by daoyao on 2019/3/28 13:35
 */
public class MaxTempApp {

    public static void main(String[] args)throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        // 设置 job 的各种属性
        job.setJobName("SecondarySortApp");                      // 作业名称类
        job.setJarByClass(MaxTempApp.class);                // 搜索类
        job.setInputFormatClass(TextInputFormat.class);  // 设置输入格式

        // 添加输入路径
        FileInputFormat.addInputPath(job,new Path(args[0]));
        // 添加输出路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(MaxTempMapper.class);
        job.setReducerClass(MaxTempReducer.class);

        job.setMapOutputKeyClass(ComboKey.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);

        // 设置分区类
        job.setPartitionerClass(YearPartitioner.class);
        // 设置分区对比器
        job.setGroupingComparatorClass(YearGroupComparator.class);
        // 设置排序对比器
        job.setSortComparatorClass(ComboKeyComparator.class);

        job.setNumReduceTasks(3);


        job.waitForCompletion(true);
    }
}
