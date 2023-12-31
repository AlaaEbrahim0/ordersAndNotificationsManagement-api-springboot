package OrdersandNotificationsManagement.Controllers;

import OrdersandNotificationsManagement.Entities.Customer;
import OrdersandNotificationsManagement.Services.CustomerService;
import OrdersandNotificationsManagement.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;


    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @GetMapping("{customerId}")
    public ResponseEntity<?> getCustomerInfo(@PathVariable int customerId){
        var customer = customerService.getCustomerById(customerId);
        if (customer == null){
            return ResponseEntity.status(404).body("customer with id: " + customerId + " doesn't exist");
        }
        return ResponseEntity.status(200).body(customer);
    }

}