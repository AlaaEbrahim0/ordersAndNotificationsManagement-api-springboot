package OrdersandNotificationsManagement.Services;

import OrdersandNotificationsManagement.Dtos.CustomerDto;
import OrdersandNotificationsManagement.Dtos.SignInDto;
import OrdersandNotificationsManagement.Entities.Customer;
import OrdersandNotificationsManagement.Repositories.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final CustomerRepository customerRepository;

    @Autowired
    public AuthService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public void registerCustomer(CustomerDto dto) throws Exception {
        var customer = new Customer(dto.getEmail(), dto.getPassword(), dto.getBalance(), dto.getAddress());
        if (customerRepository.getCustomerByEmail(customer.getEmail()) != null){
            throw new Exception("email address already exist");
        }
        customerRepository.addCustomer(customer);
    }
    public Boolean isSignedIn(int customerId, HttpSession session){
         if (session.getAttribute(String.valueOf(customerId)) == null){
             return false;
         }
         return true;
    }
    public Customer signCustomerIn(SignInDto dto) throws Exception {
        var customer = customerRepository.getCustomerByEmail(dto.getEmail());
        if (customer == null || !customer.getPassword().equals(dto.getPassword())){
            throw new Exception("invalid email or password");
        }
        return customer;
    }


}
