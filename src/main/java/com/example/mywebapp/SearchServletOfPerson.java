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

public class SearchServletOfPerson extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("query");
        List<String[]> results = search(keyword);

        request.setAttribute("results", results);

        request.getRequestDispatcher("search_result_of_person.jsp").forward(request, response);
    }

    private List<String[]> search(String keyword) {
        List<String[]> results = new ArrayList<>();

        try {
            Connection connection = main.java.com.example.mywebapp.JDBCTool.getConnection();

            String sql = "SELECT * FROM person WHERE EnglishName LIKE ? OR ChineseName LIKE ? OR Country LIKE ? OR Food LIKE ? OR Source LIKE ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            preparedStatement.setString(3, "%" + keyword + "%");
            preparedStatement.setString(4, "%" + keyword + "%");
            preparedStatement.setString(5, "%" + keyword + "%");

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                String engName = resultSet.getString("EnglishName");
                String chiName = resultSet.getString("ChineseName");
                String country = resultSet.getString("Country");
                String food = resultSet.getString("Food");
                String source = resultSet.getString("Source");

                String[] row = {engName, chiName, country, food, source};
                results.add(row);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
}