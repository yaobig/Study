package com.bigdata.hadoop.mr.skew;


import com.bigdata.hadoop.mr.Util;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 *
 */
public class WCskewMapper extends Mapper<LongWritable, Text, Text, IntWritable> {


    private Text word = new Text();
    private IntWritable one = new IntWritable(1);
    /**
     * 每传入一个 <k,v> 调用一次 map
     *
     * @param key
     * @param value
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        // 拿到传入进来的一行内弄， 把数据类型转换为 String
        String line = value.toString();
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info(line);
        String[] words = line.split(" ");
        // 遍历数组， 每出现一个单词， 就标记一个数字 1 <单词，1>
        for (String w : words ) {
            word.set(w);
            // 使用 mr 程序的上下文 context
            // 作为 reduce 节点的输入数据
            context.write(word,one);
            context.getCounter("m",Util.getInFo(this,"map")).increment(1);
        }
    }
}
