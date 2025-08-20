<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="bean.CustomerBean" %>

<%
    CustomerBean c = (CustomerBean) request.getAttribute("customer");
%>

<html>
<head>
    <title>My Profile</title>
</head>
<body>

    <h1>My Profile</h1>

    <% if (c == null) { %>
        <p style="color:red;">Customer details not found. Please login again.</p>
    <% } else { %>
        <form action="editCustomer" method="post">
            <!-- Account number shown but not editable -->
            Account Number: 
            <input type="text" value="<%=c.getAccountNumber()%>" readonly><br>
            <input type="hidden" name="accountNumber" value="<%=c.getAccountNumber()%>">

            Username: 
            <input type="text" name="username" value="<%=c.getUserName()%>"><br>

            Address: 
            <input type="text" name="address" value="<%=c.getAddress()%>"><br>

            Phone: 
            <input type="text" name="telephone" value="<%=c.getTelephoneNumber()%>"><br>

            Password: 
            <input type="password" name="password" value="<%=c.getPassword()%>"><br>

            <input type="submit" value="Update">
        </form>
    <% } %>

</body>
</html>
