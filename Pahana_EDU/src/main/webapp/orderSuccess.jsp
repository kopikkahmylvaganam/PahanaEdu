<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Order Success</title></head>
<body>
<h2>Order Successful!</h2>
<p>Your order ID: <b><%=request.getAttribute("orderId")%></b></p>
<p><a href="orders">View My Orders</a> | <a href="orderForm">Shop More</a></p>
</body>
</html>
