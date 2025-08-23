<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<link rel="stylesheet" href="css/styless.css">


<title>Order Success</title>

</head>
<body>
<div class="container">
<h2>Order Successful!</h2>
<p>Your order ID: <b><%=request.getAttribute("orderId")%></b></p>
<p><a href="orders">View My Orders</a> | <a href="orderForm">Shop More</a> <br>
<a href="customerDashboard.jsp">Back to Dashboard</a>


</p>
</div>
</body>
</html>
