package OrdersandNotificationsManagement.Repositories;


import OrdersandNotificationsManagement.Contracts.IOrderRepository;
import OrdersandNotificationsManagement.Entities.AbstractOrder;
import OrdersandNotificationsManagement.Entities.CompositeOrder;
import OrdersandNotificationsManagement.Entities.OrderItem;
import OrdersandNotificationsManagement.Entities.SimpleOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderRepository implements IOrderRepository {
    private List<AbstractOrder> orders;

    public OrderRepository() {
        orders = new ArrayList<>();

        var simpleOrder = new SimpleOrder(1, new ArrayList<>());
        simpleOrder.addOrderItem(new OrderItem("S001", simpleOrder.getId(), 2));
        simpleOrder.addOrderItem(new OrderItem("S002", simpleOrder.getId(), 3));
        orders.add(simpleOrder);

        var compositeOrder = new CompositeOrder(2, new ArrayList<>(), new ArrayList<>());
        compositeOrder.addOrderItem(new OrderItem("S003", compositeOrder.getId(), 2));

        simpleOrder = new SimpleOrder(3, new ArrayList<>());
        simpleOrder.addOrderItem(new OrderItem("S002", simpleOrder.getId(), 4));
        compositeOrder.addOrder(simpleOrder);
        orders.add(simpleOrder);

        simpleOrder = new SimpleOrder(4, new ArrayList<>());
        simpleOrder.addOrderItem(new OrderItem("S006", simpleOrder.getId(), 1));
        simpleOrder.addOrderItem(new OrderItem("S012", simpleOrder.getId(), 3));
        orders.add(simpleOrder);

        compositeOrder.addOrder(simpleOrder);
        orders.add(compositeOrder);

    }

    @Override
    public void addOrder(AbstractOrder order) throws Exception {
        orders.add(order);
    }

    @Override
    public AbstractOrder getOrderById(int id) {
        for (AbstractOrder order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

}
