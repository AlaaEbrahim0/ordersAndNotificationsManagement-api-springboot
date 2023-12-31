package OrdersandNotificationsManagement.Contracts;

import OrdersandNotificationsManagement.Entities.AbstractOrder;

public interface IShippingService{
    void shipOrder(int orderId) throws Exception;
    double getShippingFees(AbstractOrder order);
}
