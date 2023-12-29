package OrdersandNotificationsManagement.Contracts;

import OrdersandNotificationsManagement.Entities.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> getAll();
    Product getBySerialNumber(String serialNumber);
}
