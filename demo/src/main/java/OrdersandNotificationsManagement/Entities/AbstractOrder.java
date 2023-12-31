package OrdersandNotificationsManagement.Entities;

import OrdersandNotificationsManagement.Enums.OrderState;
import org.springframework.core.annotation.Order;

import java.util.List;

public abstract class AbstractOrder
{
    public int getId() {
        return id;
    }

    public static int idCounter = 1;

    public int getCustomerId() {
        return customerId;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderItem> getProducts() {
        return products;
    }

    public void setProducts(List<OrderItem> products) {
        this.products = products;
    }

    private OrderState orderState = OrderState.UNPROCESSED;

    public AbstractOrder(int customerId, List<OrderItem> products) {
        this.customerId = customerId;
        this.products = products;
        id = idCounter;
        idCounter++;
    }

    protected int id;

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    protected int customerId;

    public List<OrderItem> getOrderItems() {
        return products;
    }

    protected List<OrderItem> products;
    public abstract String displayOrderInfo();
}