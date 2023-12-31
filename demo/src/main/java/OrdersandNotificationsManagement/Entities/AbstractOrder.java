package OrdersandNotificationsManagement.Entities;

import OrdersandNotificationsManagement.Enums.OrderState;
import OrdersandNotificationsManagement.Repositories.ProductRepository;
import OrdersandNotificationsManagement.Services.ProductService;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.core.annotation.Order;

import java.util.List;

public abstract class   AbstractOrder
{
    public int getId() {
        return id;
    }

    public static int idCounter = 1;

    public int getCustomerId() {
        return customerId;
    }

    public void setId(int id) {
        this.id = id;
    }
    public AbstractOrder(int customerId,  List<OrderItem> products) {
        this.customerId = customerId;
        this.products = products;
        id = idCounter;
        idCounter++;
    }

    protected double calculateOrderTotal(AbstractOrder order, ProductService productService) {
        double total = 0.0;
        for (var orderItem : order.getOrderItems()) {
            var product = productService.getBySerialNumber(orderItem.getProductSerialNumber());
            total += orderItem.getQuantity() * product.getPrice();
        }
        return total;
    }

    protected int id;

    protected int customerId;
    public List<OrderItem> getOrderItems() {
        return products;
    }

    public void addOrderItem(OrderItem orderItem){
        products.add(orderItem);
    }
    protected List<OrderItem> products;
    public abstract String displayOrderInfo(ProductService productService);
}