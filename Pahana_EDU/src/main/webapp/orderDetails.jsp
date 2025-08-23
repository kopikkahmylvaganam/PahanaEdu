<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="bean.OrderBean,bean.OrderItemBean,java.util.*" %>
<%
  OrderBean order = (OrderBean) request.getAttribute("order");
%>
<html>
<head><title>Order Details</title>
<link rel="stylesheet" href="css/styless.css">

</head>
<body>
<div class="container">
<h2>Invoice</h2>
<% if (order == null) { %>
  <p>Order not found.</p>
<% } else { %>
  <p><b>Order ID:</b> <%=order.getOrderId()%></p>
  <p><b>Order Date:</b> <%=order.getOrderDate()%></p>

  <table  border="1" cellpadding="6">
    <tr><th>#</th><th>Book</th><th>Price</th><th>Qty</th><th>Line Total</th></tr>
    <%
      int idx = 1; 
      double total = 0.0;
      for (OrderItemBean it : order.getItems()) {
        double lt = it.getPrice() * it.getQuantity();
        total += lt;
    %>
    <tr>
      <td><%=idx++%></td>
      <td><%=it.getItemName()%></td>
      <td>Rs.<%=String.format("%.2f", it.getPrice())%></td>
      <td><%=it.getQuantity()%></td>
      <td>Rs.<%=String.format("%.2f", lt)%></td>
    </tr>
    <% } %>
    <tr>
      <td colspan="4" align="right"><b>Total</b></td>
      <td><b>Rs.<%=String.format("%.2f", total)%></b></td>
    </tr>
  </table>
<% } %>
<p><a href="orderList">Back to Orders</a></p>
<p> <a href="customerDashboard.jsp">Back to Dashboard</a></p>
</div>
</body>
</html>
