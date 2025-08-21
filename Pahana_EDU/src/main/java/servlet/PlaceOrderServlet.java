package servlet;

import java.io.IOException;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import bean.ItemBean;
import bean.CartItemBean;
import bean.CustomerBean;
import dao.ItemDao;
import dao.CustomerDao;
import dao.OrderDao;

@WebServlet("/PlaceOrder")
public class PlaceOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        HttpSession session = req.getSession(false);
        if (session == null || !"customer".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
        if (username == null) username = (String) session.getAttribute("user_name");

        // find customer account number
        CustomerDao cdao = new CustomerDao();
        CustomerBean customer = cdao.getCustomerByUserName(username);
        if (customer == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String bookName = req.getParameter("bookName");
        int qty = Integer.parseInt(req.getParameter("qty"));

        ItemDao itemDao = new ItemDao();
        ItemBean book = itemDao.getByName(bookName);

        if (book == null) {
            req.setAttribute("errorMessage", "Book not found!");
            req.getRequestDispatcher("orderForm.jsp").forward(req, resp);
            return;
        }

        if (book.getStock() < qty) {
            req.setAttribute("errorMessage", "Not enough stock!");
            req.getRequestDispatcher("orderForm.jsp").forward(req, resp);
            return;
        }

        // prepare cart
        List<CartItemBean> cart = new ArrayList<>();
        CartItemBean ci = new CartItemBean();
        ci.setItemId(book.getItemId());
        ci.setName(book.getName());
        ci.setPrice(book.getPrice());
        ci.setQty(qty);
        cart.add(ci);

        // save order
        OrderDao orderDao = new OrderDao();
        int orderId = orderDao.createOrder(customer.getAccountNumber(), cart);

        if (orderId > 0) {
            req.setAttribute("successMessage", "Order placed successfully! Order ID: " + orderId);
            resp.sendRedirect("orders"); // go to order history
        } else if (orderId == -2) {
            req.setAttribute("errorMessage", "Not enough stock!");
            req.getRequestDispatcher("orderForm.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "Order failed. Please try again.");
            req.getRequestDispatcher("orderForm.jsp").forward(req, resp);
        }
    }
}
