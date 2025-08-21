package servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.ItemDao;
import bean.ItemBean;

@WebServlet("/books")
public class CustomerBookListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // (Optional) customer-only guard
        HttpSession session = req.getSession(false);
        if (session == null || !"customer".equals(session.getAttribute("role"))) {
            resp.sendRedirect("login.jsp");
            return;
        }

        ItemDao dao = new ItemDao();
        List<ItemBean> items = dao.getAllItems();
        req.setAttribute("items", items);
        req.getRequestDispatcher("customerBooks.jsp").forward(req, resp);
    }
}
