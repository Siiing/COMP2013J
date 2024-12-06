package main.java.com.example.mywebapp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCTool {

    public static Connection getConnection(String url, String dbname, String username, String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://" + url + "/" + dbname + "?serverTimezone=UTC", username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static Connection getConnection() {
        return JDBCTool.getConnection("localhost:3306", "forbes", "root", "17803988816lsy");
    }

    // Method to retrieve data from the 'money' table
    public static List<String[]> getDataFromMoneyDatabase(String year,String like) {
        List<String[]> data = new ArrayList<>();
        Connection conn = getConnection();
        if (conn != null) {
            try (Statement stmt = conn.createStatement()) {
                //SELECT 列名 FROM 表1 FULL OUTER JOIN 表2 ON 连接条件;
                String query = "SELECT a.PID PID, EnglishName, ChineseName, Wealth FROM person a left JOIN result b ON a.PID = b.PID WHERE year=" + year;
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String PID = rs.getString("PID");
                    String englishName = rs.getString("EnglishName");
                    String chineseName = rs.getString("ChineseName");
                    String wealth = rs.getString("Wealth");
                    if(like == null){
                        data.add(new String[]{englishName, chineseName, wealth,PID});
                    }else{
                        if(englishName.contains(like)){
                            data.add(new String[]{englishName, chineseName, wealth,PID});
                        }
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static List<String[]> getDataFromRankDatabase(String year,String like) {
        List<String[]> data = new ArrayList<>();
        Connection conn = getConnection();
        if (conn != null) {
            try (Statement stmt = conn.createStatement()) {
                //SELECT 列名 FROM 表1 FULL OUTER JOIN 表2 ON 连接条件;
                String query = "SELECT a.PID PID, EnglishName, ChineseName, Ranks FROM person a left JOIN result b ON a.PID = b.PID WHERE year=" + year;
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String englishName = rs.getString("EnglishName");
                    String chineseName = rs.getString("ChineseName");
                    String rank = rs.getString("Ranks");
                    String PID = rs.getString("PID");

                    if(like == null){
                        data.add(new String[]{englishName, chineseName, rank,PID});
                    }else{
                        if(englishName.contains(like)){
                            data.add(new String[]{englishName, chineseName, rank,PID});
                        }
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static List<String[]> displayAllDataFromPersonTable(String like) {
        List<String[]> data = new ArrayList<>();
        Connection conn = getConnection();
        if (conn != null) {
            try (Statement stmt = conn.createStatement()) {
                String query = "SELECT * FROM person";
                if(like != null){
                    query = "SELECT * FROM person where EnglishName like '%"+like+"%' ";
                }
                ResultSet rs = stmt.executeQuery(query);
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Create an array to hold column names
                String[] columnNames = new String[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    columnNames[i - 1] = metaData.getColumnName(i);
                }
                data.add(columnNames); // Add column names to the list

                // Add data rows to the list
                while (rs.next()) {
                    String[] rowData = new String[columnCount];
                    for (int i = 1; i <= columnCount; i++) {
                        rowData[i - 1] = rs.getString(i);
                    }
                    data.add(rowData);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }


    public static List<String[]> getDataFromSourceDatabase(String year,String like) {
        List<String[]> data = new ArrayList<>();
        Connection conn = getConnection();
        if (conn != null) {
            try (Statement stmt = conn.createStatement()) {
                String query = "SELECT SourceOfWealth, Wealth" + year + " FROM wholesource";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String sourceOfWealth = rs.getString("SourceOfWealth");
                    String source = rs.getString("Wealth" + year);
                    if(like == null){
                        data.add(new String[]{sourceOfWealth, source});
                    }else{
                        if(sourceOfWealth.contains(like)){
                            data.add(new String[]{sourceOfWealth, source});
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static List<String[]> getDataFromYearDatabase() {
        List<String[]> data = new ArrayList<>();
        Connection conn = getConnection();
        if (conn != null) {
            try (Statement stmt = conn.createStatement()) {
                String query = "SELECT Year,Link,Date FROM year";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String Year = rs.getString("Year");
                    String Link = rs.getString("Link");
                    String Date = rs.getString("Date");

                    data.add(new String[]{Year, Link,Date});

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
    public static List<String[]> getDataFromResultDatabase() {
        List<String[]> data = new ArrayList<>();
        Connection conn = getConnection();
        if (conn != null) {
            try (Statement stmt = conn.createStatement()) {
                String query = "SELECT PID,Ranks,Wealth,year FROM result";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String PID=rs.getString("PID");
                    String Ranks=rs.getString("Ranks");
                    String Wealth=rs.getString("Wealth");
                    String year = rs.getString("year");

                    data.add(new String[]{PID, Ranks,Wealth,year});

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }


    public static List<String[]> getDataFromCountryTable() {
        List<String[]> data = new ArrayList<>();
        Connection conn = getConnection();
        if (conn != null) {
            try (Statement stmt = conn.createStatement()) {
                String query = "SELECT Country, Continent, Capital FROM country";
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String capital = rs.getString("Capital");
                    String continent = rs.getString("Continent");
                    String country = rs.getString("Country");

                    data.add(new String[]{country, continent, capital});
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}
