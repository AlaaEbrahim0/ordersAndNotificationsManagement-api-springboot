package OrdersandNotificationsManagement.Services;

import OrdersandNotificationsManagement.Contracts.IOrderRepository;
import OrdersandNotificationsManagement.Dtos.OrderToAddDto;
import OrdersandNotificationsManagement.Entities.*;
import OrdersandNotificationsManagement.Repositories.CustomerRepository;
import OrdersandNotificationsManagement.Repositories.OrderRepository;
import OrdersandNotificationsManagement.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
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
        return order;
    }
    private void handleSimpleOrder(SimpleOrder order) throws Exception {
        var customerId = order.getCustomerId();
        var orderTotal = calculateOrderTotal(order);
        updateCustomerBalance(customerId, orderTotal);
        orderRepository.addOrder(order);
    }
    private void handleCompositeOrder(CompositeOrder order) throws Exception {
        for (AbstractOrder subOrder : order.getOrders()) {
            handleSimpleOrder((SimpleOrder) subOrder);
        }
        updateCustomerBalance(order.getCustomerId(), calculateOrderTotal(order));
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
    private void updateCustomerBalance(int customerId, double amount) throws Exception {
        Customer customer = customerRepository.getCustomerById(customerId);
        if (customer != null) {
            double newBalance = customer.getBalance() - amount;
            if (newBalance >= 0) {
                customer.setBalance(newBalance);
            } else {
                throw new Exception("Insufficient funds");
            }
        } else {
            throw new Exception("Customer not found");
        }
    }
    public AbstractOrder getOrderById(int id) {
        return orderRepository.getOrderById(id);
    }

}
