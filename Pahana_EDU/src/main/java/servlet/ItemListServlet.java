package servlet;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import dao.ItemDao;
import bean.ItemBean;

@WebServlet("/itemList")
public class ItemListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        ItemDao dao = new ItemDao();
        List<ItemBean> list = dao.getAllItems();
        req.setAttribute("items", list);
        req.getRequestDispatcher("itemList.jsp").forward(req, resp);
    }
}
