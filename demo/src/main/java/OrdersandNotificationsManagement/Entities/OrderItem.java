package OrdersandNotificationsManagement.Entities;

public class  OrderItem {
    public String getProductSerialNumber() {
        return productSerialNumber;
    }
    private String productSerialNumber;
    private int orderId;
    public OrderItem(String productId, int orderId, int quantity) {
        this.productSerialNumber = productId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "productSerialNumber='" + productSerialNumber + '\'' +
                ", quantity=" + quantity +
                '}';
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    private int quantity;
}
