package dao;

import java.sql.*;
import java.util.*;
import util.DBConnection;
import bean.CustomerBean;

public class CustomerDao {

    // Get all customers (without password for admin)
    public List<CustomerBean> getAllCustomers() {
        List<CustomerBean> list = new ArrayList<>();
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "SELECT account_number, user_name, address, telephone_number FROM customer";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CustomerBean c = new CustomerBean();
                c.setAccountNumber(rs.getInt("account_number"));
                c.setUserName(rs.getString("user_name"));
                c.setAddress(rs.getString("address"));
                c.setTelephoneNumber(rs.getString("telephone_number"));
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get customer by ID
    public CustomerBean getCustomerById(int id, boolean includePassword) {
        CustomerBean c = null;
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = includePassword
                ? "SELECT * FROM customer WHERE account_number=?"
                : "SELECT account_number, user_name, address, telephone_number FROM customer WHERE account_number=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new CustomerBean();
                c.setAccountNumber(rs.getInt("account_number"));
                c.setUserName(rs.getString("user_name"));
                c.setAddress(rs.getString("address"));
                c.setTelephoneNumber(rs.getString("telephone_number"));
                if (includePassword) {
                    c.setPassword(rs.getString("password"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    // ✅ Get customer by username (needed for profile)
    public CustomerBean getCustomerByUserName(String userName) {
        CustomerBean c = null;
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "SELECT * FROM customer WHERE user_name=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new CustomerBean();
                c.setAccountNumber(rs.getInt("account_number"));
                c.setUserName(rs.getString("user_name"));
                c.setAddress(rs.getString("address"));
                c.setTelephoneNumber(rs.getString("telephone_number"));
                c.setPassword(rs.getString("password"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    // Update customer
    public boolean updateCustomer(CustomerBean c, boolean includePassword) {
        boolean status = false;
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql;
            if (includePassword) {
                sql = "UPDATE customer SET user_name=?, address=?, telephone_number=?, password=? WHERE account_number=?";
            } else {
                sql = "UPDATE customer SET user_name=?, address=?, telephone_number=? WHERE account_number=?";
            }
            PreparedStatement ps = con.prepareStatement(sql);
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
            if (rows > 0) status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    // Delete customer
    public boolean deleteCustomer(int id) {
        boolean status = false;
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "DELETE FROM customer WHERE account_number=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0) status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
