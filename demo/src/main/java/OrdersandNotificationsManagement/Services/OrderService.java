package OrdersandNotificationsManagement.Services;

import OrdersandNotificationsManagement.Contracts.IOrderRepository;
import OrdersandNotificationsManagement.Contracts.IOrderService;
import OrdersandNotificationsManagement.Dtos.OrderToAddDto;
import OrdersandNotificationsManagement.Entities.*;
import OrdersandNotificationsManagement.Enums.NotificationTemplates;
import OrdersandNotificationsManagement.Repositories.OrderRepository;
import OrdersandNotificationsManagement.Repositories.ProductRepository;
import org.apache.el.parser.AstBracketSuffix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ShippingService shippingService;
    private  final CustomerService customerService;
    private  final  NotificationService notificationService;

    @Autowired
    public OrderService(
            OrderRepository orderRepository,
            CustomerService customerService,
            ProductRepository productRepository,
            ShippingService shippingService,
            NotificationService notificationService) {
        this.customerService = customerService;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.shippingService = shippingService;
        this.notificationService = notificationService;
    }
    public AbstractOrder placeOrder(OrderToAddDto dto) throws Exception {
        AbstractOrder order;
        if (dto.getSubOrders() == null){
            order = new SimpleOrder(dto.getCustomerId(), dto.getOrderItems());
            handleSimpleOrder((SimpleOrder) order);
        }
        else{
            var subOrders = dto.getSubOrders();
            var subSimpleOrders = new ArrayList<SimpleOrder>();
            for(var suborder: subOrders){
                var simpleOrder = new SimpleOrder(suborder.getCustomerId(), suborder.getOrderItems());
                subSimpleOrders.add(simpleOrder);
            }
            order = new CompositeOrder(dto.getCustomerId(), dto.getOrderItems(), subSimpleOrders);
            handleCompositeOrder((CompositeOrder) order);
        }
        notificationService.notify(order, NotificationTemplates.ORDER_PLACEMENT);
        return order;
    }
    public void shipOrder(int id) throws Exception {
        shippingService.shipOrder(id);
    }
    private void handleSimpleOrder(SimpleOrder order) throws Exception {
        var customerId = order.getCustomerId();
        var orderTotal = calculateOrderTotal(order);
        customerService.updateCustomerBalance(customerId, orderTotal);
        orderRepository.addOrder(order);
    }
    private void handleCompositeOrder(CompositeOrder order) throws Exception {
        for (AbstractOrder subOrder : order.getOrders()) {
            handleSimpleOrder((SimpleOrder) subOrder);
        }
        customerService.updateCustomerBalance(order.getCustomerId(), calculateOrderTotal(order));
        orderRepository.addOrder(order);
    }
    private double calculateOrderTotal(AbstractOrder order) {
        double total = 0.0;
        for (var orderItem : order.getOrderItems()) {
            var product = productRepository.getBySerialNumber(orderItem.getProductSerialNumber());
            total += orderItem.getQuantity() * product.getPrice();
        }
        return total;
    }
    public String displayOrderDetails(AbstractOrder order){
        return order.displayOrderInfo(productRepository);
    }
    public AbstractOrder getOrderById(int id) {
        return orderRepository.getOrderById(id);
    }


}
