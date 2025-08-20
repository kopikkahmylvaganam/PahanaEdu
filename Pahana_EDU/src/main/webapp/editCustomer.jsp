<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="bean.CustomerBean" %>
<%
    CustomerBean c = (CustomerBean) request.getAttribute("customer");
%>
<html>
<head><title>Edit Customer</title></head>
<body>
<h1>Edit Customer (Admin)</h1>
<% if (c == null) { %>
    <p style="color:red;">Customer not found!</p>
<% } else { %>
<form action="editCustomer" method="post">
    <input type="hidden" name="accountNumber" value="<%=c.getAccountNumber()%>">
    Username: <input type="text" name="username" value="<%=c.getUserName()%>"><br>
    Address: <input type="text" name="address" value="<%=c.getAddress()%>"><br>
    Phone: <input type="text" name="telephonenumber" value="<%=c.getTelephoneNumber()%>"><br>
    <!-- Admin cannot see/edit password -->
    <input type="submit" value="Update">
</form>
<% } %>
</body>
</html>
