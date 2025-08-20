package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import util.DBConnection;
import bean.CustomerBean;
import bean.LoginBean;

public class LoginDao {

    // Check login for Customer
    public boolean validateCustomer(LoginBean loginBean) {
        boolean status = false;
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "SELECT * FROM customer WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, loginBean.getUserName());
            ps.setString(2, loginBean.getPassword());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                status = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // Fetch full customer details
    public CustomerBean getCustomerDetails(String username) {
        CustomerBean customer = null;
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "SELECT * FROM customer WHERE username=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                customer = new CustomerBean();
                customer.setAccountNumber(rs.getInt("account_number"));
                customer.setUserName(rs.getString("username"));
                customer.setAddress(rs.getString("address"));
                customer.setTelephoneNumber(rs.getString("telephone"));
                customer.setPassword(rs.getString("password")); // ✅ Added
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    // Admin login check
    public boolean validateAdmin(LoginBean loginBean) {
        return loginBean.getUserName().equalsIgnoreCase("admin")
                && loginBean.getPassword().equals("admin");
    }
}
