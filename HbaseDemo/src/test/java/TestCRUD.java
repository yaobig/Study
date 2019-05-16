import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;

/**
 * Created by daoyao on 2019/5/14 15:31
 */
public class TestCRUD {

    /**
     * 插入数十万条数据
     */
    @Test
    public void bigInsert() throws Exception {
        long start = System.currentTimeMillis() ;
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "master");
        Connection conn = ConnectionFactory.createConnection(conf);
        TableName tname = TableName.valueOf("ns1:t1");
        HTable table = (HTable)conn.getTable(tname);
        //不要自动清理缓冲区
        table.setAutoFlush(false);

        for(int i = 4 ; i < 1000000 ; i ++){
            Put put = new Put(Bytes.toBytes("row" + i)) ;
            //关闭写前日志
            put.setWriteToWAL(false);
            put.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("id"),Bytes.toBytes(i));
            put.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("name"),Bytes.toBytes("tom" + i));
            put.addColumn(Bytes.toBytes("f1"),Bytes.toBytes("age"),Bytes.toBytes(i % 100));
            table.put(put);

            if(i % 2000 == 0){
                table.flushCommits();
            }
        }
        //
        table.flushCommits();
        System.out.println(System.currentTimeMillis() - start );
    }



}
