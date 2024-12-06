<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            background-image: url('./imgs/background.jpg');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
        }

        .login-container {
            border: 2px solid #ccc;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;

            background-color: rgba(255, 255, 255, 0.9);
        }

        .login-container h2 {
            margin-top: 0;
        }
        .login-container label {
            font-size: 1.3em;
        }
        .login-container input[type="text"],
        .login-container input[type="password"] {
            font-size: 1em;
            width: 80%;
            padding: 10px;
            margin: 10px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .login-container input[type="submit"] {
            font-size: 1.2em;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .login-container input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
    <script>
        function showForm(formId) {  //the three login buttons
            document.getElementById('guestForm').style.display = 'none';
            document.getElementById('adminForm').style.display = 'none';
            document.getElementById('registerForm').style.display = 'none';
            document.getElementById(formId).style.display = 'block';

        }

    </script>
</head>
<body>
<div class="login-container">
    <h2>Welcome to Our Page :)</h2>

    <button onclick="showForm('guestForm')"
            style="background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;">
        Login as Guest</button>
    <button onclick="showForm('adminForm')"
            style="background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;">
        Login as Admin</button>
    <button onclick="showForm('registerForm')"
            style="
            background-color: #4CAF50; color: white; padding: 10px 20px; border: none; border-radius: 5px; cursor: pointer;">
        Register</button>

    <form id="guestForm" action="login" method="post" style="display:none;">
        <h3>Login as Guest</h3>
        <label for="guestUsername">Username:</label>
        <input type="text" id="guestUsername" name="username" required><br><br>
        <label for="guestPassword">Password:</label>
        <input type="password" id="guestPassword" name="password" required><br><br>
        <input type="hidden" name="loginType" value="guest">
        <input type="submit" value="Login">
    </form>

    <form id="adminForm" action="login" method="post" style="display:none;">
        <h3>Login as Admin</h3>
        <label for="adminUsername">Username:</label>
        <input type="text" id="adminUsername" name="username" required><br><br>
        <label for="adminPassword">Password:</label>
        <input type="password" id="adminPassword" name="password" required><br><br>
        <input type="hidden" name="loginType" value="admin">
        <input type="submit" value="Login">
    </form>

    <form id="registerForm" action="register" method="post" style="display:none;">
        <h3>Register</h3>
        <label for="registerUsername">Username:</label>
        <input type="text" id="registerUsername" name="username" required><br><br>
        <label for="registerPassword">Password:</label>
        <input type="password" id="registerPassword" name="password" required><br><br>
        <input type="submit" value="Register">
    </form>

    <div style="height: 20px;"></div>

</div>
</body>
</html>
