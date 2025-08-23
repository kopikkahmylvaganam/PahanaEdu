<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
<link rel="stylesheet" href="css/styless.css">

<title>Add Book</title>
</head>
<body>
<div class="container">
<h2>Add New Book</h2>
<form action="addItem" method="post">
    Name: <input type="text" name="name" required><br>
    Price: <input type="text" name="price" required><br>
    Stock: <input type="number" name="stock" required><br>
    <input type="submit" value="Add Book">
</form>
</div>
</body>
</html>
