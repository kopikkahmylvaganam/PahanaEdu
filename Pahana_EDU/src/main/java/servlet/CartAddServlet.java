package servlet;

import java.io.IOException;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import bean.CartItemBean;
import bean.ItemBean;
import dao.ItemDao;

@WebServlet("/cart/add")
public class CartAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String bookName = req.getParameter("bookName");
        int qty = Integer.parseInt(req.getParameter("qty"));

        ItemDao itemDao = new ItemDao();
        ItemBean item = itemDao.getByName(bookName);
        if (item == null) {
            req.setAttribute("errorMessage", "Book not found!");
            req.getRequestDispatcher("orderForm").forward(req, resp);
            return;
        }
        if (qty <= 0) qty = 1;

        HttpSession session = req.getSession();
        List<CartItemBean> cart = (List<CartItemBean>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();

        // if already exists, increase qty
        boolean found = false;
        for (CartItemBean ci : cart) {
            if (ci.getItemId() == item.getItemId()) {
                ci.setQty(ci.getQty() + qty);
                found = true;
                break;
            }
        }
        if (!found) {
            CartItemBean ci = new CartItemBean();
            ci.setItemId(item.getItemId());
            ci.setName(item.getName());
            ci.setPrice(item.getPrice());
            ci.setQty(qty);
            cart.add(ci);
        }
        session.setAttribute("cart", cart);

        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
