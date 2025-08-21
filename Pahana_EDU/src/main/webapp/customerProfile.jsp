<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="bean.CustomerBean" %>

<%
    CustomerBean c = (CustomerBean) session.getAttribute("customer");
%>

<html>
<head>
    <title>My Profile</title>
</head>
<body>
    <h1>My Profile</h1>

    <% if (request.getAttribute("errorMessage") != null) { %>
      <p style="color:red;"><%= request.getAttribute("errorMessage") %></p>
    <% } %>
    <% if (request.getAttribute("successMessage") != null) { %>
      <p style="color:green;"><%= request.getAttribute("successMessage") %></p>
    <% } %>

    <% if (c == null) { %>
        <p style="color:red;">Customer details not found. Please login again.</p>
    <% } else { %>
        <form action="editCustomer" method="post">
            Account Number:
            <input type="text" value="<%=c.getAccountNumber()%>" readonly><br>
            <input type="hidden" name="accountNumber" value="<%=c.getAccountNumber()%>">

            Username:
            <input type="text" name="userName" value="<%=c.getUserName()%>" required><br>

            Address:
            <input type="text" name="address" value="<%=c.getAddress()%>" required><br>

            Phone:
            <input type="text" name="telephone" value="<%=c.getTelephoneNumber()%>" required><br>

            Password:
            <input type="password" name="password" value="<%=c.getPassword()%>" required><br>

            <input type="submit" value="Update">
        </form>
    <% } %>
</body>
</html>
