
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import util.DBConnection;
import bean.SignupBean;

public class SignupDao {

    public boolean registerCustomer(SignupBean customer) {
        boolean result = false;
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "INSERT INTO customer (user_name, address, telephone_number, password) VALUES (?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, customer.getUserName());
            ps.setString(2, customer.getAddress());
            ps.setString(3, customer.getTelephoneNumber());
            ps.setString(4, customer.getPassword());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

