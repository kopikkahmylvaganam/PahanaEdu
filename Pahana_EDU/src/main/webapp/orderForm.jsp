<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,bean.ItemBean" %>
<html>
<head><title>Place Order</title>
<link rel="stylesheet" href="css/styless.css">
</head>
<body>
<div class="container">
<h2>Select Book</h2>

<% if (request.getAttribute("errorMessage") != null) { %>
  <p style="color:red;"><%=request.getAttribute("errorMessage")%></p>
<% } %>

<form action="cart/add" method="post">
    <label>Book:</label>
    <select name="bookName" required>
        <%
            List<ItemBean> items = (List<ItemBean>) request.getAttribute("items");
            for (ItemBean i : items) {
        %>
        <option value="<%=i.getName()%>">
            <%=i.getName()%> - Rs.<%=i.getPrice()%> (Stock: <%=i.getStock()%>)
        </option>
        <% } %>
    </select>
    &nbsp;&nbsp;<br>
    
    <label>Qty:</label>
    <input type="number" name="qty" min="1" value="1" required>
    <input type="submit" value="Add to Cart">
</form>

<p><a href="cart">View Cart</a> | <a href="books">Back to Books</a></p>
<p> <a href="customerDashboard.jsp">Back to Dashboard</a></p>
</div>
</body>
</html>
