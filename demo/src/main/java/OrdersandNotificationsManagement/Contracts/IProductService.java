package OrdersandNotificationsManagement.Contracts;

import OrdersandNotificationsManagement.Entities.AbstractOrder;
import OrdersandNotificationsManagement.Entities.Product;

import java.util.List;
import java.util.Map;

public interface IProductService {
    List<Product> getAllProducts();
    Map<String, Long> getRemainingPartsCountByCategory();
     void decreaseProductQuantity(AbstractOrder order) throws Exception;
     Product getBySerialNumber(String  serialNumber);
}