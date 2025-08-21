package servlet;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.CustomerDao;
import bean.CustomerBean;

@WebServlet("/editCustomer")
public class CustomerEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String role = (String) req.getSession().getAttribute("role");

        CustomerDao dao = new CustomerDao();
        CustomerBean c = dao.getCustomerById(id, "customer".equals(role));
        req.setAttribute("customer", c);

        if ("admin".equals(role)) {
            req.getRequestDispatcher("editCustomer.jsp").forward(req, resp);
        } else {
            // customer profile page
            req.getSession().setAttribute("customer", c);
            req.getRequestDispatcher("customerProfile.jsp").forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String role = (String) req.getSession().getAttribute("role");

        int id = Integer.parseInt(req.getParameter("accountNumber"));

        // read both keys safely
        String userName = req.getParameter("userName");
        if (userName == null || userName.isBlank()) {
            userName = req.getParameter("username");
        }

        String address = req.getParameter("address");
        String phone = req.getParameter("telephone");
        String password = req.getParameter("password"); // customer updates this

        boolean includePassword = "customer".equals(role);

        CustomerBean c = new CustomerBean();
        c.setAccountNumber(id);
        c.setUserName(userName);
        c.setAddress(address);
        c.setTelephoneNumber(phone);
        if (includePassword) {
            c.setPassword(password);
        }

        CustomerDao dao = new CustomerDao();
        boolean updated = dao.updateCustomer(c, includePassword);

        if (updated) {
            // refresh current bean from DB using account number (robust even if username changed)
            CustomerBean fresh = dao.getCustomerById(id, true);

            if ("admin".equals(role)) {
                resp.sendRedirect("customerList");
            } else {
                // keep session in sync
                HttpSession session = req.getSession();
                session.setAttribute("customer", fresh);
                session.setAttribute("username", fresh.getUserName());
                session.setAttribute("user_name", fresh.getUserName());

                req.setAttribute("successMessage", "Profile updated successfully!");
                req.getRequestDispatcher("customerProfile.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("errorMessage", "Update failed! Please check your inputs.");
            if ("admin".equals(role)) {
                req.getRequestDispatcher("editCustomer.jsp").forward(req, resp);
            } else {
                // re-bind what user typed so fields don't go blank
                req.setAttribute("customer", c);
                req.getRequestDispatcher("customerProfile.jsp").forward(req, resp);
            }
        }
    }
}
