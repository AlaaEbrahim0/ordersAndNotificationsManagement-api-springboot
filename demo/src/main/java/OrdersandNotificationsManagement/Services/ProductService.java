package OrdersandNotificationsManagement.Services;

import OrdersandNotificationsManagement.Contracts.IProductService;
import OrdersandNotificationsManagement.Entities.AbstractOrder;
import OrdersandNotificationsManagement.Entities.Product;
import OrdersandNotificationsManagement.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAll();
    }


    @Override
    public Map<String, Long> getRemainingPartsCountByCategory() {
        List<Product> allProducts = productRepository.getAll();
        return allProducts.stream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));
    }

    @Override
    public void decreaseProductQuantity(AbstractOrder order) throws Exception {

    }

    @Override
    public Product getBySerialNumber(String serialNumber) {
        return  productRepository.getBySerialNumber(serialNumber);
    }

}