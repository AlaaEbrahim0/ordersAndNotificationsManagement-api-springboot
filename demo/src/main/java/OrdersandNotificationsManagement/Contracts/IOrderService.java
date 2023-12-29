package OrdersandNotificationsManagement.Contracts;

import OrdersandNotificationsManagement.Dtos.OrderToAddDto;

public interface IOrderService {
    void PlaceSimpleOrder(OrderToAddDto dto);
    void PlaceCompositeOrder();

}
