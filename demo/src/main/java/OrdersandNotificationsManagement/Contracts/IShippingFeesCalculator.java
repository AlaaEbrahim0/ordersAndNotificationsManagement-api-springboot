package OrdersandNotificationsManagement.Contracts;

import OrdersandNotificationsManagement.Entities.AbstractOrder;

public interface IShippingFeesCalculator{
    double calculateShippingFee(AbstractOrder order);
}
