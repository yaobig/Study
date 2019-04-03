package com.bigdata.hadoop.Maxtemp.SecondarySort;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by daoyao on 2019/3/31 10:12
 */
public class ComboKey implements WritableComparable<ComboKey> {
    private int year;
    private int temp;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTemp() {
        return temp;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    /**
     * 对 key 进行比较实现
     */
    public int compareTo(ComboKey o) {
        System.out.println("ComboKey.compareTo" + o.toString());
        int y0 = o.getYear();
        int t0 = o.getTemp();
        // 年份相同 (升序)
        if (year == y0){
            // 气温降序
            return -(temp - t0);
        }else {
            return year - y0;
        }
    }

    /**
     * 串行化过程,序列化，将IntPair转化成使用流传送的二进制
     * @param out
     * @throws IOException
     */
    public void write(DataOutput out) throws IOException {
        //  年份
        out.writeInt(year);
        // 气温
        out.writeInt(temp);
    }

    // 反序列化,从流中的二进制转换成 IntPair
    public void readFields(DataInput in) throws IOException {
       year =  in.readInt();
       temp = in.readInt();
    }

    @Override
    public String toString() {
        return year + " : " + temp;
    }
}
