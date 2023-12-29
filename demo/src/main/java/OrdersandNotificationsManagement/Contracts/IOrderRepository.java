package OrdersandNotificationsManagement.Contracts;

import OrdersandNotificationsManagement.Entities.AbstractOrder;
import OrdersandNotificationsManagement.Entities.SimpleOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IOrderRepository {
    void addOrder(AbstractOrder order) throws Exception;
    AbstractOrder getOrderById(int id);
}
