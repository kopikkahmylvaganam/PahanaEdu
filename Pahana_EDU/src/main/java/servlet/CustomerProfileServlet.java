package servlet;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import bean.CustomerBean;
import dao.CustomerDao;

@WebServlet("/customerProfile")
public class CustomerProfileServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) { 
            resp.sendRedirect("login.jsp");
            return;
        }

        String userName = (String) session.getAttribute("username"); // ✅ FIXED
        CustomerDao dao = new CustomerDao();
        CustomerBean customer = dao.getCustomerByUserName(userName);

        if (customer != null) {
            req.setAttribute("customer", customer);
            RequestDispatcher rd = req.getRequestDispatcher("customerProfile.jsp");
            rd.forward(req, resp);
        } else {
            resp.getWriter().println("Customer details not found!");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
