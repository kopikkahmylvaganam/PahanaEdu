package servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import dao.LoginDao;
import bean.CustomerBean;
import bean.LoginBean;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        LoginBean loginBean = new LoginBean();
        loginBean.setUserName(userName);
        loginBean.setPassword(password);

        LoginDao loginDao = new LoginDao();

        try {
            // ✅ Admin Login
            if (loginDao.validateAdmin(loginBean)) {
                HttpSession session = request.getSession();
                session.setAttribute("role", "admin");
                session.setAttribute("username", userName);
                response.sendRedirect("adminDashboard.jsp");
                return;
            }

            // ✅ Customer Login
            if (loginDao.validateCustomer(loginBean)) {
                HttpSession session = request.getSession();
                session.setAttribute("role", "customer");
                session.setAttribute("username", userName);

                // Fetch full customer details
                CustomerBean customer = loginDao.getCustomerDetails(userName);

                if (customer != null) {
                    session.setAttribute("customer", customer);
                    response.sendRedirect("customerDashboard.jsp");
                } else {
                    // Customer not found in DB
                    session.invalidate();
                    response.sendRedirect("login.jsp?error=customerNotFound");
                }
            } else {
                request.setAttribute("errorMessage", "Invalid Username or Password!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=exception");
        }
    }
}
