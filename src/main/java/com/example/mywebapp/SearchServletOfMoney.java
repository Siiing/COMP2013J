package main.java.com.example.mywebapp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SearchServletOfMoney extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("query");
        List<String[]> results = search(keyword);

        request.setAttribute("results", results);

        request.getRequestDispatcher("search_result_of_money.jsp").forward(request, response);
    }

    private List<String[]> search(String keyword) {
        List<String[]> results = new ArrayList<>();

        try {
            Connection connection = main.java.com.example.mywebapp.JDBCTool.getConnection();

            String sql = "SELECT a.EnglishName, a.ChineseName, b.Wealth, b.year FROM person a left JOIN result b ON a.PID = b.PID" +
                    " WHERE EnglishName LIKE ? OR ChineseName LIKE ? " +
                    "OR year LIKE ? " +
                    "OR Wealth LIKE ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            preparedStatement.setString(3, "%" + keyword + "%");
            preparedStatement.setString(4, "%" + keyword + "%");
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                String engName = resultSet.getString("EnglishName");
                String chiName = resultSet.getString("ChineseName");
                String year = resultSet.getString("Year");
                String wealth = resultSet.getString("Wealth");

                String[] row = {engName, chiName, year, wealth};
                results.add(row);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
}