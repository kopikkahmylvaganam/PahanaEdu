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

        if (c == null) {
            resp.sendRedirect("customerList?error=notfound");
            return;
        }

        req.setAttribute("customer", c);

        if ("admin".equals(role)) {
            req.getRequestDispatcher("editCustomer.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("customerProfile.jsp").forward(req, resp);
        }
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("accountNumber"));
        String userName = req.getParameter("username");
        String address = req.getParameter("address");
        String phone = req.getParameter("telephonenumber");
        String password = req.getParameter("password"); // may be null

        String role = (String) req.getSession().getAttribute("role");
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
            if ("admin".equals(role)) {
                resp.sendRedirect("customerList");
            } else {
                resp.sendRedirect("customerDashboard.jsp");
            }
        } else {
            req.setAttribute("errorMessage", "Update failed!");
            if ("admin".equals(role)) {
                req.getRequestDispatcher("editCustomer.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("customerProfile.jsp").forward(req, resp);
            }
        }
    }
}
