package com.bigdata.hivedemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 使用 jdbc 连接 hive 数据库仓库， 数据仓库需要开启 hiveserver2 服务
 */
public class App {
    public static void main(String[] args) throws  Exception{
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        Connection conn = DriverManager.getConnection("jdbc:hive2://192.168.1.149:10000/mydb2");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select id , name ,age from t");
        while(rs.next()){
            System.out.println(rs.getInt(1) + "," + rs.getString(2)) ;
        }
        rs.close();
        st.close();
        conn.close();
    }
}
