package OrdersandNotificationsManagement.Controllers;

import OrdersandNotificationsManagement.Dtos.CustomerDto;
import OrdersandNotificationsManagement.Repositories.CustomerRepository;
import OrdersandNotificationsManagement.Entities.Customer;
import OrdersandNotificationsManagement.Dtos.SignInDto;
import OrdersandNotificationsManagement.Services.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> Register(@RequestBody CustomerDto dto) throws BadRequestException {
        try{
            customerService.registerCustomer(dto);
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(dto.getEmail() + " has been created successfully");
        }
        catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> SignIn(@RequestBody SignInDto dto, HttpSession session){
        try{
            var customer = customerService.signCustomerIn(dto);
            var key = "customer_" + customer.getId();
            session.setAttribute(key, customer);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("successful login");
        }
        catch (Exception ex){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("invalid email or password");
        }
    }

}
