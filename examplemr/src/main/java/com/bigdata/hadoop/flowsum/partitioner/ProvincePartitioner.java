package com.bigdata.hadoop.flowsum.partitioner;


import com.bigdata.hadoop.flowsum.FlowBean;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;

/**
 * Created by daoyao
 */
public class ProvincePartitioner extends Partitioner<Text, FlowBean>{

    public static HashMap<String,Integer> provinceMap = new HashMap<String, Integer>();
    static {
        provinceMap.put("134",0);
        provinceMap.put("135",1);
        provinceMap.put("136",2);
        provinceMap.put("137",3);
        provinceMap.put("138",4);
    }
    // 这里就是实际分区方法 返回就是分区编号 分区编号就决定了数据到那个分区中 part-r-0000?
    public int getPartition(Text key, FlowBean flowBean, int numPartitions) {
        Integer code = provinceMap.get(key.toString().substring(0,3));
        if (code != null) {
            return code;
        }
        return 5;
    }
}
