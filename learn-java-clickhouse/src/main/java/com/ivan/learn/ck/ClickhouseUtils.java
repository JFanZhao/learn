package com.ivan.learn.ck;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * clickhouse 工具类
 * @author 周虎
 * @since 2022-04-15
 */
public class ClickhouseUtils {

    private static Connection connection = null;

    /**
     * 获取connection
     * @return
     */
    public static Connection getConnection(String host, int port, String database, String username, String pwd) throws ClassNotFoundException, SQLException {
        Class.forName("ru.yandex.clickhouse.ClickHouseDriver");
        String url = String.format("jdbc:clickhouse://%s:%d/%s", host, port, database);
        connection = DriverManager.getConnection(url, username, pwd);
        return connection;
    }

    /**
     * 获取connection
     * @return
     */
    public static Connection getConnection() {
       try {
           String host = "10.200.59.81";
           int port = 8123;
           String user = "admin";
           String password = "N#nIpms1";
           String database = "click";
           Class.forName("ru.yandex.clickhouse.ClickHouseDriver");
           String url = String.format("jdbc:clickhouse://%s:%d/%s", host, port, database);
           connection = DriverManager.getConnection(url, user, password);
       }catch (Exception e){
           e.printStackTrace();
       }
        return connection;
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO click.zh_ck_test VALUES (?);";
        PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
        preparedStatement.setString(1, "1");
        preparedStatement.addBatch();
        preparedStatement.executeBatch();
        long startTime = System.currentTimeMillis();
        int[] ints = preparedStatement.executeBatch();
        connection.commit();
        long endTime = System.currentTimeMillis();
        System.out.println("Insert with batch finished with：" + (endTime - startTime) + " -- sink data: " + ints.length);
    }
}