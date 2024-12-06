<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="main.java.com.example.mywebapp.money" %>
<%@ page import="main.java.com.example.mywebapp.moneyDAO" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="main.java.com.example.mywebapp.rankDAO" %>
<%@ page import="main.java.com.example.mywebapp.personDAO" %>
<!DOCTYPE html>

<html>
<head>
  <meta charset="UTF-8">
  <title>Update Record</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f0f0f0;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
    }
    .update-form {
      background-color: #fff;
      padding: 20px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      width: 400px;
      box-sizing: border-box;
    }
    .update-form h2 {
      margin-top: 0;
    }
    .update-form label {
      display: block;
      margin: 10px 0 5px;
    }
    .update-form input[type="text"], .update-form input[type="number"] {
      width: 100%;
      padding: 10px;
      box-sizing: border-box;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
    .update-form input[type="submit"] {
      margin-top: 20px;
      padding: 10px 20px;
      background-color: #4CAF50;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      box-sizing: border-box;
    }
    .update-form input[type="submit"]:hover {
      background-color: #45a049;
    }
  </style>
</head>
<body>
  <%

  HttpSession session2 = request.getSession(false);
    String contextPath = session2.getServletContext().getContextPath();
    Boolean isAdmin = (Boolean) session2.getAttribute("isAdmin");
  if (isAdmin == null || !isAdmin) {
    response.sendRedirect("access_denied.jsp");
    return;
  }


  String name = request.getParameter("name");
  String chineseName = request.getParameter("chineseName");
  String food = request.getParameter("food");
  String country = request.getParameter("country");
  String source = request.getParameter("source");
  String pid = request.getParameter("pid");

%>
<div class="update-form">
  <h2>Update Record</h2>
  <form method="post" action="<%=contextPath%>/person">
    <input type="text" hidden name="type"  value="update" >
    <input type="text" hidden name="pid"  value="<%=pid %>">
    <label for="newEnglishName">English Name</label>
    <input type="text" id="newEnglishName" name="newEnglishName" value="<%= name %>" disabled>
    <label for="newChineseName">Chinese Name</label>
    <input type="text" id="newChineseName" name="newChineseName" value="<%= chineseName %>" disabled>
    <label for="country">country</label>
    <input type="text" id="country" name="newCountry" value="<%= country %>" required>
    <label for="food">food</label>
    <input type="text" id="food" name="newFood" value="<%= food %>" required>
    <label for="Source">Source</label>
    <input type="text" id="Source" name="newSource"  value="<%= source %>" required>
    <input type="submit" value="Update">
  </form>

</div>
</body>
</html>