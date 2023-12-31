package OrdersandNotificationsManagement.Contracts;

import OrdersandNotificationsManagement.Dtos.OrderToAddDto;
import OrdersandNotificationsManagement.Entities.AbstractOrder;

public interface IOrderService {

    AbstractOrder placeOrder(OrderToAddDto dto) throws Exception;

    AbstractOrder getOrderById(int id);
}

