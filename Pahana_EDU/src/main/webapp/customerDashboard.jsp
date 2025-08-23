<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    // ✅ Session check: if no session, redirect to login
    String loggedUser = (String) session.getAttribute("username");
    if (loggedUser == null) {
        response.sendRedirect("login.jsp");
        return;
    }


%>
    
    
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bookshop</title>
    <link rel="stylesheet" href="css/style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/804a29e5b5.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
    <!-- navbarb section start here -->
    <nav class="navbar navbar-expand-lg fixed-top">
        <div class="container">
            <a class="navbar-brand" href=""><h2>PATHANA EDU <span>BOOKSHOP</span></h2></a>
    
            <button class="navbar-toggler bg-light" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
    
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    
                    
                    <li class="nav-item">
                        <a class="nav-link" href="customerProfile.jsp">My Profile</a>
                    </li>
                     <li class="nav-item">
                        <a class="nav-link" href="books">Books</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="orders">My Orders</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="cart">View cart</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#contact">Help</a>
                    </li>
                    
                    <li class="nav-item">
                        <a class="nav-link" href="Logout">
                            <i class="fa-regular fa-user"></i>
                        </a>
                    </li>
                    
                     
                    
                    
                </ul>
    
                
            </div>
        </div>
    </nav>
    
    
    <!-- navbar finish here -->
    <!-- Home section  start-->
    <div class="home" id="home" style="position: relative;">
    <img src="img/book.jpeg" alt="" style="width: 100%; height:100vh; object-fit: cover; position: relative;">
<div style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; background-image: linear-gradient(rgba(0,0,0,0.5),rgba(0,0,0,0.5));"></div>


    <div class="home-content" style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 1; text-align: center; color: #fff;">
    
    <!-- ✅ Welcome message -->
    <h2>Welcome, <%= loggedUser %>!</h2>
        <h2>Welcome to The pahana edu bookshop </h2>
        <p> Books is the source of knoledge.
        </p>
    </div>
</div>
    <!-- home section finish here -->

    <!-- promotions -->
    
    
     <!-- about -->
     <div class="about" id="about">
        <div class="flex-row1">
            <ul>
            
           <!--  <li><a href="cart">🛒 View Cart</a></li>
             <li><a href="myOrders">📦 My Orders</a></li>
             <li><a href="customerProfile.jsp">👤 My Profile</a></li>
             
             <li><a href="customerBooks">📚 View Books & Order</a></li> -->
             
          </ul>
          
            
        </div>
    </div>
    

    <!-- about end -->



    

   <!--  <section id="contact">
        <h2>Contact Us</h2>
        <div class="container" >
            
            <div class="left-side">
                <h4>we are here to get your Enquiries and feedbacks so feel free to contact</h4>
                <img src="img/contact.jpg" alt="Description of the image">
                
            </div>
            <div class="right-side">
                <div class="feedback-container">
                    <h2>Feedback / Enquiry Form</h2>
                    <form id="feedbackForm" action="index2.php" method="POST">
                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name" required><br>
        
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required><br>
        
                    <label for="message">Message:</label>
                    <textarea id="message" name="message" rows="5" required></textarea><br><br>
                        
                    <input type="submit" class="special-submit" id="submit" name="submit" value="Submit" onclick="sendmessage">
                    </form>
                </div>
            </div>
        </div>
    </section> -->
   

   

    <!-- footer -->
    <section>
        <div class="footer-container">
            <div class="footer-column">
                <h3 class="logo-text">Pahana edu <span> bookshop</span> </h3>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Accusamus, minima.</p>
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
                    <!-- <li><a href="#">Promotions</a></li> -->
                    <li><a href="#about">About</a></li>
                    <!-- <li><a href="#service">Service</a></li>
                    <li><a href="#">Menu</a></li>
                    <li><a href="#">Reservation</a></li> -->
                    
                </ul>
            </div>
            <div class="footer-column">
                <h3>Contact Us</h3>
                <ul>
                    <li><i class="fas fa-phone"></i> +94675546576</li>
                    <li><i class="fa-solid fa-location-dot"></i> 123, main street, Colombo</li>
                    <li><i class="fas fa-envelope"></i> pathanaedu@gmail.com</li>
                </ul>
            </div>

        </div>
        <div class="footer_bottom">&copy; All rights reserved from pahanaedu since-2025</div>

    </section>
    <!-- footer end -->
    <% 
    String successMessage = (String) session.getAttribute("successMessage");
    if (successMessage != null) { 
%>
    <script>
        alert("<%= successMessage %>");
    </script>
<%
        session.removeAttribute("successMessage"); // clear after showing once
    } 
%>


    
</body>
</html>