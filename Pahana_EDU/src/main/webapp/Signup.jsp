
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>Customer Signup</title>
<link rel="stylesheet" href="css/home.css">
</head>
<body>
<section>
   <div class="login" id="login">
        <h2>Welcome to Padana Edu Bookshop!</h2>


    <div class="login-container">
        <h1>Customer Signup</h1>

        <!-- Display error/success messages -->
        <p style="color: red;">
            ${errorMessage}
        </p>
        <p style="color: green;">
            ${message}
        </p>

        <form action="signup" method="post">
            <label for="username">Username:</label><br>
            <input type="text" id="username" name="username" placeholder="Enter your Username" required><br><br>

            <label for="address">Address:</label><br>
            <input type="text" id="address" name="address" placeholder="Enter your Address" required><br><br>

            <label for="telephone">Telephone:</label><br>
            <input type="text" id="telephone" name="telephone" placeholder="Enter your Telephonenumber" required><br><br>

            <label for="password">Password:</label><br>
            <input type="password" id="password" name="password" placeholder="Enter your Password" required><br><br>

            <input type="submit" value="Signup">
        </form>

        <p>Already have an account? <a href="login.jsp">Login here</a></p>
    </div>
    </div>
</section>
</body>
</html>
