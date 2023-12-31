package OrdersandNotificationsManagement.Entities;

import OrdersandNotificationsManagement.Contracts.IOrderObserver;
import OrdersandNotificationsManagement.Repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class CompositeOrder extends AbstractOrder {
    public List<SimpleOrder > getOrders() {
        return orders;
    }
    private List<SimpleOrder> orders;

    private final List<IOrderObserver> observers = new ArrayList<>();

    public CompositeOrder(int customerId, List<OrderItem> products, List<SimpleOrder> orders) {
        super(customerId, products);
        this.orders = orders;
    }
    public void addOrder(SimpleOrder order){
        orders.add(order);
    }

    @Override
    public String displayOrderInfo(ProductRepository productRepository) {
        StringBuilder sb = new StringBuilder();
        sb.append("Main (Composite) Order:\n");
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

        sb.append("SubOrders:\n");
        for (var order : orders) {
            sb.append(order.displayOrderInfo(productRepository));
        }

        return sb.toString();
    }
}
