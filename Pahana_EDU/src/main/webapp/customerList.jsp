<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*, bean.CustomerBean" %>
<html>
<head>
  <title>Customer List</title>
  <link rel="stylesheet" href="css/styless.css">
</head>
<body>


<div style="height:64px"></div>

<div class="container">
  <h1>Customer List (Admin)</h1>
  <table>
    <thead>
    <tr>
      <th>ID</th><th>Username</th><th>Address</th><th>Phone</th><th>Action</th>
    </tr>
    </thead>
    <tbody>
    <%
      // IMPORTANT: Controller should set request attribute as "customers"
      List<CustomerBean> list = (List<CustomerBean>) request.getAttribute("customers");
      if (list != null && !list.isEmpty()) {
          for (CustomerBean c : list) {
    %>
    <tr>
      <td><%=c.getAccountNumber()%></td>
      <td><%=c.getUserName()%></td>
      <td><%=c.getAddress()%></td>
      <td><%=c.getTelephoneNumber()%></td>
      <td class="actions">
        <a class="btn btn-warning" href="editCustomer?id=<%=c.getAccountNumber()%>">Edit</a>
        <a class="btn btn-danger" href="deleteCustomer?id=<%=c.getAccountNumber()%>"
           onclick="return confirm('Are you sure?');">Delete</a>
      </td>
    </tr>
    <%    }
        } else { %>
    <tr><td colspan="5" style="text-align:center;color:red;">No customers found.</td></tr>
    <% } %>
    </tbody>
  </table>
  <p> <a href="adminDashboard.jsp">Back to Dashboard</a></p>
</div>
</body>
</html>
