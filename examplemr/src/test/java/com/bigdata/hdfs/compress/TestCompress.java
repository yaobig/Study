package com.bigdata.hdfs.compress;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.*;
import org.apache.hadoop.util.ReflectionUtils;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by daoyao
 * 测试压缩
 */
public class TestCompress {
    @Test
    public void deflateCompress()throws Exception {
        Class[] zipClasses = {
          //  DeflateCodec.class,
            GzipCodec.class,
           // BZip2Codec.class,
            Lz4Codec.class,
           // SnappyCodec.class,
        };
        for(Class c : zipClasses){
            unzip(c);
        }
    }
    // 解压缩
    private void unzip(Class codecClass)throws Exception{
        long start = System.currentTimeMillis();
        // 通过反射实例化对象         压缩的编解码器
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass,new Configuration());
        // 创建文件输出流, 得到默认扩展名
        FileInputStream fis = new FileInputStream("e:/BigTest/2019/大数据" + codec.getDefaultExtension());
        // 得到压缩流
        CompressionInputStream zipIn = codec.createInputStream(fis);
        IOUtils.copyBytes(zipIn,new FileOutputStream("e:/BigTest/2019/大数据" + ".mp4"),1024);
        zipIn.close();
        System.out.println(codecClass.getSimpleName() + " : " + (System.currentTimeMillis() - start));
    }
    // 压缩文件
    private void zip(Class codecClass)throws Exception{
        long start = System.currentTimeMillis();
        // 通过反射实例化对象         压缩的编解码器
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass,new Configuration());
        // 创建文件输出流, 得到默认扩展名
        FileOutputStream fos = new FileOutputStream("e:/BigTest/2019/大数据" + codec.getDefaultExtension());
        // 得到压缩流
        CompressionOutputStream zipOut = codec.createOutputStream(fos);
        IOUtils.copyBytes(new FileInputStream("e:/BigTest/2019/大数据.mp4"),zipOut,1024);
        zipOut.close();
        System.out.println(codecClass.getSimpleName() + " : " + (System.currentTimeMillis() - start));
    }
}
