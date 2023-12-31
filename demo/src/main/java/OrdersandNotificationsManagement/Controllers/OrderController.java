package OrdersandNotificationsManagement.Controllers;

import OrdersandNotificationsManagement.Dtos.OrderToAddDto;
import OrdersandNotificationsManagement.Entities.AbstractOrder;
import OrdersandNotificationsManagement.Services.AuthService;
import OrdersandNotificationsManagement.Services.OrderService;
import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    private  final OrderService orderService;
    private  final AuthService authService;

    @Autowired
    public OrderController(OrderService orderService, AuthService authService) {
        this.orderService = orderService;
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> PlaceOrder(@RequestBody OrderToAddDto dto, HttpSession httpSession){
        if (dto == null){
            return ResponseEntity.status(400).body("input data is null");
        }
        if (!authService.isSignedIn(dto.getCustomerId(), httpSession)){
            return ResponseEntity.status(401).body("unauthenticated");
        }
        try{
            var order = orderService.placeOrder(dto);
            return ResponseEntity.status(200).body(orderService.displayOrderDetails(order));
        }
        catch (Exception ex){
                return ResponseEntity.status(400).body(ex.getMessage());
        }
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<?> getOrderDetails(@PathVariable int orderId){
        var order = orderService.getOrderById(orderId);
        if (order == null){
            return ResponseEntity.status(404).body("order doesn't exist");
        }
        return  ResponseEntity.status(200).body(orderService.displayOrderDetails(order));
    }

    @PostMapping("/{orderId}/ship")
    public ResponseEntity<?> shipOrder(@PathVariable int orderId, HttpSession httpSession) {
        try {
            var customerId = orderService.getOrderById(orderId).getCustomerId();
            if (!authService.isSignedIn(customerId, httpSession)){
                return ResponseEntity.status(401).body("unauthenticated");
            }
            orderService.shipOrder(orderId);
            return ResponseEntity.status(200).body("Order shipped successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }


}
