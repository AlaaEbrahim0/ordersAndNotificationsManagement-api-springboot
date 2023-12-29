package OrdersandNotificationsManagement.Repositories;
import OrdersandNotificationsManagement.Contracts.IProductRepository;
import OrdersandNotificationsManagement.Entities.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class ProductRepository implements IProductRepository {
    private ArrayList<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
        // Generate 10 additional products
        for (int i = 1; i <= 10; i++) {
            String serialNumber = "serial-" + i;
            String productName = "prod-" + String.format("%02d", i);
            String vendor = "vendor-" + String.format("%02d", i);
            String category = i % 2 == 0 ? "category01" : "category02";
            int quantity = (i * 10) + 50;
            int price = (i * 5) + 100;

            products.add(new Product(serialNumber, productName, vendor, category, quantity, price));
        }};

    public List<Product> getAll() {
        return products;
    }

    @Override
    public Product getBySerialNumber(String serialNumber) {
        for (var prod : products){
            if (prod.getSerialNumber().equals(serialNumber)){
                return prod;
            }
        }
        return null;
    }
}
