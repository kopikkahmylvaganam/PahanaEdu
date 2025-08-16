package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.SignupDao;
import bean.SignupBean;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("username");
        String address = request.getParameter("address");
        String telephone = request.getParameter("telephone");
        String password = request.getParameter("password");

        // ---------- Backend Validation ----------
        if (userName == null || userName.trim().length() < 3) {
            request.setAttribute("errorMessage", "Username must be at least 3 characters.");
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
            return;
        }

        if (telephone == null || !telephone.matches("\\d{10}")) {
            request.setAttribute("errorMessage", "Invalid phone number. Must be exactly 10 digits.");
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
            return;
        }

        if (password == null || password.length() < 4) {
            request.setAttribute("errorMessage", "Password must be at least 6 characters.");
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
            return;
        }
        // ---------------------------------------

        SignupBean customer = new SignupBean();
        customer.setUserName(userName);
        customer.setAddress(address);
        customer.setTelephoneNumber(telephone);
        customer.setPassword(password);

        SignupDao dao = new SignupDao();
        boolean status = dao.registerCustomer(customer);

        if (status) {
            request.setAttribute("message", "Signup successful! Please login.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Signup failed. Try again!");
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
        }
    }
}
