package com.bigdata.hadoop.mr.skew;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 解决数据倾斜问题
 * Created by daoyao
 */
public class WCSkewApp {

    public static void main(String[] args)  throws Exception {
        Logger logger = LoggerFactory.getLogger(WCSkewApp.class);
        /**
         *  添加 conf.set("mapreduce.framework.name","local"); 之后
         *  可以在把路径填写为本地路径进行执行
          */

        String output = "/wordcount/output";
        String intput = "/wordcount/inpout";
        // 通过 Job 来封装本次 mr 的相关信息
        Configuration conf = new Configuration();

        //
        conf.set("mapreduce.framework.name","local");
        Job job = Job.getInstance(conf);

        // 指定本次 mr job jar 包主类
        job.setJarByClass(WCSkewApp.class);

        // 指定 mapper reducer 分别是什么
        job.setMapperClass(WCskewMapper.class);
        job.setReducerClass(WCskewReducer.class);

        // 设置 mapper 阶段的输出k v 类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 设置 reducer 阶段的输出k v 类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileSystem fs = FileSystem.get(conf);
        if (!fs.exists(new Path(intput))){
            logger.info("不存在input目录");
        }
        FileInputFormat.setInputPaths(job,intput);
        if (fs.exists(new Path(output))){
            logger.info("已存在output目录正在删除........");
            fs.delete(new Path(output), true);
        }
        FileOutputFormat.setOutputPath(job,new Path(output));
        boolean b = job.waitForCompletion(true);
        fs.close();
        System.exit(b?0:1);
    }
}
