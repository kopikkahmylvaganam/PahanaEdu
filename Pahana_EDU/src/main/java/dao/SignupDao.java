package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import util.DBConnection;
import bean.SignupBean;

public class SignupDao {
    public boolean registerCustomer(SignupBean customer) {
        boolean result = false;
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "INSERT INTO customer (username, address, telephonenumber, password) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customer.getUserName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getTelephoneNumber());
            ps.setString(4, customer.getPassword());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                result = true;
            }
        } catch (SQLException e) {
            if (e.getSQLState().equals("23000")) { // Duplicate entry error
                return false; // Indicate duplicate username
            }
            e.printStackTrace();
            return false;
        }
        return result;
    }
}