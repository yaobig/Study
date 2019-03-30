package com.bigdata.hdfs.SeqFile;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.GzipCodec;
import org.junit.Test;

/**
 * Created by daoyao
 * 序列文件
 * e:/BigTest/1.seq
 */
public class TestSeqFile {
    /**
     * 写操作
     */
    @Test
    public void save() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        FileSystem fs = FileSystem.get(conf);
        Path p = new Path("e:/BigTest/1.seq") ;
        SequenceFile.Writer writer = SequenceFile.createWriter(fs, conf,p, IntWritable.class, Text.class);
        for(int i = 0 ; i < 10 ; i ++){
            writer.append(new IntWritable(i),new Text("tom" + i));
            //添加一个同步点
            writer.sync();
        }
        for(int i = 0 ; i < 10 ; i ++){
            writer.append(new IntWritable(i),new Text("tom" + i));
            if(i % 2 == 0){
                writer.sync();
            }
        }
        writer.close();
    }

    /**
     * 写操作
     */
    @Test
    public void zipGzip() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        FileSystem fs = FileSystem.get(conf);
        Path p = new Path("e:/BigTest/1.seq") ;
        SequenceFile.Writer writer = SequenceFile.createWriter(fs,
                conf,
                p,
                IntWritable.class,
                Text.class,
                SequenceFile.CompressionType.BLOCK,
                new GzipCodec());
        for(int i = 0 ; i < 10 ; i ++){
            writer.append(new IntWritable(i),new Text("tom" + i));
            //添加一个同步点
            writer.sync();
        }
        for(int i = 0 ; i < 10 ; i ++){
            writer.append(new IntWritable(i),new Text("tom" + i));
            if(i % 2 == 0){
                writer.sync();
            }
        }
        writer.close();
    }

    /**
     * 读操作,循环输出所有key-value
     */
    @Test
    public void read() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        FileSystem fs = FileSystem.get(conf);
        Path p = new Path("e:/BigTest/1.seq") ;
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, p , conf);

        IntWritable key = new IntWritable();
        Text value = new Text() ;
        while(reader.next(key,value)){
            System.out.println(key.get() + " : " + value.toString());
        }
        reader.close();
    }

    /**
     * 读操作,得到当前value
     */
    @Test
    public void read2() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        FileSystem fs = FileSystem.get(conf);
        Path p = new Path("e:/BigTest/1.seq") ;
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, p , conf);

        IntWritable key = new IntWritable();
        Text value = new Text() ;
        while(reader.next(key)){
            reader.getCurrentValue(value);
            System.out.println(value.toString());
        }
        reader.close();
    }

    /**
     * 读操作
     */
    @Test
    public void read3() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        FileSystem fs = FileSystem.get(conf);
        Path p = new Path("e:/BigTest/1.seq") ;
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, p , conf);
        IntWritable key = new IntWritable();
        Text value = new Text() ;
        reader.seek(288);

        reader.next(key,value);
        System.out.println(value.toString());
        reader.close();
    }

    /**
     *
     * 操纵同步点
     */
    @Test
    public void read4() throws Exception {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS","file:///");
        FileSystem fs = FileSystem.get(conf);
        Path p = new Path("e:/BigTest/1.seq") ;
        SequenceFile.Reader reader = new SequenceFile.Reader(fs, p , conf);
        IntWritable key = new IntWritable();
        Text value = new Text() ;

        //
        reader.sync(648);
        while(reader.next(key,value)){
            System.out.println(reader.getPosition() + "   " + key.get() + "-" + value.toString());
        }
        reader.close();
    }
}
