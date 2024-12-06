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



public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String loginType = request.getParameter("loginType");

        HttpSession session = request.getSession();

        if ("admin".equals(loginType)) {
            // check admin
            Map<String, String> adminCredentials = InitUserInfo.getAdminCredentials();
            if (adminCredentials.containsKey(username) && adminCredentials.get(username).equals(password)) {
                request.getSession().setAttribute("user", username);
                request.getSession().setAttribute("isAdmin", true);
                response.sendRedirect("search.jsp");
            } else {
                response.sendRedirect("login.jsp?error=true");
            }
        } else if ("guest".equals(loginType)) {
            // check guest
            Map<String, String> guestCredentials = InitUserInfo.getGuestCredentials();

            if (guestCredentials.containsKey(username) && guestCredentials.get(username).equals(password)) {
                request.getSession().setAttribute("user", username);
                request.getSession().setAttribute("isAdmin", false);
                response.sendRedirect("search.jsp");
            } else {
                response.sendRedirect("login.jsp?error=true");
            }
        } else {
            response.sendRedirect("login.jsp?error=true");
        }
    }
}
