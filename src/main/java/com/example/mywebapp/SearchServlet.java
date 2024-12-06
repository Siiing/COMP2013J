package main.java.com.example.mywebapp;


// SearchServlet.java

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
import java.util.stream.Collectors;

public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("query");
        List<String[]> results = search(keyword);

        request.setAttribute("results", results);

        request.getRequestDispatcher("search_result.jsp").forward(request, response);
    }

    private List<String[]> search(String keyword) {
        List<String[]> results = new ArrayList<>();

        String sql = "SELECT person.*, result.Ranks, result.year, result.Wealth FROM result " +
                "JOIN person ON result.PID = person.PID " +
                "WHERE result.Ranks LIKE ? " +
                "OR result.Wealth LIKE ? " +
                "OR result.year LIKE ? " +
                "OR person.PID LIKE ? " +
                "OR person.ChineseName LIKE ? " +
                "OR person.EnglishName LIKE ? " +
                "OR person.Country LIKE ? " +
                "OR person.Food LIKE ? " +
                "OR person.Source LIKE ?;";

        try (Connection connection = main.java.com.example.mywebapp.JDBCTool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            for (int i = 1; i <= 9; i++) {
                preparedStatement.setString(i, "%" + keyword + "%");
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String rank = resultSet.getString("Ranks");
                    String wealth = resultSet.getString("Wealth");
                    String year = resultSet.getString("year");
                    String pid = resultSet.getString("PID");
                    String engName = resultSet.getString("EnglishName");
                    String chiName = resultSet.getString("ChineseName");
                    String country = resultSet.getString("Country");
                    String food = resultSet.getString("Food");
                    String source = resultSet.getString("Source");

                    String[] row = {pid, engName, chiName, country, food, source, rank, wealth, year};
                    results.add(row);
                }
            }
        } catch (Exception e) {
            // Consider using a logging framework instead of printStackTrace
            e.printStackTrace();
        }

        // Remove duplicates
        return results.stream().distinct().collect(Collectors.toList());
    }
}
