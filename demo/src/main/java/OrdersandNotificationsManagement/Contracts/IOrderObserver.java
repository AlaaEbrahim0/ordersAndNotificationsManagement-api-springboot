package OrdersandNotificationsManagement.Contracts;

import OrdersandNotificationsManagement.Entities.AbstractOrder;

public interface IOrderObserver {
    void update(AbstractOrder order);
}