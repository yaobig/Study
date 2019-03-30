package com.bigdata.hadoop.mr;


import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by daoyao
 */
public class Util {

    public static String getInFo(Object o,String msg){
        return getHostName() + ":" + getPID() + ":" + getTID() + ":" + getObjInfo(o) + ":" + msg;
    }

    /**
     * 得到主机名
     */
    public static String getHostName(){
        try {
            return  InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return  null;
    }

    /**
     * 获得当前进程所在程序 id
     */
    public static int getPID(){
        try {
            String info = ManagementFactory.getRuntimeMXBean().getName();
            return Integer.parseInt(info.substring(0,info.indexOf("@")));
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获得当前线程 tid
     */
    public static String getTID(){
        try {
            return Thread.currentThread().getName();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得 对象信息
     *
     */
    public static String getObjInfo(Object o){
        try {
            String sname = o.getClass().getSimpleName();
            return sname + "@" + o.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
