package com.ivan.learn.ck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 读取本地文件写入ck工具类
 *
 * @author 周虎
 * @since 2022-04-15
 */

public class ReadCsvToCK1 {

    public static void main(String[] args) {
        //读取本地文件写入clickhouse
        readCsvByBufferedReader("/Users/ivan/dev/applications/git/learn/learn-java-clickhouse/src/main/resources/d_mgr_store_info.txt", "click", "d_mgr_store_info");

    }

    /**
     * 从csv中读取数据写入ck中
     *
     * @param filePath  文件路径
     * @param dbName    数据库名
     * @param tableName 表名
     */
    public static void readCsvByBufferedReader(String filePath, String dbName, String tableName) {
        File csv = new File(filePath);
//        csv.setReadable(true);
//        csv.setWritable(true);
        InputStreamReader isr = null;
        BufferedReader br = null;
        Connection connection = null;
        try {
            isr = new InputStreamReader(new FileInputStream(csv), "UTF-8");
            br = new BufferedReader(isr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String line = "";
        int i = 1;
        int j = 0;
        try {
            StringBuffer sqlSb = new StringBuffer("INSERT INTO " + dbName + "." + tableName + " VALUES (");
            while ((line = br.readLine()) != null) {
                //去掉表头,拼接sql
                if (j == 0) {
                    String[] split = line.split("\t");
                    for (String s : split) {
                        sqlSb.append("?,");
                    }
                    break;
                }
            }
            String sql = sqlSb.substring(0, sqlSb.length() - 1) + ");";

            //写入sql
            connection = ClickhouseUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            System.out.println(sql);

            //读取数据
            while ((line = br.readLine()) != null) {
                // System.out.println(line);
                String[] split = line.split("\t");
                int index = 1;
                for (int k = 0; k < split.length; k++) {
                    if (index == 39) {
                        System.out.println(line);
                        System.out.println(split[k]);
                    }
                    preparedStatement.setObject(index, split[k].equals("NULL") ? "" : split[k]);
                    index += 1;
                }
                preparedStatement.addBatch();

                //分批写入
                if (i >= 4900) {
                    preparedStatement.executeBatch();
//                    connection.commit();
                    i = 1;
                    //Too many partitions for single INSERT block (more than 5000).
                    Thread.sleep(1000);
                }
                i++;
                j++;
            }
            preparedStatement.executeBatch();
//            connection.commit();
            System.out.println("csv表格读取行数：" + j);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }


}

