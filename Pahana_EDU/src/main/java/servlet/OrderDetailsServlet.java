package servlet;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import dao.OrderDao;
import bean.OrderBean;

@WebServlet("/orderDetails")
public class OrderDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int orderId = Integer.parseInt(req.getParameter("id"));
        OrderDao dao = new OrderDao();
        OrderBean order = dao.getOrderWithItems(orderId);
        if (order == null) {
            resp.sendRedirect("orders");
            return;
        }
        req.setAttribute("order", order);
        req.getRequestDispatcher("orderDetails.jsp").forward(req, resp);
    }
}
