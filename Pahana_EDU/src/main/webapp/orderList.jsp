<%@ page import="java.util.*, bean.OrderBean" %>
<html>
<head><title>All Orders</title>
<link rel="stylesheet" href="css/styless.css">
</head>
<body>
<h2>All Orders</h2>

<table border="1" cellpadding="5">
<tr>
    <th>Order ID</th>
    <th>Customer Account</th>
    <th>Date</th>
    <th>Total</th>
    <th>Actions</th>
</tr>
<%
    List<OrderBean> orders = (List<OrderBean>) request.getAttribute("orders");
    if (orders != null && !orders.isEmpty()) {
        for (OrderBean o : orders) {
%>
<tr>
    <td><%=o.getOrderId()%></td>
    <td><%=o.getAccountNumber()%></td>
    <td><%=o.getOrderDate()%></td>
    <td><%=o.getTotalAmount()%></td>
    <td>
        <a href="orderDetails?id=<%=o.getOrderId()%>">View</a> |
        <a href="deleteOrder?id=<%=o.getOrderId()%>">Delete</a>
    </td>
</tr>
<%      }
    } else { %>
<tr><td colspan="5">No orders found.</td></tr>
<% } %>
</table>

<a href="adminDashboard.jsp">Back to Dashboard</a>
</body>
</html>
