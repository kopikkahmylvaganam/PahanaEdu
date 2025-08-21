package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.DBConnection;
import bean.CustomerBean;
import bean.LoginBean;

public class LoginDao {

    // Customer login check
    public boolean validateCustomer(LoginBean loginBean) {
        boolean status = false;
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "SELECT 1 FROM customer WHERE username=? AND password=?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, loginBean.getUserName());
                ps.setString(2, loginBean.getPassword());
                try (ResultSet rs = ps.executeQuery()) {
                    status = rs.next();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // Fetch full customer details by username
    public CustomerBean getCustomerDetails(String username) {
        CustomerBean customer = null;
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "SELECT account_number, username, address, telephonenumber, password " +
                         "FROM customer WHERE username=?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        customer = new CustomerBean();
                        customer.setAccountNumber(rs.getInt("account_number"));
                        customer.setUserName(rs.getString("username"));
                        customer.setAddress(rs.getString("address"));
                        customer.setTelephoneNumber(rs.getString("telephonenumber"));
                        customer.setPassword(rs.getString("password"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    // Admin login: fixed admin/admin
    public boolean validateAdmin(LoginBean loginBean) {
        return "admin".equalsIgnoreCase(loginBean.getUserName())
            && "admin".equals(loginBean.getPassword());
    }
}
