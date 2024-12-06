<%@ page contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8" language="java" %>
<%@ page import="main.java.com.example.mywebapp.money" %>
<%@ page import="main.java.com.example.mywebapp.moneyDAO" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="main.java.com.example.mywebapp.person" %>
<!DOCTYPE html>

<html>
<head>
  <meta charset="UTF-8">
  <title>Add Record</title>
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
    .add-form {
      background-color: #fff;
      padding: 20px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      border-radius: 5px;
      width: 400px;
      box-sizing: border-box;
      height: 700px;
      overflow: auto;
    }
    .add-form h2 {
      margin-top: 0;
    }
    .add-form label {
      display: block;
      margin: 10px 0 5px;
    }
    .add-form input[type="text"], .add-form input[type="number"] {
      width: 100%;
      padding: 10px;
      box-sizing: border-box;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
    .add-form input[type="submit"] {
      margin-top: 20px;
      padding: 10px 20px;
      background-color: #4CAF50;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      box-sizing: border-box;
    }
    .add-form input[type="submit"]:hover {
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

  String year = request.getParameter("year");


%>
<div class="add-form">
  <h2>Add Record</h2>
  <form method="post"  action="<%= contextPath %>/source">
    <input type="text" hidden  name="type"  value="add"  >
    <input type="text" hidden  name="year"  value="<%= year %>"  >

    <label for="name">English Name</label>
    <input type="text" id="name" name="name"  required>
    <label for="chineseName">Chinese Name</label>
    <input type="text" id="chineseName" name="chineseName"  required>
    <label for="country">country</label>
    <input type="text" id="country" name="country"  required>
    <label for="food">food</label>
    <input type="text" id="food" name="food"  required>

    <label for="source">Source of Wealth</label>
    <input type="text" id="source" name="source"  required>

    <label for="money2018">Money/2018</label>
    <input type="number" id="money2018" name="money2018"  required>

    <label for="money2019">Money/2019</label>
    <input type="number" id="money2019" name="money2019"  required>

    <label for="money2020">Money/2020</label>
    <input type="number" id="money2020" name="money2020"  required>

    <label for="money2021">Money/2021</label>
    <input type="number" id="money2021" name="money2021"  required>

    <label for="money2022">Money/2022</label>
    <input type="number" id="money2022" name="money2022"  required>


    <label for="rank2018">rank/2018</label>
    <input type="number" id="rank2018" name="rank2018"  required>

    <label for="rank2019">rank/2019</label>
    <input type="number" id="rank2019" name="rank2019"  required>

    <label for="rank2020">rank/2020</label>
    <input type="number" id="rank2020" name="rank2020"  required>

    <label for="rank2021">rank/2021</label>
    <input type="number" id="rank2021" name="rank2021"  required>

    <label for="rank2022">rank/2022</label>
    <input type="number" id="rank2022" name="rank2022"  required>

    <input type="submit" value="Add">
  </form>
</div>
</body>
</html>