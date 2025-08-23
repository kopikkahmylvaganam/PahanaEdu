<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,bean.ItemBean" %>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Page</title>
    
    <link rel="stylesheet" href="css/styless.css">
    

</head>
<body>


                    
                    
                    <!-- <li class="nav-item">
                        <a class="nav-link" href="#about">Books</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="">Manage customers</a>
                    </li> -->
                    <!-- <li class="nav-item">
                        <a class="nav-link" href="menu.php">Cars</a>
                    </li> -->
                                        <!-- <li class="nav-item">
                        <a class="nav-link" href="#contact">Feedbacks</a>
                    </li> -->
                    
                    
                    
                    
                    
                
  
                <!-- <form class="d-flex">
                    <div class="input-group">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <div class="input-group-append">
                            <button class="btn btn-outline-light" type="submit">
                                <i class="fas fa-search"></i> 
                            </button>
                        </div>
                    </div>
                </form> -->
            
  
   <section>
        
            <div class="container">
                
               
                 <h2>Books (Item List)</h2>

                <a href="addItem.jsp">Add New Book</a><br><br>

                   <table border="1">
                <tr>
              <th>ID</th><th>Name</th><th>Price</th><th>Stock</th><th>Action</th>
             </tr>
 <%
    List<ItemBean> list = (List<ItemBean>) request.getAttribute("items");
    for (ItemBean i : list) {
%>
<tr>
    <td><%=i.getItemId()%></td>
    <td><%=i.getName()%></td>
    <td><%=i.getPrice()%></td>
    <td><%=i.getStock()%></td>
    <td>
        <a href="editItem?id=<%=i.getItemId()%>">Edit</a> | 
        <a href="deleteItem?id=<%=i.getItemId()%>" onclick="return confirm('Delete this book?');">Delete</a>
    </td>
</tr>
<% } %>
</table>
                 
          
            </div>
        
        
        <p> <a href="adminDashboard.jsp">Back to Dashboard</a></p>
        
    </section> 
    
<%-- <h2>Books (Item List)</h2>

<a href="addItem.jsp">Add New Book</a><br><br>

<table border="1">
<tr>
    <th>ID</th><th>Name</th><th>Price</th><th>Stock</th><th>Action</th>
</tr>
<%
    List<ItemBean> list = (List<ItemBean>) request.getAttribute("items");
    for (ItemBean i : list) {
%>
<tr>
    <td><%=i.getItemId()%></td>
    <td><%=i.getName()%></td>
    <td><%=i.getPrice()%></td>
    <td><%=i.getStock()%></td>
    <td>
        <a href="editItem?id=<%=i.getItemId()%>">Edit</a> | 
        <a href="deleteItem?id=<%=i.getItemId()%>" onclick="return confirm('Delete this book?');">Delete</a>
    </td>
</tr>
<% } %>
</table> --%>

</body>
</html>
