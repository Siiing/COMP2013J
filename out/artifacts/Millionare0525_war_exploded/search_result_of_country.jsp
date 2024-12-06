<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="main.java.com.example.mywebapp.JDBCTool" %>
<%@ page import="main.java.com.example.mywebapp.SearchServlet" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Result</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            display: flex;
            height: 100vh;
            overflow: hidden; /* Prevent body from having its own scroll */
        }
        .sidebar {
            width: 300px!important;
            background-color: #333;
            color: white;
            padding: 40px;
            box-sizing: border-box;
            height: 100%;
        }
        .sidebar h2 {
            font-size: 1.6em;
            margin-top: 0;
        }
        .sidebar a {
            color: white;
            text-decoration: none;
            display: block;
            margin: 15px 0;
            font-size: 1.1em;
            cursor: pointer;
        }
        .sidebar a:hover {
            text-decoration: underline;
        }
        .dropdown {
            display: none;
            background-color: #444;
            margin-top: 10px;
            padding: 10px;
            border-radius: 5px;
        }
        .dropdown a {
            color: white;
            text-decoration: none;
            display: block;
            margin: 5px 0;
        }
        .dropdown a:hover {
            text-decoration: underline;
        }
        .content {
            flex-grow: 1;
            display: flex;
            flex-direction: column;
            padding: 20px;
            box-sizing: border-box;
            overflow-y: scroll;
        }
        .search-container {
            display: flex;
            justify-content: center;
            padding: 10px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            box-sizing: border-box;
        }
        .search-container input[type="text"] {
            width: 100%;
            max-width: 800px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px 0 0 5px;
            box-sizing: border-box;
        }
        .search-container input[type="text"]::placeholder {
            color: #999;
            opacity: 0.8;
        }
        .search-container input[type="submit"] {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 0 5px 5px 0;
            cursor: pointer;
            box-sizing: border-box;
        }
        .search-container input[type="submit"]:hover {
            background-color: #45a049;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #333;
            color: white;
        }
        .alphabet-nav {
            position: fixed;
            top: 50%;
            right: 0;
            transform: translateY(-50%);
            background-color: #f0f0f0;
            padding: 10px;
        }
        .alphabet-nav a {
            display: block;
            padding: 5px;
            text-decoration: none;
            color: #333;
            font-weight: bold;
            cursor: pointer;
        }
        .alphabet-nav a:hover {
            background-color: #ddd;
        }
    </style>
</head>


<body>

<div class="sidebar">
    <h2>Categories</h2>
    <a href = "search.jsp"> MainMenu</a>

    <a href="#" id="money-link">Money</a>
    <div id="money-dropdown" class="dropdown">
        <a href="money.jsp?year=2018">2018</a>
        <a href="money.jsp?year=2019">2019</a>
        <a href="money.jsp?year=2020">2020</a>
        <a href="money.jsp?year=2021">2021</a>
        <a href="money.jsp?year=2022">2022</a>
    </div>

    <a href="#" id="rank-link">Rank</a>
    <div id="rank-dropdown" class="dropdown">
        <a href="rank.jsp?year=2018">2018</a>
        <a href="rank.jsp?year=2019">2019</a>
        <a href="rank.jsp?year=2020">2020</a>
        <a href="rank.jsp?year=2021">2021</a>
        <a href="rank.jsp?year=2022">2022</a>
    </div>

    <a href="person.jsp">Person</a>

    <a href="#" id="sourceOfWealth-link">Source of Wealth</a>
    <div id="sourceOfWealth-dropdown" class="dropdown">
        <a href="source.jsp?year=2018">2018</a>
        <a href="source.jsp?year=2019">2019</a>
        <a href="source.jsp?year=2020">2020</a>
        <a href="source.jsp?year=2021">2021</a>
        <a href="source.jsp?year=2022">2022</a>
    </div>
    <a href="Year.jsp">Year</a>
    <a href="Result.jsp">Result</a>
    <a href="country.jsp">Country</a>
</div>


<div class="content">
    <div class="search-container">
        <form action="search_result_of_country" method="get" style="display: flex; width: 100%;">
            <input type="text" id="query" name="query" required placeholder="Enter anything you want to search in country">
            <input type="submit" value="Search">
        </form>
    </div>
    <h2>Search Result in Country</h2>
    <div style="display: flex; justify-content: space-between;">
        <button style = "background-color: #4CAF50;color:white; border: #4CAF50;height: 30px" onclick="window.location.href='country.jsp'">Back to country</button>
        <button style = "background-color: #4CAF50;color:white; border: #4CAF50;height: 30px" onclick="window.location.href='search.jsp'">Back to main menu</button>
    </div>

    <table>
        <thead>
        <tr>
            <th>Country</th>
            <th>Continent</th>
            <th>Capital</th>
        </tr>
        </thead>

        <tbody>
        <%
            List<String[]> results = (List<String[]>) request.getAttribute("results");
            if (results != null && !results.isEmpty()) {
                // Sort the data by the English name
                Collections.sort(results, new Comparator<String[]>() {
                    @Override
                    public int compare(String[] a, String[] b) {
                        return a[0].compareToIgnoreCase(b[0]);
                    }
                });

                char currentInitial = ' ';
                for (String[] row : results) {
                    char initial = row[0].toUpperCase().charAt(0);
                    if (initial != currentInitial) {
                        currentInitial = initial;
        %>
        <tr id="<%= initial %>">
            <td colspan="4" style="background-color: #eee; font-weight: bold;"><%= initial %></td>
        </tr>
        <%
            }
        %>
        <tr>
            <td><%= row[0] %></td>
            <td><%= row[1] %></td>
            <td><%= row[2] %></td>
        </tr>
        <%
            }
        %>

        <%
        } else {
        %>
        <tr>
            <td>No results found</td>
        </tr>
        <%
            }
        %>



        </tbody>

    </table>
</div>


<script>
    // Get the money, rank, and source of wealth links and dropdowns
    var moneyLink = document.getElementById("money-link");
    var moneyDropdown = document.getElementById("money-dropdown");
    var rankLink = document.getElementById("rank-link");
    var rankDropdown = document.getElementById("rank-dropdown");
    var sourceOfWealthLink = document.getElementById("sourceOfWealth-link");
    var sourceOfWealthDropdown = document.getElementById("sourceOfWealth-dropdown");

    // Toggle the display of the dropdown when the money link is clicked
    moneyLink.onclick = function() {
        if (moneyDropdown.style.display === "none" || moneyDropdown.style.display === "") {
            moneyDropdown.style.display = "block";
        } else {
            moneyDropdown.style.display = "none";
        }
    }

    // Toggle the display of the dropdown when the rank link is clicked
    rankLink.onclick = function() {
        if (rankDropdown.style.display === "none" || rankDropdown.style.display === "") {
            rankDropdown.style.display = "block";
        } else {
            rankDropdown.style.display = "none";
        }
    }

    // Toggle the display of the dropdown when the sourceOfWealth link is clicked
    sourceOfWealthLink.onclick = function() {
        if (sourceOfWealthDropdown.style.display === "none" || sourceOfWealthDropdown.style.display === "") {
            sourceOfWealthDropdown.style.display = "block";
        } else {
            sourceOfWealthDropdown.style.display = "none";
        }
    }
</script>
</body>
</html>