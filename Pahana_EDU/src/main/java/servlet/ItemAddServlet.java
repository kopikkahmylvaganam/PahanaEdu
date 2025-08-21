package servlet;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import dao.ItemDao;
import bean.ItemBean;

@WebServlet("/addItem")
public class ItemAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int stock = Integer.parseInt(req.getParameter("stock"));

        ItemBean i = new ItemBean();
        i.setName(name);
        i.setPrice(price);
        i.setStock(stock);

        ItemDao dao = new ItemDao();
        dao.insert(i);

        resp.sendRedirect("itemList");
    }
}
