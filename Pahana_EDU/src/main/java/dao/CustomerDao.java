package dao;

import java.sql.*;
import java.util.*;
import util.DBConnection;
import bean.CustomerBean;

public class CustomerDao {

    // All customers (no password)
    public List<CustomerBean> getAllCustomers() {
        List<CustomerBean> list = new ArrayList<>();
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "SELECT account_number, username, address, telephonenumber FROM customer";
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    CustomerBean c = new CustomerBean();
                    c.setAccountNumber(rs.getInt("account_number"));
                    c.setUserName(rs.getString("username"));
                    c.setAddress(rs.getString("address"));
                    c.setTelephoneNumber(rs.getString("telephonenumber"));
                    list.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get by ID (includePassword controls password column)
    public CustomerBean getCustomerById(int id, boolean includePassword) {
        CustomerBean c = null;
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = includePassword
                ? "SELECT account_number, username, address, telephonenumber, password FROM customer WHERE account_number=?"
                : "SELECT account_number, username, address, telephonenumber FROM customer WHERE account_number=?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        c = new CustomerBean();
                        c.setAccountNumber(rs.getInt("account_number"));
                        c.setUserName(rs.getString("username"));
                        c.setAddress(rs.getString("address"));
                        c.setTelephoneNumber(rs.getString("telephonenumber"));
                        if (includePassword) {
                            c.setPassword(rs.getString("password"));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    // Get by username (for profile)
    public CustomerBean getCustomerByUserName(String userName) {
        CustomerBean c = null;
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "SELECT account_number, username, address, telephonenumber, password " +
                         "FROM customer WHERE username=?";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, userName);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        c = new CustomerBean();
                        c.setAccountNumber(rs.getInt("account_number"));
                        c.setUserName(rs.getString("username"));
                        c.setAddress(rs.getString("address"));
                        c.setTelephoneNumber(rs.getString("telephonenumber"));
                        c.setPassword(rs.getString("password"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    // Update (admin: no password, customer: with password) — treat “no change” as success
    public boolean updateCustomer(CustomerBean c, boolean includePassword) {
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = includePassword
                ? "UPDATE customer SET username=?, address=?, telephonenumber=?, password=? WHERE account_number=?"
                : "UPDATE customer SET username=?, address=?, telephonenumber=? WHERE account_number=?";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, c.getUserName());
                ps.setString(2, c.getAddress());
                ps.setString(3, c.getTelephoneNumber());
                if (includePassword) {
                    ps.setString(4, c.getPassword());
                    ps.setInt(5, c.getAccountNumber());
                } else {
                    ps.setInt(4, c.getAccountNumber());
                }
                int rows = ps.executeUpdate();
                if (rows > 0) return true;

                // maybe no change; ensure row exists
                try (PreparedStatement chk = con.prepareStatement(
                        "SELECT COUNT(*) FROM customer WHERE account_number=?")) {
                    chk.setInt(1, c.getAccountNumber());
                    try (ResultSet rs = chk.executeQuery()) {
                        if (rs.next() && rs.getInt(1) == 1) return true;
                    }
                }
            }
        } catch (SQLException e) {
            // If you still have UNIQUE on password and password becomes NULL, you’ll hit here.
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Delete
    public boolean deleteCustomer(int id) {
        try (Connection con = DBConnection.getInstance().getConnection();
             PreparedStatement ps = con.prepareStatement("DELETE FROM customer WHERE account_number=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
