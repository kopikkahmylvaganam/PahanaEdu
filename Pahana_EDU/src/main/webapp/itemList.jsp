<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.*,bean.ItemBean" %>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Page</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/style.css">
    <!-- FontAwesome -->
    <!-- <script src="https://kit.fontawesome.com/804a29e5b5.js" crossorigin="anonymous"></script> -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    

</head>
<body>

<nav class="navbar navbar-expand-lg fixed-top">
        <div class="container">
            <a class="navbar-brand" href="adminDashboard.jsp"><h2>PAHANA EDU <span>BOOKSHOP</span></h2></a>
    
            <button class="navbar-toggler bg-light" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
    
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    
                    
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
                    
                    
                    
                    
                    
                </ul>
    
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
            </div>
        </div>
    </nav>
  
   <section id="promotions">
        <div class="promotion-section">
            <div class="promotion-container">
                
               
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
        </div>
        
        <p> <a href="customerDashboard.jsp">Back to Dashboard</a></p>
        
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
