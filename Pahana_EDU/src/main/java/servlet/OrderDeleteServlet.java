package servlet;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.OrderDao;

@WebServlet("/deleteOrder")
public class OrderDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        String idStr = req.getParameter("id");
        if (idStr == null) {
            resp.sendRedirect("orderList?msg=Missing+order+id&ok=0");
            return;
        }

        try {
            int orderId = Integer.parseInt(idStr);
            OrderDao dao = new OrderDao();
            boolean ok = dao.deleteOrderCascade(orderId);
            if (ok) {
                resp.sendRedirect("orderList?msg=Order+deleted&ok=1");
            } else {
                resp.sendRedirect("orderList?msg=Delete+failed&ok=0");
            }
        } catch (NumberFormatException nfe) {
            resp.sendRedirect("orderList?msg=Invalid+order+id&ok=0");
        }
    }
}
