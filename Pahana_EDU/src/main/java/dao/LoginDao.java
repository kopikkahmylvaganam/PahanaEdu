package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import util.DBConnection;
import bean.LoginBean;

public class LoginDao {

    // Check login for Customer
    public boolean validateCustomer(LoginBean loginBean) {
        boolean status = false;
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "SELECT * FROM customer WHERE user_name=? AND password=?";
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

    // Admin login check (fixed password = "admin")
    public boolean validateAdmin(LoginBean loginBean) {
        return loginBean.getUserName().equalsIgnoreCase("admin")
                && loginBean.getPassword().equals("admin");
    }
}
