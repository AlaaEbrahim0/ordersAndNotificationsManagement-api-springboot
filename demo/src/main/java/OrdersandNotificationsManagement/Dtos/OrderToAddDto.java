package OrdersandNotificationsManagement.Dtos;

import OrdersandNotificationsManagement.Entities.AbstractOrder;
import OrdersandNotificationsManagement.Entities.OrderItem;
import OrdersandNotificationsManagement.Entities.Product;
import org.springframework.core.annotation.Order;

import java.util.List;

public class OrderToAddDto {
    public int getCustomerId() {
        return customerId;
    }
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public List<OrderToAddDto> getSubOrders() {
        return subOrders;
    }
    private int customerId;
    private List<OrderItem> orderItems;
    private List<OrderToAddDto> subOrders;


}
