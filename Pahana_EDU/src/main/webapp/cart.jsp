<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,bean.CartItemBean" %>
<html>
<head>
<link rel="stylesheet" href="css/styless.css">

<title>My Cart</title>
</head>
<body>
<div class="container">
<h2>My Cart</h2>

<% if (request.getAttribute("errorMessage") != null) { %>
  <p style="color:red;"><%=request.getAttribute("errorMessage")%></p>
<% } %>

<%
  List<CartItemBean> cart = (List<CartItemBean>) request.getAttribute("cart");
  if (cart == null || cart.isEmpty()) {
%>
  <p>Your cart is empty.</p>
  <p><a href="orderForm">Continue Shopping</a></p>
<% } else { 
     double total = 0.0;
%>
<table border="1" cellpadding="6">
<tr>
  <th>Book</th><th>Price</th><th>Qty</th><th>Line Total</th><th>Action</th>
</tr>
<% for (CartItemBean ci : cart) {
     total += ci.getLineTotal();
%>
<tr>
  <td><%=ci.getName()%></td>
  <td>Rs.<%=ci.getPrice()%></td>
  <td>
    <form action="cart/update" method="post" style="display:inline;">
      <input type="hidden" name="itemId" value="<%=ci.getItemId()%>">
      <input type="number" name="qty" min="0" value="<%=ci.getQty()%>">
      <input type="submit" value="Update">
    </form>
  </td>
  <td>Rs.<%=String.format("%.2f", ci.getLineTotal())%></td>
  <td><a href="cart/remove?itemId=<%=ci.getItemId()%>">Remove</a></td>
</tr>
<% } %>
<tr>
  <td colspan="3" align="right"><b>Total</b></td>
  <td colspan="2"><b>Rs.<%=String.format("%.2f", total)%></b></td>
</tr>
</table>

<br>
<form action="checkout" method="post">
  <input type="submit" value="Checkout">
</form>
<p><a href="orderForm">Add more books</a></p>

<% } %>
<p> <a href="customerDashboard.jsp">Back to Dashboard</a></p>
</div>
</body>
</html>
