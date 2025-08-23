<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="bean.ItemBean" %>
<%
    ItemBean i = (ItemBean) request.getAttribute("item");
%>
<html>
<head>
<link rel="stylesheet" href="css/styless.css">
<title>Edit Book</title>
</head>
<body>
<div class="container">
<h2>Edit Book</h2>
<form action="editItem" method="post">
    <input type="hidden" name="itemId" value="<%=i.getItemId()%>">
    Name: <input type="text" name="name" value="<%=i.getName()%>" required><br>
    Price: <input type="text" name="price" value="<%=i.getPrice()%>" required><br>
    Stock: <input type="number" name="stock" value="<%=i.getStock()%>" required><br>
    <input type="submit" value="Update Book">
</form>
</div>
</body>
</html>
