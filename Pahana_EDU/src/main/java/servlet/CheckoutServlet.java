package servlet;

import java.io.IOException;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import bean.CartItemBean;
import dao.OrderDao;
import dao.CustomerDao;
import bean.CustomerBean;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"customer".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        // We stored login username in session earlier as "username" or "user_name".
        String username = (String) session.getAttribute("username");
        if (username == null) username = (String) session.getAttribute("user_name");
        if (username == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        // Get customer account_number
        CustomerDao cdao = new CustomerDao();
        CustomerBean c = cdao.getCustomerByUserName(username);
        if (c == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        int accountNumber = c.getAccountNumber();

        List<CartItemBean> cart = (List<CartItemBean>) session.getAttribute("cart");
        if (cart == null || cart.isEmpty()) {
            req.setAttribute("errorMessage", "Your cart is empty.");
            req.getRequestDispatcher("cart.jsp").forward(req, resp);
            return;
        }

        OrderDao orderDao = new OrderDao();
        int orderId = orderDao.createOrder(accountNumber, cart);

        if (orderId == -2) {
            req.setAttribute("errorMessage", "Not enough stock for one or more items.");
            req.getRequestDispatcher("cart.jsp").forward(req, resp);
            return;
        }
        if (orderId <= 0) {
            req.setAttribute("errorMessage", "Order failed. Please try again.");
            req.getRequestDispatcher("cart.jsp").forward(req, resp);
            return;
        }

        // success
        session.removeAttribute("cart");
        req.setAttribute("orderId", orderId);
        req.getRequestDispatcher("orderSuccess.jsp").forward(req, resp);
    }
}
