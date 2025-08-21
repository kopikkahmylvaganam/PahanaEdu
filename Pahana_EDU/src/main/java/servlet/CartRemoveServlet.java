package servlet;

import java.io.IOException;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import bean.CartItemBean;

@WebServlet("/cart/remove")
public class CartRemoveServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int itemId = Integer.parseInt(req.getParameter("itemId"));

        HttpSession session = req.getSession();
        List<CartItemBean> cart = (List<CartItemBean>) session.getAttribute("cart");
        if (cart != null) {
            cart.removeIf(ci -> ci.getItemId() == itemId);
        }
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
