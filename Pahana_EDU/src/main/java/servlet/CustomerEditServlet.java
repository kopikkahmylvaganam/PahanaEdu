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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String role = (String) req.getSession().getAttribute("role");

        CustomerDao dao = new CustomerDao();
        CustomerBean c = dao.getCustomerById(id, "customer".equals(role));
        if ("admin".equals(role)) {
            req.setAttribute("customer", c);
            req.getRequestDispatcher("editCustomer.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("customer", c);
            req.getRequestDispatcher("customerProfile.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("role") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }
        String role = (String) session.getAttribute("role");

        int id = Integer.parseInt(req.getParameter("accountNumber"));

        CustomerDao dao = new CustomerDao();
        CustomerBean current = dao.getCustomerById(id, true);
        if (current == null) {
            req.setAttribute("errorMessage", "Customer not found.");
            req.getRequestDispatcher("customerProfile.jsp").forward(req, resp);
            return;
        }

        // read new values + fallback to old if blank
        String userName = gv(req.getParameter("userName"), current.getUserName());
        if (userName == null || userName.isBlank()) {
            userName = gv(req.getParameter("username"), current.getUserName());
        }
        String address  = gv(req.getParameter("address"), current.getAddress());
        String phone    = gv(req.getParameter("telephone"), current.getTelephoneNumber());
        String password = current.getPassword(); // default keep old

        boolean includePassword = "customer".equals(role);
        if (includePassword) {
            String p = req.getParameter("password");
            if (p != null && !p.trim().isEmpty()) {
                password = p.trim();
            }
        }

        // phone must be exactly 10 digits
        if (phone == null || !phone.matches("^\\d{10}$")) {
            req.setAttribute("errorMessage", "Phone must be exactly 10 digits.");
            CustomerBean rebound = new CustomerBean();
            rebound.setAccountNumber(id);
            rebound.setUserName(userName);
            rebound.setAddress(address);
            rebound.setTelephoneNumber(phone);
            rebound.setPassword(password);
            req.setAttribute("customer", rebound);
            req.getRequestDispatcher("customerProfile.jsp").forward(req, resp);
            return;
        }

        CustomerBean toSave = new CustomerBean();
        toSave.setAccountNumber(id);
        toSave.setUserName(userName);
        toSave.setAddress(address);
        toSave.setTelephoneNumber(phone);
        toSave.setPassword(password);

        boolean updated = dao.updateCustomer(toSave, includePassword);

        if (updated) {
            CustomerBean fresh = dao.getCustomerById(id, true);
            session.setAttribute("customer", fresh);
            session.setAttribute("username", fresh.getUserName());
            req.setAttribute("successMessage", "Profile updated successfully!");
            req.getRequestDispatcher("customerProfile.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorMessage", "Update failed! Please check your inputs.");
            req.setAttribute("customer", toSave);
            req.getRequestDispatcher("customerProfile.jsp").forward(req, resp);
        }
    }

    private static String gv(String in, String fallback) {
        return (in == null || in.trim().isEmpty()) ? fallback : in.trim();
    }
}
