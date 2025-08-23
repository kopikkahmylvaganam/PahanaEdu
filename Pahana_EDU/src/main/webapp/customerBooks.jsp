<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,bean.ItemBean" %>
<html>
<head>
<link rel="stylesheet" href="css/styless.css">
    <title>Books</title>
</head>
<body>
<h2>Available Books</h2>

<% if (request.getAttribute("errorMessage") != null) { %>
  <p style="color:red;"><%= request.getAttribute("errorMessage") %></p>
<% } %>
<% if (request.getAttribute("successMessage") != null) { %>
  <p style="color:green;"><%= request.getAttribute("successMessage") %></p>
<% } %>

<table border="1" cellpadding="6">
  <tr>
    <th>#</th>
    <th>Book</th>
    <th>Price</th>
    <th>Stock</th>
    <th>Add to Cart</th>
  </tr>
<%
  List<ItemBean> items = (List<ItemBean>) request.getAttribute("items");
  if (items == null || items.isEmpty()) {
%>
  <tr><td colspan="5">No books found.</td></tr>
<% } else {
     int i = 1;
     for (ItemBean b : items) {
%>
  <tr>
    <td><%= i++ %></td>
    <td><%= b.getName() %></td>
    <td>Rs.<%= String.format("%.2f", b.getPrice()) %></td>
    <td><%= b.getStock() %></td>
    <td>
      <form action="cart/add" method="post" style="display:inline;">
        <input type="hidden" name="bookName" value="<%= b.getName() %>">
        <input type="number" name="qty" min="1" value="1" <%= b.getStock() <= 0 ? "disabled" : "" %> >
        <input type="submit" value="Add" <%= b.getStock() <= 0 ? "disabled" : "" %> >
      </form>
    </td>
  </tr>
<%   } } %>
</table>

<p>
  <a href="cart">🛒 View Cart</a> |
  <a href="orderForm">Shop by Dropdown</a> |
  <a href="customerDashboard.jsp">Back to Dashboard</a>
</p>
</body>
</html>
