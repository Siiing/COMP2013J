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

public class SearchServletOfSource extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("query");
        List<String[]> results = search(keyword);

        request.setAttribute("results", results);

        request.getRequestDispatcher("search_result_of_source.jsp").forward(request, response);
    }

    private List<String[]> search(String keyword) {
        List<String[]> results = new ArrayList<>();

        try {
            Connection connection = main.java.com.example.mywebapp.JDBCTool.getConnection();

            String sql = "SELECT * FROM wholesource WHERE SourceOfWealth LIKE ? OR Wealth2018 LIKE ? OR Wealth2019 LIKE ? OR Wealth2020 LIKE ? OR Wealth2021 LIKE ? OR Wealth2022 LIKE ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + keyword + "%");
            preparedStatement.setString(2, "%" + keyword + "%");
            preparedStatement.setString(3, "%" + keyword + "%");
            preparedStatement.setString(4, "%" + keyword + "%");
            preparedStatement.setString(5, "%" + keyword + "%");
            preparedStatement.setString(6, "%" + keyword + "%");

            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                String sourceOfWealth = resultSet.getString("SourceOfWealth");
                String wealth2018 = resultSet.getString("Wealth2018");
                String wealth2019 = resultSet.getString("Wealth2019");
                String wealth2020 = resultSet.getString("Wealth2020");
                String wealth2021 = resultSet.getString("Wealth2021");
                String wealth2022 = resultSet.getString("Wealth2022");

                String[] row = {sourceOfWealth, wealth2018, wealth2019, wealth2020, wealth2021, wealth2022};
                results.add(row);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }
}
