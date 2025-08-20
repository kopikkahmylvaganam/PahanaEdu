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

        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String telephonenumber = request.getParameter("telephonenumber");
        String password = request.getParameter("password");

        // ---------- Backend Validation ----------
        if (username == null || username.trim().length() < 3) {
            request.setAttribute("errorMessage", "Username must be at least 3 characters.");
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
            return;
        }

        if (telephonenumber == null || !telephonenumber.matches("\\d{10}")) {
            request.setAttribute("errorMessage", "Invalid phone number. Must be exactly 10 digits.");
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
            return;
        }

        if (password == null || password.length() < 4) {
            request.setAttribute("errorMessage", "Password must be at least 4 characters.");
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
            return;
        }

        if (username.length() > 45 || address.length() > 100 || telephonenumber.length() > 10 || password.length() > 50) {
            request.setAttribute("errorMessage", "Input exceeds allowed length for one or more fields.");
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
            return;
        }
        // ---------------------------------------

        SignupBean customer = new SignupBean();
        customer.setUserName(username);
        customer.setAddress(address);
        customer.setTelephoneNumber(telephonenumber);
        customer.setPassword(password);

        SignupDao dao = new SignupDao();
        boolean status = dao.registerCustomer(customer);

        if (status) {
            request.setAttribute("message", "Signup successful! You will be redirected to login.");
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Signup failed. Username may already exist. Please try a different username.");
            request.getRequestDispatcher("Signup.jsp").forward(request, response);
        }
    }
}