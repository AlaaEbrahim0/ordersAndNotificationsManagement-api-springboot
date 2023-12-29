package OrdersandNotificationsManagement.Entities;

import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

public class CompositeOrder extends AbstractOrder {
    public List<SimpleOrder > getOrders() {
        return orders;
    }
    private List<SimpleOrder> orders;

    public CompositeOrder(int customerId, List<OrderItem> products, List<SimpleOrder> orders) {
        super(customerId, products);
        this.orders = orders;
    }

    public void addOrder(SimpleOrder order){
        orders.add(order);
    }

    @Override
    public String displayOrderInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Main (Composite) Order:");
        var compositeSimpleOrder = new SimpleOrder(super.customerId, super.products);
        sb.append(compositeSimpleOrder.toString());
        sb.append("SubOrders: ");
        for (var order: orders){
            sb.append(order.toString());
        }
        return sb.toString();
    }
}
