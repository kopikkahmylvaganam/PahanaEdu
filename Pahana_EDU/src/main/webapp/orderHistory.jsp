<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,bean.OrderBean" %>
<html>
<head><title>My Orders</title></head>
<body>
<h2>My Orders</h2>
<%
  List<OrderBean> orders = (List<OrderBean>) request.getAttribute("orders");
  if (orders == null || orders.isEmpty()) {
%>
  <p>No orders yet.</p>
<% } else { %>
<table border="1" cellpadding="6">
<tr><th>Order ID</th><th>Date</th><th>Total</th><th>Action</th></tr>
<% for (OrderBean o : orders) { %>
<tr>
  <td><%=o.getOrderId()%></td>
  <td><%=o.getOrderDate()%></td>
  <td>Rs.<%=String.format("%.2f", o.getTotalAmount())%></td>
  <td><a href="orderDetails?id=<%=o.getOrderId()%>">View</a></td>
</tr>
<% } %>
</table>
<% } %>
<p><a href="orderForm">Back to Shop</a></p>
</body>
</html>
