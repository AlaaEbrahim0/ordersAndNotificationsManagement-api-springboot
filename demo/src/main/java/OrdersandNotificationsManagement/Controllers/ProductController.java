package OrdersandNotificationsManagement.Controllers;

import OrdersandNotificationsManagement.Entities.Product;
import OrdersandNotificationsManagement.Repositories.ProductRepository;
import OrdersandNotificationsManagement.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/remaining-parts-count")
    public Map<String, Long> getRemainingPartsCountByCategory() {
        return productService.getRemainingPartsCountByCategory();
    }

}