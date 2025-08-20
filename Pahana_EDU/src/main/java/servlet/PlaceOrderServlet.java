package servlet;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import bean.ItemBean;
import dao.ItemDao;

@WebServlet("/PlaceOrder")
public class PlaceOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String bookName = req.getParameter("bookName");
        int qty = Integer.parseInt(req.getParameter("qty"));

        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        String username = (String) session.getAttribute("username");

        if (role == null || !"customer".equals(role)) {
            resp.sendRedirect("login.jsp");
            return;
        }

        ItemDao dao = new ItemDao();
        ItemBean book = dao.getByName(bookName);

        if (book == null) {
            req.setAttribute("errorMessage", "Book not found!");
            req.getRequestDispatcher("orderForm.jsp").forward(req, resp);
            return;
        }

        if (book.getStock() < qty) {
            req.setAttribute("errorMessage", "Not enough stock!");
            req.getRequestDispatcher("orderForm.jsp").forward(req, resp);
            return;
        }

        // ❗ Here you would call OrderDao to save order + reduce stock
        req.setAttribute("successMessage", "Order placed for " + qty + " x " + book.getName());
        req.getRequestDispatcher("customerDashboard.jsp").forward(req, resp);
    }
}
