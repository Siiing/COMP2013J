<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="main.java.com.example.mywebapp.JDBCTool" %>
<%@ page import="javax.servlet.http.HttpSession" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Search Page</title>
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
            width: 755px !important;
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

        .content {
            flex-grow: 1;
            display: flex;
            flex-direction: column;
            padding: 20px;
            box-sizing: border-box;
            overflow-y: scroll;
            /* Add vertical scroll to content */
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

        .banner-container{  /* the size of img container and the roll setting*/
            background-color: transparent;
            box-sizing: border-box;
            width: 800px;
            height: 400px;
            justify-content: center;
            margin: 1rem auto;
            overflow: hidden;
            position: relative;
            flex-shrink: 0;
        }

        .banner-img-container{
            display: flex;
            height: 100%;
            animation: run 30s infinite;
        }

        .banner-container .banner-img-container img { /* the size of imgs*/
            width: 3200px;
            height: 400px;
            margin-right: 250px;
        }


        @keyframes run {
            0% { transform: translateX(7%); }
            10% { transform: translateX(7%); }
            43% { transform: translateX(-113%); }
            76% { transform: translateX(-236%); }
            100% { transform: translateX(7%); }
        }



        .FunctionIntroduction-content {
            margin-top: 20px;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .Forbes-content {
            margin-top: 20px;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .forbeseg-image{
            width: 100%;
            height: auto;
        }

        .AboutUs-content {
            margin-top: 20px;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        /* Dropdown menu styles */
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

    </style>
</head>
<body>
<%
    HttpSession session0 = request.getSession(false);
    Boolean isAdmin = (Boolean) session0.getAttribute("isAdmin");
    if (isAdmin == null) {
        isAdmin = false;
    }
%>

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

    <br><br>
    <a href = "login.jsp"> Logout</a>
</div>
<div class="content">
    <div class="search-container">
        <form action="search" method="get" style="display: flex; width: 100%;">
            <input type="text" id="query" name="query" required placeholder="Enter your search query">
            <input type="submit" value="Search">
        </form>
    </div>


    <div class="banner-container">
        <div class="banner-img-container">
            <img src=".img/FunctionIntroduction.jpg" alt="Function Introduction" id="img1">
            <img src=".img/Forbes.png" alt="Forbes" id="img2">
            <img src=".img/AboutUs.jpg" alt="About Us" id="img3">
        </div>
    </div>


    <div class="FunctionIntroduction-content" id = "FunctionIntroduction">
        <p style="line-height: 1.5;font-size: 24px;">  Here is the Function Introduction content which is used to introduce about our web :P</p>
        <p style="line-height: 1.5;font-size: 20px;">  In the main menu page, it just includes the function introduction part, data source part and our team information. The information of databases is shown on the left side.</p>
        <p style="line-height: 1.5;font-size: 20px;">  As you can see, in the top we have a search helper, and in different pages, the results of search are different. For example, when you search "Jack Ma" in the main menu window, you will see all the information in the database about him, while you search in "Money-2018" chart, you will only see the information about how much he profited in 2018.</p>
        <p style="line-height: 1.5;font-size: 20px;">  On the left side, you can see eight part: MainMenu(help you back to this window when you want), Money(including 2018-2022), Rank(including 2018-2022), Person(including the information about their nation and their favorite food), Source of Wealth(the millionaires made their fortune in what ways, and the whole wealth one way gained), Year(including the five years, their source web link, and their publish date), Result(including their pid instead of name, according to data in "People" and "Source of Wealth" and linked to "Year"), Country(including all the millionaires' countries, related continents and capitals). Some of them are divided by years and you can click each one to see the details.</p>
        <p style="line-height: 1.5;font-size: 20px;">  The login interface has three login patterns: when you log in as guests or use register, you are just allowed to read the web. But if you log in as admins, you are able to edit related charts such as adding, deleting and modifying.</p>
    </div>

    <div class="Forbes-content" id="Forbes">
        <p style="line-height: 1.5;font-size: 24px;">  Here is the source of our data :P</p>
        <p style="line-height: 1.5;font-size: 20px;">  The data(millionaires, wealth, source of wealth rank) was collected from "ForbesChina"(<a href="https://www.forbeschina.com/lists">https://www.forbeschina.com/lists</a>), the specific link of the five years will be shown at the bottom of the content. But some information was made up, such as their favorite food and some people's name(due to some tiny difference like "Abdulla" and "Abdullah").</p>
        <p style="line-height: 1.5;font-size: 20px;">  The chart of the millionaires in five years(you can click related link to turn to the web):</p>
        <p style="line-height: 1.5;font-size: 20px;">  2018: <a href="https://www.forbeschina.com/lists/1151">https://www.forbeschina.com/lists/1151</a></p>
        <p style="line-height: 1.5;font-size: 20px;">  2019: <a href="https://www.forbeschina.com/lists/21">https://www.forbeschina.com/lists/21</a></p>
        <p style="line-height: 1.5;font-size: 20px;">  2020: <a href="https://www.forbeschina.com/lists/1733">https://www.forbeschina.com/lists/1733</a></p>
        <p style="line-height: 1.5;font-size: 20px;">  2021: <a href="https://www.forbeschina.com/lists/1 The left sidebar is about our tables, and this part later will be explained in detail by the next member Shen Jinyan.757">https://www.forbeschina.com/lists/1757</a></p>
        <p style="line-height: 1.5;font-size: 20px;">  2022: <a href="https://www.forbeschina.com/lists/1829">https://www.forbeschina.com/lists/1829</a></p>
        <br><br>
        <p style="line-height: 1.5;font-size: 20px;">  Below is a brief introduction of Forbes Rank List (From "WIKIPEDIA-Forbes" <a href="https://en.wikipedia.org/wiki/Forbes">https://en.wikipedia.org/wiki/Forbes</a></p>
        <p  style="line-height: 1.5;font-size: 20px; font-family: 'Times New Roman', sans-serif;">
            <em>Forbes is an American business magazine founded by B. C. Forbes in 1917 and owned by Hong Kong-based investment group Integrated Whale Media Investments since 2014. Its chairperson and editor-in-chief is Steve Forbes, and its CEO is Mike Federle. It is based in Jersey City, New Jersey. Competitors in the national business magazine category include Fortune and Bloomberg Businessweek.</em></p>
        <p  style="line-height: 1.5;font-size: 20px; font-family: 'Times New Roman', sans-serif;">
            <em>Published eight times a year, Forbes features articles on finance, industry, investing, and marketing topics. It also reports on related subjects such as technology, communications, science, politics, and law. It has an international edition in Asia as well as editions produced under license in 27 countries and regions worldwide. The magazine is known for its lists and rankings, including of the richest Americans (the Forbes 400), the 600 most notable young people under the age of 30 (Forbes 30 under 30), America's Wealthiest Celebrities, the world's top companies (the Forbes Global 2000), Forbes list of the World's Most Powerful People, and The World's Billionaires. The motto of Forbes magazine is "Change the World".</em></p>
        <img src=".img/forbeseg.png" alt="forbeseg" class="forbeseg-image">
    </div>


    <div class="AboutUs-content" id="AboutUs">
        <p style="line-height: 1.5;font-size: 24px;">  Here is the introduction of the members of our groups :D</p>
        <p style="line-height: 1.5;font-size: 20px;">  An Ran&nbsp;&nbsp;&nbsp;&nbsp;UCD:22207228&nbsp;&nbsp;&nbsp;BDIC: 22372122</p>
        <p style="line-height: 1.5;font-size: 20px;">  Shen Jinyan&nbsp;&nbsp;&nbsp;&nbsp;UCD:22207230&nbsp;&nbsp;&nbsp;BDIC: 22372124 </p>
        <p style="line-height: 1.5;font-size: 20px;">  Zhu Qiyue&nbsp;&nbsp;&nbsp;&nbsp;UCD:22207232&nbsp;&nbsp;&nbsp;BDIC:22372126</p>
        <p style="line-height: 1.5;font-size: 20px;">  Li Siying&nbsp;&nbsp;&nbsp;&nbsp;UCD:22207236&nbsp;&nbsp;&nbsp;BDIC:22372130</p>
    </div>
</div>


<script>
    //if click imgs, it will scroll to related text content
    document.getElementById('img1').onclick = function() {
        document.getElementById('FunctionIntroduction').scrollIntoView({ behavior: 'smooth' });
    };

    document.getElementById('img2').onclick = function() {
        document.getElementById('Forbes').scrollIntoView({ behavior: 'smooth' });
    };

    document.getElementById('img3').onclick = function() {
        document.getElementById('AboutUs').scrollIntoView({ behavior: 'smooth' });
    };

    // Get the money and rank links and dropdowns
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
            sourceOfWealthLink.style.display = "none";
        }
    }
</script>
</body>
</html>
