package dao;

import java.sql.*;
import java.util.*;
import util.DBConnection;
import bean.ItemBean;

public class ItemDao {

    // Add new item
    public boolean insert(ItemBean i) {
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "INSERT INTO item(name, price, stock) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, i.getName());
            ps.setDouble(2, i.getPrice());
            ps.setInt(3, i.getStock());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    // Get all items
    public List<ItemBean> getAllItems() {
        List<ItemBean> list = new ArrayList<>();
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "SELECT * FROM item";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ItemBean i = new ItemBean();
                i.setItemId(rs.getInt("item_id"));
                i.setName(rs.getString("name"));
                i.setPrice(rs.getDouble("price"));
                i.setStock(rs.getInt("stock"));
                list.add(i);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    // Get item by ID
    public ItemBean getById(int id) {
        ItemBean i = null;
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "SELECT * FROM item WHERE item_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                i = new ItemBean();
                i.setItemId(rs.getInt("item_id"));
                i.setName(rs.getString("name"));
                i.setPrice(rs.getDouble("price"));
                i.setStock(rs.getInt("stock"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return i;
    }
    // Get item by name
    public ItemBean getByName(String name) {
        ItemBean i = null;
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "SELECT * FROM item WHERE name=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                i = new ItemBean();
                i.setItemId(rs.getInt("item_id"));
                i.setName(rs.getString("name"));
                i.setPrice(rs.getDouble("price"));
                i.setStock(rs.getInt("stock"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return i;
    }


    // Update item
    public boolean update(ItemBean i) {
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "UPDATE item SET name=?, price=?, stock=? WHERE item_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, i.getName());
            ps.setDouble(2, i.getPrice());
            ps.setInt(3, i.getStock());
            ps.setInt(4, i.getItemId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }

    // Delete item
    public boolean delete(int id) {
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "DELETE FROM item WHERE item_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); }
        return false;
    }
    
    // Lock item for update (used in transactions)
    public ItemBean getByIdForUpdate(Connection con, int id) throws SQLException {
        String sql = "SELECT * FROM item WHERE item_id=? FOR UPDATE";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ItemBean i = new ItemBean();
                    i.setItemId(rs.getInt("item_id"));
                    i.setName(rs.getString("name"));
                    i.setPrice(rs.getDouble("price"));
                    i.setStock(rs.getInt("stock"));
                    return i;
                }
            }
        }
        return null;
    }

    // ✅ Reduce stock after order
    public boolean reduceStock(Connection con, int itemId, int qty) throws SQLException {
        String sql = "UPDATE item SET stock = stock - ? WHERE item_id=? AND stock >= ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, qty);
            ps.setInt(2, itemId);
            ps.setInt(3, qty);
            return ps.executeUpdate() > 0; // true only if stock reduced successfully
        }
    }
}
