package servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.CustomerDao;
import bean.CustomerBean;

@WebServlet("/customerList") // 👉 lower-case, JSP லிங்க்க்கு match
public class CustomerListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // Admin மட்டும் பார்ப்பது நல்லது; இல்லையெனில் login க்கு அனுப்பு
        HttpSession session = req.getSession(false);
        String role = (session == null) ? null : (String) session.getAttribute("role");
        if (!"admin".equals(role)) {
            resp.sendRedirect("login.jsp");
            return;
        }

        CustomerDao dao = new CustomerDao();
        List<CustomerBean> list = dao.getAllCustomers();

        // 👉 JSP எதிர்பார்ப்பது "customers"
        req.setAttribute("customers", list);

        // forward to JSP
        RequestDispatcher rd = req.getRequestDispatcher("customerList.jsp");
        rd.forward(req, resp);
    }
}
