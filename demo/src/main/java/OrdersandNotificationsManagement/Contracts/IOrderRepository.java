package OrdersandNotificationsManagement.Contracts;

import OrdersandNotificationsManagement.Entities.AbstractOrder;

public interface IOrderRepository {
    void addOrder(AbstractOrder order) throws Exception;
    AbstractOrder getOrderById(int id);
}
