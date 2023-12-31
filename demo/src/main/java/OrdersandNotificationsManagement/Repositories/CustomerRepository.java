package OrdersandNotificationsManagement.Repositories;

import OrdersandNotificationsManagement.Contracts.ICustomerRepository;
import OrdersandNotificationsManagement.Entities.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class    CustomerRepository implements ICustomerRepository {
    private final List<Customer> customers;

    public CustomerRepository() {
        customers = new ArrayList<>();
        customers.add(new Customer("mail@mail.com", "password", 3000, "address"));
        customers.add(new Customer("mail2@mail.com", "password", 2800, "address"));
        customers.add(new Customer("mail3@mail.com", "password", 5000, "address"));
        customers.add(new Customer("mail4@mail.com", "password", 1000, "address"));
        customers.add(new Customer("mail5@mail.com", "password", 3000, "address"));
        customers.add(new Customer("mail6@mail.com", "password", 3000, "address"));
        customers.add(new Customer("mail7@mail.com", "password", 3000, "address"));
        customers.add(new Customer("mail8@mail.com", "password", 3000, "address"));
        customers.add(new Customer("mail9@mail.com", "password", 3000, "address"));
        customers.add(new Customer("mail10@mail.com", "password", 3000, "address"));
    }
    @Override
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }
    @Override
    public Customer getCustomerByEmail(String email) {
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email)) {
                return customer;
            }
        }
        return null;
    }
    @Override
    public Customer getCustomerById(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

}
