package OrdersandNotificationsManagement.Entities;

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