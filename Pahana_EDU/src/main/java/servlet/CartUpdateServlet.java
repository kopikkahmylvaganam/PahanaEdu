package servlet;

import java.io.IOException;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import bean.CartItemBean;

@WebServlet("/cart/update")
public class CartUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int itemId = Integer.parseInt(req.getParameter("itemId"));
        int qty = Integer.parseInt(req.getParameter("qty"));

        HttpSession session = req.getSession();
        List<CartItemBean> cart = (List<CartItemBean>) session.getAttribute("cart");
        if (cart != null) {
            Iterator<CartItemBean> it = cart.iterator();
            while (it.hasNext()) {
                CartItemBean ci = it.next();
                if (ci.getItemId() == itemId) {
                    if (qty <= 0) it.remove();
                    else ci.setQty(qty);
                    break;
                }
            }
        }
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
