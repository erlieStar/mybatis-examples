package com.javashitang.jdbc;

import org.junit.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author lilimin
 * @since 2021-12-13
 */
public class JDBCTest {

    @Test
    public void v1() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://myhost/test?useUnicode=true&characterEncoding=utf-8&useSSL=false", "test", "test");
        String sql = "select id, name, age from user_info";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String email = rs.getString(3);
            System.out.println(id + "," + name + "," + email);
        }
        connection.close();
    }

    @Test
    public void v2() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://myhost/test?useUnicode=true&characterEncoding=utf-8&useSSL=false", "test", "test");
        String sql = "insert user_info(name, age) values (?, ?)";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, "testName");
        pstmt.setInt(2, 10);
        pstmt.execute();
        connection.close();
    }

    @Test
    public void v3() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://myhost/test?useUnicode=true&characterEncoding=utf-8&useSSL=false", "root", "XpFNhfrEM6eIlRB1");
        // 插入5条记录
        CallableStatement cs = connection.prepareCall("call create_kf_use_arg(5)");
        cs.execute();
        // 获取id=5的记录
        cs = connection.prepareCall("call get_kf(5)");
        cs.execute();
        ResultSet rs = cs.getResultSet();
        while(rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String email = rs.getString(3);
            System.out.println(id + "," + name + "," + email);
        }
        connection.close();
    }
}
