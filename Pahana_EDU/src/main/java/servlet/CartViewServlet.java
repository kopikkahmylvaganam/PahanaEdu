package servlet;

import java.io.IOException;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import bean.CartItemBean;

@WebServlet("/cart")
public class CartViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<CartItemBean> cart = (List<CartItemBean>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();
        req.setAttribute("cart", cart);
        req.getRequestDispatcher("cart.jsp").forward(req, resp);
    }
}
