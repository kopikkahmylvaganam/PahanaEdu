<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Customer Signup</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        /* Optional: Custom popup styling */
        .popup {
            display: none;
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: #4CAF50;
            color: white;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,0.5);
            z-index: 1000;
            text-align: center;
        }
        .popup.show {
            display: block;
        }
    </style>
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
            <p style="color: green;" id="successMessage">
                ${message}
            </p>

            <!-- Custom Popup -->
            <div id="successPopup" class="popup">
                ${message}
            </div>

            <form action="signup" method="post">
                <label for="username">Username:</label><br>
                <input type="text" id="username" name="username" placeholder="Enter your Username" required><br><br>

                <label for="address">Address:</label><br>
                <input type="text" id="address" name="address" placeholder="Enter your Address" required><br><br>

                <label for="telephonenumber">Telephone:</label><br> <!-- Fixed label 'for' attribute -->
                <input type="text" id="telephonenumber" name="telephonenumber" placeholder="Enter your Telephone number" required><br><br>

                <label for="password">Password:</label><br>
                <input type="password" id="password" name="password" placeholder="Enter your Password with minimum 4 characters" required><br><br>

                <input type="submit" value="Signup">
            </form>

            <p>Already have an account? <a href="login.jsp">Login here</a></p>
        </div>
    </div>
</section>

<script>
    // Show popup if success message exists
    const successMessage = document.getElementById("successMessage").textContent.trim();
    if (successMessage) {
        const popup = document.getElementById("successPopup");
        popup.classList.add("show");
        // Hide popup and redirect after 3 seconds
        setTimeout(() => {
            popup.classList.remove("show");
            window.location.href = "login.jsp";
        }, 3000);
    }
</script>
</body>
</html>