package servlet;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import dao.ItemDao;
import bean.ItemBean;

@WebServlet("/editItem")
public class ItemEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ItemDao dao = new ItemDao();
        ItemBean i = dao.getById(id);
        req.setAttribute("item", i);
        req.getRequestDispatcher("editItem.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("itemId"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int stock = Integer.parseInt(req.getParameter("stock"));

        ItemBean i = new ItemBean();
        i.setItemId(id);
        i.setName(name);
        i.setPrice(price);
        i.setStock(stock);

        ItemDao dao = new ItemDao();
        dao.update(i);

        resp.sendRedirect("itemList");
    }
}
