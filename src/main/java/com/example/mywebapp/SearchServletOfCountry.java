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
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchServletOfCountry extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SearchServletOfCountry.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("query");

        List<String[]> results = search(keyword);
        request.setAttribute("results", results);

        request.getRequestDispatcher("search_result_of_country.jsp").forward(request, response);
    }

    private List<String[]> search(String keyword) {
        List<String[]> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = main.java.com.example.mywebapp.JDBCTool.getConnection();

            String sql = "SELECT country.* FROM country WHERE Country LIKE ? OR Continent LIKE ? OR Capital LIKE ?;";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            preparedStatement.setString(3, "%" + keyword + "%");

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String country = resultSet.getString("Country");
                String continent = resultSet.getString("Continent");
                String capital = resultSet.getString("Capital");

                String[] row = { country, continent, capital};
                results.add(row);
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Database access error", e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                LOGGER.log(Level.SEVERE, "Error closing resources", e);
            }
        }

        return results;
    }
}
