package OrdersandNotificationsManagement.Contracts;

import OrdersandNotificationsManagement.Entities.Product;

import java.util.List;
import java.util.Map;

public interface IProductService {
    List<Product> getAllProducts();
    Map<String, Long> getRemainingPartsCountByCategory();
}