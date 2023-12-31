package OrdersandNotificationsManagement.Entities;


import java.util.UUID;

public class Product {
    public Product(String serialNumber, String name, String vendor, String category, int quantity, double price) {
        this.quantity = quantity;
        this.serialNumber = serialNumber    ;
        this.name = name;
        this.vendor = vendor;
        this.category = category;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "serialNumber='" + serialNumber + '\'' +
                ", name='" + name + '\'' +
                ", vendor='" + vendor + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }

    private  String serialNumber;
    private  String name;
    private  String vendor;
    private String category;
    private int quantity;
    private  double price;
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product() {
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

