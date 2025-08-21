package servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import dao.ItemDao;
import bean.ItemBean;

@WebServlet("/orderForm")
public class OrderFormServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ItemDao dao = new ItemDao();
        List<ItemBean> items = dao.getAllItems();
        req.setAttribute("items", items);
        req.getRequestDispatcher("orderForm.jsp").forward(req, resp);
    }
}
