<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css/home.css"> <!-- Link to your CSS file -->
</head>
<body>
    <section>
        <div class="login" id="login">
            <h2>Happy to meet again! LOGIN here </h2>
            <br><br>
            <div class="login-container">
                <h1>Login</h1>
                
                <!-- Display error message if login fails -->
                <% if (request.getAttribute("errorMessage") != null) { %>
                    <p style="color: red;"><%= request.getAttribute("errorMessage") %></p>
                <% } %>

                <!-- Form action updated to call the LoginServlet -->
                <form method="POST" action="Login">
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" placeholder="Enter your username" required><br>

                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" placeholder="Enter the password" required><br><br>
            
                    <input type="submit" id="login" name="login" value="Login">
                </form>
                <p>Are you new to our Shop? <a href="Signup.jsp">Sign Up Here</a></p>
            </div>
        </div>
    </section>
</body>
</html>