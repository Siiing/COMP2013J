package main.java.com.example.mywebapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAccessor {

    static final String DB_URL = "jdbc:mysql://localhost/";

    static final String USER = "root";
    static final String PASS = "17803988816lsy";

    public List<String[]> getDataFromDatabase() {
        Connection conn = null;
        Statement stmt = null;
        List<String[]> data = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT EnglishName, ChineseName, Wealth2018 FROM forbes"; // 假设你的表名是forbes
            ResultSet rs = stmt.executeQuery(sql);


            while (rs.next()) {
                String englishName = rs.getString("EnglishName");
                String chineseName = rs.getString("ChineseName");
                String wealth2018 = rs.getString("Wealth_2018");

                String[] row = {englishName, chineseName, wealth2018};
                data.add(row);
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return data;
    }
}
