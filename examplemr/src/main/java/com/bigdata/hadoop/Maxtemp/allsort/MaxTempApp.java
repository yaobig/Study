package com.bigdata.hadoop.Maxtemp.allsort;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
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
        job.setJobName("MaxTempApp");                      // 作业名称类
        job.setJarByClass(MaxTempApp.class);                // 搜索类
        job.setInputFormatClass(SequenceFileInputFilter.class);  // 设置输入格式

        // 添加输入路径
        FileInputFormat.addInputPath(job,new Path(args[0]));
        // 添加输出路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(MaxTempMapper.class);
        job.setReducerClass(MaxTempReducer.class);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(IntWritable.class);


        job.setNumReduceTasks(3);

        // 创建随机采样器对象
        // freq：每个 key 被选中的概率
        // numSapmple: 抽取样本的总数
        // maxSplitSampled: 最大采样切片数
        InputSampler.Sampler<IntWritable, IntWritable> sampler =
                new InputSampler.RandomSampler<IntWritable, IntWritable>(1, 6000, 3);

        TotalOrderPartitioner.setPartitionFile(job.getConfiguration(),new Path("e:/BigTest/maxtemp/par.lst"));
        // 设置全排序分区类
        job.setPartitionerClass(TotalOrderPartitioner.class);




        // 将 sample 数据写入分区文件
        InputSampler.writePartitionFile(job,sampler);


        job.waitForCompletion(true);
    }
}
