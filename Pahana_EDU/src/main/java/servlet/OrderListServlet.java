package servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import dao.OrderDao;
import bean.OrderBean;

@WebServlet("/orderList")
public class OrderListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        OrderDao dao = new OrderDao();
        List<OrderBean> orders = dao.getAllOrders();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("orderList.jsp").forward(req, resp);
    }
}
