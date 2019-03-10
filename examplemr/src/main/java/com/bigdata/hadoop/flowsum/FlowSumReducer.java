package com.bigdata.hadoop.flowsum;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by daoyao
 *  
 */
public class FlowSumReducer extends Reducer<Text,FlowBean,Text,FlowBean> {
    FlowBean v = new FlowBean();
    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        long upFlowCount = 0;
        long downFlowCount = 0;

        for (FlowBean bean : values){
            upFlowCount += bean.getUpFlow();
            downFlowCount += bean.getDownFlow();
        }
        v.set(upFlowCount,downFlowCount);
        context.write(key,v);
    }
}
