package bean;

import java.util.List;

public class OrderBean {
    private int orderId;
    private int accountNumber;  // ✅ matches customer.account_number
    private double totalAmount;
    private List<OrderItemBean> items;

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public int getAccountNumber() { return accountNumber; }
    public void setAccountNumber(int accountNumber) { this.accountNumber = accountNumber; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public List<OrderItemBean> getItems() { return items; }
    public void setItems(List<OrderItemBean> items) { this.items = items; }
}
