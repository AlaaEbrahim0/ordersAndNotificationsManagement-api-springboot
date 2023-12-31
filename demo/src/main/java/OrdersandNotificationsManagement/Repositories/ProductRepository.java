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

        products.add(new Product("S001", "Laptop", "BrandX", "Electronics", 100, 999.99));
        products.add(new Product("S002", "Headphones", "AudioCorp", "Electronics", 50, 49.99));
        products.add(new Product("S003", "Book", "Bookstore Inc.", "Literature", 200, 19.99));
        products.add(new Product("S004", "Smartphone", "TechCo", "Electronics", 80, 799.99));
        products.add(new Product("S005", "Chair", "FurnitureMall", "Furniture", 30, 149.99));
        products.add(new Product("S006", "Coffee Maker", "KitchenTech", "Appliances", 40, 89.99));
        products.add(new Product("S007", "Running Shoes", "SportsGear", "Footwear", 60, 79.99));
        products.add(new Product("S008", "Backpack", "AdventureCo", "Travel", 25, 29.99));
        products.add(new Product("S009", "Television", "HomeElectronics", "Electronics", 120, 1499.99));
        products.add(new Product("S010", "Desk Lamp", "OfficeSupplies", "Office", 15, 34.99));
        products.add(new Product("S011", "Tablet", "TechGizmo", "Electronics", 60, 399.99));
        products.add(new Product("S012", "Gaming Chair", "GamerHaven", "Furniture", 20, 249.99));
        products.add(new Product("S013", "Novel", "ReadingCorner", "Literature", 150, 12.99));
        products.add(new Product("S014", "Digital Camera", "PhotoTech", "Electronics", 45, 699.99));
        products.add(new Product("S015", "Sofa", "LivingStyle", "Furniture", 40, 499.99));
        products.add(new Product("S016", "Blender", "KitchenMaster", "Appliances", 35, 49.99));
        products.add(new Product("S017", "Sneakers", "SportsZone", "Footwear", 80, 89.99));
        products.add(new Product("S018", "Suitcase", "TravelGear", "Travel", 10, 79.99));
        products.add(new Product("S019", "Projector", "HomeTheater", "Electronics", 30, 799.99));
        products.add(new Product("S020", "Desk Organizer", "OfficeEssentials", "Office", 25, 19.99));
    }

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
