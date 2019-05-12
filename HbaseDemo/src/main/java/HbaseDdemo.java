import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.commons.io.IOUtils;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by daoyao on 2019/5/9 14:22
 */
public class HbaseDdemo {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private static Admin admin;

    private static Connection connection;

    @Test
    public static void main(String[] args) {
        // 连接数据库
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "master");
        try {
            connection = ConnectionFactory.createConnection(conf);
            admin = connection.getAdmin();
            HbaseDdemo hbaseDdemo = new HbaseDdemo();
            // 创建表
//            hbaseDdemo.createTable("job_internet", "RAW_DATA,TAG_DATA,PERCEPT_DATA");
//            hbaseDdemo.createTable("job_cloud", "cloud");
            // 插入数据
//            Map<String,Object> map = new HashMap<String, Object>();
//            map.put("id","1");
//            map.put("jobname","云计算");
//            hbaseDdemo.add("job_internet",map);

            // 查询
            hbaseDdemo.get("ns1:t1","f1","JOB_NAME");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建表
     */
    public void createTable(String tableName, String families) throws IOException {
        TableName table = TableName.valueOf(tableName);
        if (admin.tableExists(table)) {
            logger.info("表已存在");
        } else {
            try {
                admin = connection.getAdmin();
                HTableDescriptor descriptor = new HTableDescriptor(table);
                String[] family = families.split(",");
                for (String str : family) {
                    descriptor.addFamily(new HColumnDescriptor(str.getBytes()));
                }
                admin.createTable(descriptor);
                logger.info("创建表成功");
            } finally {
                IOUtils.closeQuietly(admin);
            }
        }

    }


    /**
     * 添加数据
     */
    public void add(String tableName,Map<String,Object> map)throws IOException{
        Table table = null;
        try {
            table = connection.getTable(TableName.valueOf("ns1:t1"));
            Put put = new Put(Bytes.toBytes(2));
            put.addColumn(Bytes.toBytes("f1"), Bytes.toBytes("JOB_NAME"),Bytes.toBytes("云计算"));
        }finally {
            IOUtils.closeQuietly(table);
        }
    }

    /**
     * 获取数据
     */
    public void get(String tableName,String family,String qualifier)throws IOException{
        Table table = connection.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes("1"));
        Result r = table.get(get);
        byte[] value = r.getValue(Bytes.toBytes(family), Bytes.toBytes(qualifier));
        System.out.println(Bytes.toString(value));
    }
}
