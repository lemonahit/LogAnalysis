package com.imooc.utils;

import java.sql.*;

/**
 * 操作MySQL的工具类
 */
public class MySQLUtils {

    private static final String USERNAME = "root";

    private static final String PASSWORD = "zhaotao88016476";

    private static final String DRIVERCLASS = "com.mysql.jdbc.Driver";

    private static final String URL = "jdbc:mysql://localhost:3306/imooc_project";

    /**
     * 获取数据库连接
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVERCLASS);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    /**
     * 释放资源
     */
    public static void release(Connection connection, PreparedStatement psmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (psmt != null) {
            try {
                psmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //测试是否能够获取连接
        System.out.println(getConnection());
    }

}
