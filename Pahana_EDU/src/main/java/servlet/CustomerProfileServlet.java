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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String userName = (String) session.getAttribute("username");
        CustomerDao dao = new CustomerDao();
        CustomerBean customer = dao.getCustomerByUserName(userName);

        if (customer != null) {
            session.setAttribute("customer", customer); // keep session in sync
            req.setAttribute("customer", customer);     // also set for JSP if needed
            req.getRequestDispatcher("customerProfile.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("customerDashboard.jsp?error=notfound");
        }
    }
}
