package bean;

import java.util.List;
import java.time.LocalDateTime;

public class OrderBean {
    private int orderId;
    private int accountNumber;
    private LocalDateTime orderDate;
    private double totalAmount;
    private List<OrderItemBean> items;

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getAccountNumber() { return accountNumber; }
    public void setAccountNumber(int accountNumber) { this.accountNumber = accountNumber; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public List<OrderItemBean> getItems() { return items; }
    public void setItems(List<OrderItemBean> items) { this.items = items; }
}
