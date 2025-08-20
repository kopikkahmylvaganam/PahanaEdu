<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="bean.CustomerBean" %>
<html>
<head><title>Customer List</title></head>
<body>
<h1>Customer List (Admin)</h1>
<table border="1">
<tr>
    <th>ID</th><th>Username</th><th>Address</th><th>Phone</th><th>Action</th>
</tr>
<%
    List<CustomerBean> list = (List<CustomerBean>) request.getAttribute("customer");
    if (list != null) {
        for (CustomerBean c : list) {
%>
<tr>
    <td><%=c.getAccountNumber()%></td>
    <td><%=c.getUserName()%></td>
    <td><%=c.getAddress()%></td>
    <td><%=c.getTelephoneNumber()%></td>
    <td>
        <a href="editCustomer?id=<%=c.getAccountNumber()%>">Edit</a> | 
        <a href="deleteCustomer?id=<%=c.getAccountNumber()%>" 
           onclick="return confirm('Are you sure?');">Delete</a>
    </td>
</tr>
<%      }
    }
%>
</table>
</body>
</html>
