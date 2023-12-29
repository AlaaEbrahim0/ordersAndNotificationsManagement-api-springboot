package OrdersandNotificationsManagement.Entities;

import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.util.List;

public class SimpleOrder extends AbstractOrder {

    public SimpleOrder(int customerId, List<OrderItem> products) {
        super(customerId, products);
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        for (var product : this.products){
            sb.append(product);
            sb.append("\n");
        }
        return "Order{" +
                "id=" + this.id +
                ", customer id=" + this.customerId +
                ", orderItems=" + sb +
                '}';
    }
    @Override
    public String displayOrderInfo() {
        return toString();
    }
}
