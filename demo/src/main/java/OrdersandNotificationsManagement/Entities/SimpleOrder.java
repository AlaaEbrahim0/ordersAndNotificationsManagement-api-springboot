package OrdersandNotificationsManagement.Entities;

import OrdersandNotificationsManagement.Repositories.ProductRepository;
import org.springframework.core.annotation.Order;

import java.lang.annotation.Annotation;
import java.util.List;

public class SimpleOrder extends AbstractOrder {

    public SimpleOrder(int customerId, List<OrderItem> products) {
        super(customerId, products);
    }
    @Override
    public String displayOrderInfo(ProductRepository productRepository) {
        var sb = new StringBuilder();
        sb.append("Order Details:\n");
        sb.append("Order ID: ").append(this.id).append("\n");
        sb.append("Customer ID: ").append(this.customerId).append("\n");
        sb.append("Products:\n");

        for (var orderItem : this.products) {
            var product = productRepository.getBySerialNumber(orderItem.getProductSerialNumber());
            sb.append("  - Product: ").append(product.getName())
                    .append(", Price: ").append(product.getPrice())
                    .append(", Quantity: ").append(orderItem.getQuantity())
                    .append("\n");
        }

        sb.append("Total Order Cost: ").append(calculateOrderTotal(this, productRepository)).append("\n");

        return sb.toString();
    }
}
