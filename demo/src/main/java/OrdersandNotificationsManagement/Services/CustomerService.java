package OrdersandNotificationsManagement.Services;

import OrdersandNotificationsManagement.Dtos.CustomerDto;
import OrdersandNotificationsManagement.Dtos.SignInDto;
import OrdersandNotificationsManagement.Entities.Customer;
import OrdersandNotificationsManagement.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.StyledEditorKit;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public void registerCustomer(CustomerDto dto) throws Exception {
        var customer = new Customer(dto.getEmail(), dto.getPassword(), dto.getBalance(), dto.getAddress());
        if (customerRepository.getCustomerByEmail(customer.getEmail()) != null){
            throw new Exception("email address already exist");
        }
        customerRepository.addCustomer(customer);
    }

    public Customer signCustomerIn(SignInDto dto) throws Exception {
        var customer = customerRepository.getCustomerByEmail(dto.getEmail());
        if (customer == null || customer.getPassword() != dto.getPassword()){
            throw new Exception("invalid email or password");
        }
        return customer;
    }
}
