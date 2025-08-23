<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="bean.CustomerBean" %>

<%
    CustomerBean c = (CustomerBean) session.getAttribute("customer");
    String safeUser = (c != null && c.getUserName() != null) ? c.getUserName() : "";
    String safeAddr = (c != null && c.getAddress() != null) ? c.getAddress() : "";
    String safeTel  = (c != null && c.getTelephoneNumber() != null) ? c.getTelephoneNumber() : "";
    String safePwd  = (c != null && c.getPassword() != null) ? c.getPassword() : "";
%>

<html>
<head>
<link rel="stylesheet" href="css/styless.css">
<title>My Profile</title></head>
<body>
<div class="container">
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
  <input type="text" name="userName" value="<%=safeUser%>" required><br>

  Address:
  <input type="text" name="address" value="<%=safeAddr%>" required><br>

  Phone:
  <input type="text" name="telephone" value="<%=safeTel%>" required><br>

  Password:
  <input type="password" name="password" value="<%=safePwd%>" required><br>

  <input type="submit" value="Update">
</form>


<% } %>
</div>
<p> <a href="customerDashboard.jsp">Back to Dashboard</a></p>
</body>
</html>
