package com.bigdata.hdfs;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.FileInputStream;

/**
 * Created by daoyao
 */
public class TestHDFSClient {
    public static void main(String[] args) throws Exception{

        Configuration conf = new Configuration();

        /**
         *   注意 hdfs 的默认端口号是 8020
         * 这里指定使用的是 hdfs 文件系统
         *
         */
        conf.set("fs.defaultFS","hdfs://master:8020");
        /**
         *
         * 通过这种方式设置 java 客户端访问 hdfs 身份
         * 或者使用 FileSystem fs = FileSystem.get(new URL("hdfs://master:8020"), conf , "root");
         */
        System.setProperty("HADOOP_USER_NAME","root");

        FileSystem fs = FileSystem.get(conf);
        // 在 hdfs 中创建文件
       // fs.create(new Path("/helloByJava"));

        // 下载 hdfs 中的文件到 Windows 目录下s
        //fs.copyToLocalFile(new Path("/user/douxue/insight/job/crawler/54185480.html"), new Path("E:\\"));

        // 上传文件到 hdfs 中
        //fs.copyFromLocalFile(new Path("E:\\TextJava.txt"),new Path("/"));

        /**
         *  使用 Stream 的形式， 操作 HDFS 更底层的方式
         */
        FSDataOutputStream outputStream = fs.create(new Path("/1.txt"),true);
        FileInputStream inputStream = new FileInputStream("E:\\1.txt");
        IOUtils.copy(inputStream,outputStream);
        fs.close();
    }
}
