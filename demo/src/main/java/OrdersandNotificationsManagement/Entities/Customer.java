package OrdersandNotificationsManagement.Entities;

import org.springframework.boot.autoconfigure.domain.EntityScan;

public class Customer {
    private int id;
    private static int idCounter = 1;
    private String email;
    private String password;

    private String address;
    private double balance;
    public Customer() {
    }
    public Customer(String email, String password, double balance, String address) {
        this.id = idCounter;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.address = address;
        idCounter++;
    }
    public Customer(Customer customer) {
        this.email = customer.getEmail();
        this.password = customer.getPassword();
        this.balance = customer.getBalance();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
