package OrdersandNotificationsManagement.Services;

import OrdersandNotificationsManagement.Contracts.IShippingService;
import OrdersandNotificationsManagement.Entities.AbstractOrder;
import OrdersandNotificationsManagement.Entities.CompositeOrder;
import OrdersandNotificationsManagement.Entities.SimpleOrder;
import OrdersandNotificationsManagement.Enums.NotificationTemplates;
import OrdersandNotificationsManagement.Repositories.OrderRepository;
import OrdersandNotificationsManagement.Utilities.FakeShippingFeesCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ShippingService implements IShippingService {
    private  final OrderRepository orderRepository;
    private  final FakeShippingFeesCalculator shippingFeeCalculator;
    private  final CustomerService customerService;

    private  final NotificationService notificationService;
    @Autowired
    public ShippingService(OrderRepository orderRepository, FakeShippingFeesCalculator shippingFeeCalculator, CustomerService customerService, NotificationService notificationService) {
        this.orderRepository = orderRepository;
        this.shippingFeeCalculator = shippingFeeCalculator;
        this.customerService = customerService;
        this.notificationService = notificationService;
    }
    @Override
    public void shipOrder(int orderId) throws Exception {
        AbstractOrder order = orderRepository.getOrderById(orderId);
        if (order != null) {
            double shippingFee = shippingFeeCalculator.calculateShippingFee(order);

            if (order instanceof SimpleOrder) {
                handleShippingForSimpleOrder((SimpleOrder) order, shippingFee);
            } else if (order instanceof CompositeOrder) {
                handleShippingForCompositeOrder((CompositeOrder) order, shippingFee);
            }
        } else {
            throw new Exception("Order not found");
        }
        notificationService.notify(order, NotificationTemplates.ORDER_SHIPPING);
    }
    @Override
    public double getShippingFees(AbstractOrder order) {
        return shippingFeeCalculator.calculateShippingFee(order);
    }
    private void handleShippingForSimpleOrder(SimpleOrder order, double shippingFee) throws Exception {
        int customerId = order.getCustomerId();
        customerService.updateCustomerBalance(customerId, shippingFee);
    }
    private void handleShippingForCompositeOrder(CompositeOrder order, double shippingFee) throws Exception {
        for (SimpleOrder subOrder : order.getOrders()) {
            handleShippingForSimpleOrder(subOrder, shippingFee);
        }
        customerService.updateCustomerBalance(order.getCustomerId(), shippingFee);
    }
}
