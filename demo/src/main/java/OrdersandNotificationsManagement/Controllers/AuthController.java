package OrdersandNotificationsManagement.Controllers;

import OrdersandNotificationsManagement.Dtos.CustomerDto;
import OrdersandNotificationsManagement.Dtos.SignInDto;
import OrdersandNotificationsManagement.Services.AuthService;
import jakarta.servlet.http.HttpSession;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class AuthController {
    private final AuthService authService;
    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> Register(@RequestBody CustomerDto dto) throws BadRequestException {
        try{
            authService.registerCustomer(dto);
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
            var customer = authService.signCustomerIn(dto);
            var key = customer.getId();
            session.setAttribute(String.valueOf(key), customer);
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
