package OrdersandNotificationsManagement.Services;

import OrdersandNotificationsManagement.Contracts.ICustomerService;
import OrdersandNotificationsManagement.Entities.Customer;
import OrdersandNotificationsManagement.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    @Override
    public Customer getCustomerById(int customerId) {
        return customerRepository.getCustomerById(customerId);
    }
    @Override
    public void updateCustomerBalance(int customerId, double amount) throws Exception {
        var customer = customerRepository.getCustomerById(customerId);
        if (customer == null) {
            throw new Exception("Customer not found");
        }
        double newBalance = customer.getBalance() - amount;
        if (newBalance >= 0) {
        customer.setBalance(newBalance);
        } else {
            throw new Exception("Insufficient funds");
        }
    }
}
