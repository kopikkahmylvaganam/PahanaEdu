package servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.CustomerDao;
import bean.CustomerBean;

@WebServlet("/CustomerList")
public class CustomerListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        if (role == null || !"admin".equals(role)) {
            resp.sendRedirect("login.jsp");
            return;
        }

        CustomerDao dao = new CustomerDao();
        List<CustomerBean> list = dao.getAllCustomers();
        req.setAttribute("customers", list);
        req.getRequestDispatcher("customerList.jsp").forward(req, resp);
    }
}
