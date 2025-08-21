package dao;

import java.sql.*;
import java.util.*;
import util.DBConnection;
import bean.CartItemBean;
import bean.OrderBean;
import bean.OrderItemBean;
import bean.ItemBean;

public class OrderDao {

    /**
     * Create order transaction:
     * 1) Lock items (SELECT ... FOR UPDATE)
     * 2) Validate stock
     * 3) Insert into orders
     * 4) Insert lines into order_items
     * 5) Reduce stock
     */
    public int createOrder(int accountNumber, List<CartItemBean> cart) {
        if (cart == null || cart.isEmpty()) return -1;

        Connection con = null;
        PreparedStatement psOrder = null;
        PreparedStatement psOrderItem = null;
        ResultSet rs = null;

        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            double total = 0.0;
            ItemDao itemDao = new ItemDao();

            // lock rows + validate stock + compute total (price snapshot)
            Map<Integer, Double> priceAtOrder = new HashMap<>();
            for (CartItemBean ci : cart) {
                ItemBean dbItem = itemDao.getByIdForUpdate(con, ci.getItemId());
                if (dbItem == null) {
                    con.rollback();
                    return -1; // item missing
                }
                if (dbItem.getStock() < ci.getQty()) {
                    con.rollback();
                    return -2; // not enough stock
                }
                priceAtOrder.put(ci.getItemId(), dbItem.getPrice());
                total += dbItem.getPrice() * ci.getQty();
            }

            // insert order (parent)
            String sqlOrder = "INSERT INTO orders(account_number, total_amount) VALUES (?, ?)";
            psOrder = con.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
            psOrder.setInt(1, accountNumber);
            psOrder.setDouble(2, total);
            psOrder.executeUpdate();

            rs = psOrder.getGeneratedKeys();
            int orderId = -1;
            if (rs.next()) orderId = rs.getInt(1);
            if (orderId <= 0) { con.rollback(); return -1; }

            // insert order items + reduce stock
            String sqlItem = "INSERT INTO order_items(order_id, item_id, quantity, price) VALUES (?, ?, ?, ?)";
            psOrderItem = con.prepareStatement(sqlItem);

            for (CartItemBean ci : cart) {
                double unitPrice = priceAtOrder.get(ci.getItemId());

                // insert line
                psOrderItem.setInt(1, orderId);
                psOrderItem.setInt(2, ci.getItemId());
                psOrderItem.setInt(3, ci.getQty());
                psOrderItem.setDouble(4, unitPrice);
                psOrderItem.addBatch();

                // reduce stock (same transaction)
                if (!itemDao.reduceStock(con, ci.getItemId(), ci.getQty())) {
                    con.rollback();
                    return -2; // safety: stock changed meanwhile
                }
            }
            psOrderItem.executeBatch();

            con.commit();
            return orderId;

        } catch (Exception e) {
            e.printStackTrace();
            try { if (con != null) con.rollback(); } catch (Exception ignored) {}
            return -1;
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (psOrderItem != null) psOrderItem.close(); } catch (Exception ignored) {}
            try { if (psOrder != null) psOrder.close(); } catch (Exception ignored) {}
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (Exception ignored) {}
        }
    }

    /** Customer → list own orders */
    public List<OrderBean> getOrdersByAccountNumber(int accountNumber) {
        List<OrderBean> list = new ArrayList<>();
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "SELECT order_id, order_date, total_amount " +
                         "FROM orders WHERE account_number=? ORDER BY order_date DESC";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, accountNumber);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        OrderBean o = new OrderBean();
                        o.setOrderId(rs.getInt("order_id"));
                        o.setAccountNumber(accountNumber);
                        o.setTotalAmount(rs.getDouble("total_amount"));
                        o.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
                        list.add(o);
                    }
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    /** Admin/Customer → get one order with items (for invoice/details) */
    public OrderBean getOrderWithItems(int orderId) {
        OrderBean order = null;
        try (Connection con = DBConnection.getInstance().getConnection()) {
            // header
            String so = "SELECT account_number, order_date, total_amount FROM orders WHERE order_id=?";
            try (PreparedStatement p1 = con.prepareStatement(so)) {
                p1.setInt(1, orderId);
                try (ResultSet rs = p1.executeQuery()) {
                    if (rs.next()) {
                        order = new OrderBean();
                        order.setOrderId(orderId);
                        order.setAccountNumber(rs.getInt("account_number"));
                        order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
                        order.setTotalAmount(rs.getDouble("total_amount"));
                    }
                }
            }
            if (order == null) return null;

            // lines
            String si = "SELECT oi.order_item_id, oi.item_id, i.name, oi.quantity, oi.price " +
                        "FROM order_items oi JOIN item i ON oi.item_id=i.item_id " +
                        "WHERE oi.order_id=?";
            List<OrderItemBean> items = new ArrayList<>();
            try (PreparedStatement p2 = con.prepareStatement(si)) {
                p2.setInt(1, orderId);
                try (ResultSet rs = p2.executeQuery()) {
                    while (rs.next()) {
                        OrderItemBean it = new OrderItemBean();
                        it.setOrderItemId(rs.getInt("order_item_id"));
                        it.setItemId(rs.getInt("item_id"));
                        it.setItemName(rs.getString("name"));
                        it.setQuantity(rs.getInt("quantity"));
                        it.setPrice(rs.getDouble("price"));
                        items.add(it);
                    }
                }
            }
            order.setItems(items);
        } catch (Exception e) { e.printStackTrace(); }
        return order;
    }

    /** Admin → list all orders */
    public List<OrderBean> getAllOrders() {
        List<OrderBean> list = new ArrayList<>();
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "SELECT order_id, account_number, order_date, total_amount " +
                         "FROM orders ORDER BY order_date DESC";
            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderBean o = new OrderBean();
                    o.setOrderId(rs.getInt("order_id"));
                    o.setAccountNumber(rs.getInt("account_number"));
                    o.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
                    o.setTotalAmount(rs.getDouble("total_amount"));
                    list.add(o);
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    /**
     * Admin → delete an order safely (order_items first, then orders)
     * Note: this does NOT re-stock items. Ask if you want re-stock logic.
     */
    public boolean deleteOrderCascade(int orderId) {
        Connection con = null;
        PreparedStatement psDelItems = null;
        PreparedStatement psDelOrder = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            // delete children
            String d1 = "DELETE FROM order_items WHERE order_id=?";
            psDelItems = con.prepareStatement(d1);
            psDelItems.setInt(1, orderId);
            psDelItems.executeUpdate();

            // delete parent
            String d2 = "DELETE FROM orders WHERE order_id=?";
            psDelOrder = con.prepareStatement(d2);
            psDelOrder.setInt(1, orderId);
            int rows = psDelOrder.executeUpdate();

            con.commit();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            try { if (con != null) con.rollback(); } catch (Exception ignore) {}
            return false;
        } finally {
            try { if (psDelOrder != null) psDelOrder.close(); } catch (Exception ignore) {}
            try { if (psDelItems != null) psDelItems.close(); } catch (Exception ignore) {}
            try {
                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (Exception ignore) {}
        }
    }
}
