package com.bigdata.hadoop.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by daoyao
 */
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable sum = new IntWritable();

    /**
     * reduce 接受所有来自 map 阶段处理的诗句之后，按照 key 的字典进行排序
     *  <hello.1><hadoop,1><spark,1><hadoop,1>
     *
     *  按照 key 是否相同作为一组去调用 reduce 方法
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        // 定义计数器
        int count = 0;
        // 遍历一组迭代器， 把每一个数量 1 累加起来就构成了单词的
        for (IntWritable value : values) {
            count += value.get();
        }
        sum.set(count);
        context.write(key, sum);
    }
}
