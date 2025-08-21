package servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.OrderDao;
import dao.CustomerDao;
import bean.OrderBean;
import bean.CustomerBean;

@WebServlet("/orders")
public class OrderHistoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || !"customer".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
        if (username == null) username = (String) session.getAttribute("user_name");

        CustomerDao cdao = new CustomerDao();
        CustomerBean c = cdao.getCustomerByUserName(username);
        if (c == null) { resp.sendRedirect("login.jsp"); return; }

        OrderDao dao = new OrderDao();
        List<OrderBean> list = dao.getOrdersByAccountNumber(c.getAccountNumber());
        req.setAttribute("orders", list);
        req.getRequestDispatcher("orderHistory.jsp").forward(req, resp);
    }
}
