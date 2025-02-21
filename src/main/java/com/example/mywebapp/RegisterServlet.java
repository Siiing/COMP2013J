package main.java.com.example.mywebapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Map<String, String> guestCredentials = InitUserInfo.getGuestCredentials();
        if(guestCredentials.get(username)==null){
            guestCredentials.put(username, password);
        }

        // Optionally, you can save this information to a file or database for persistence
        // Redirect to login page after registration
        response.sendRedirect("login.jsp");
    }
}
