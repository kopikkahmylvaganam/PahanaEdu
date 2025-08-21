<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
    <!-- navbarb section start here -->
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
                    <li class="nav-item">
                        <a class="nav-link" href="customerList.jsp">Customers</a>
                    </li>
                    <!-- <li class="nav-item">
                        <a class="nav-link" href="#contact">Feedbacks</a>
                    </li> -->
                    
                    <li class="nav-item">
                        <a class="nav-link" href="Logout">
                           <i class="fa-solid fa-power-off"></i>
                        </a>
                    </li>
                    
                    
                    
                    
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
    
    
    <!-- navbar finish here -->
    <!-- Home section  start-->
    <div class="home" id="home" style="position: relative;">
    <img src="img/top-view-food-banquet.jpg" alt="" style="width: 100%; height:100vh; object-fit: cover; position: relative;">
<div style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; background-image: linear-gradient(rgba(0,0,0,0.5),rgba(0,0,0,0.5));"></div>


    <div class="home-content" style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 1; text-align: center; color: #fff;">
        <h2>Welcome ADMIN to The PAHANA EDU BOOKSHOP   </h2>
        
    </div>
</div>
    <!-- home section finish here -->
 <div class="about" id="about">
        <div class="flex-row1">
            <h2>Admin Dashboard</h2>
         <ul>
             <li><a href="itemList">📚 Manage Books</a></li>
             <li><a href="customerList">👥 View Customers</a></li>
             <li><a href="orderList">📦 View All Orders</a></li>
             <li><a href="logout.jsp">🚪 Logout</a></li>
             
             
        </ul>

          
            
        </div>
    </div>
    
   


     
    
    

   

    <!-- footer -->
    <section>
        <div class="footer-container">
            <div class="footer-column">
                <h3 class="logo-text">PATHANA EDU <span> BOOKSHOP</span> </h3>
                <p>The mega city cab is one of the best cab service in Srilanka</p>
                <ul class="social-icons">
                    <li><a href="#"><i class="fab fa-facebook"></i></a></li>
                    <li><a href="#"><i class="fab fa-twitter"></i></a></li>
                    <li><a href="#"><i class="fab fa-instagram"></i></a></li>
                    <li><a href="#"><i class="fab fa-linkedin"></i></a></li>

                </ul>
                
            </div>
            <div class="footer-column">
                <h3>Quick links</h3>
                <ul>
                    <li><a href="#home">Home</a></li>
                    <li><a href="#">Promotions</a></li>
                    <li><a href="#about">About</a></li>
                    <li><a href="#service">Service</a></li>
                    <li><a href="#">Menu</a></li>
                    <li><a href="#">Reservation</a></li>
                    
                </ul>
            </div>
            <div class="footer-column">
                <h3>Contact Us</h3>
                <ul>
                    <li><i class="fas fa-phone"></i> +94675546576</li>
                    <li><i class="fa-solid fa-location-dot"></i> 123, main street,Colombo</li>
                    <li><i class="fas fa-envelope"></i> pahanaedu@gmail.com</li>
                </ul>
            </div>

        </div>
        <div class="footer_bottom">&copy; All rights reserved from pahanaedu since-2020</div>

    </section>
    <!-- footer end -->
    

    <!-- Bootstrap JS (with Popper) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>