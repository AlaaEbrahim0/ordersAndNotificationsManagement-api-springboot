package OrdersandNotificationsManagement.Repositories;


import OrdersandNotificationsManagement.Contracts.IOrderRepository;
import OrdersandNotificationsManagement.Entities.AbstractOrder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderRepository implements IOrderRepository {
    private List<AbstractOrder> orders;

    public OrderRepository() {
        orders = new ArrayList<>();
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
