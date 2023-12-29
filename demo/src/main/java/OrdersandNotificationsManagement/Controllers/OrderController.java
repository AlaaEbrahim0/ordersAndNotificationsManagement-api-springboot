package OrdersandNotificationsManagement.Controllers;

import OrdersandNotificationsManagement.Dtos.OrderToAddDto;
import OrdersandNotificationsManagement.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/orders")
public class OrderController {
    private  final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping
    public ResponseEntity<?> PlaceOrder(@RequestBody OrderToAddDto dto){
        if (dto == null){
            return ResponseEntity.status(400).body("input data is null");
        }
        try{
            var order = orderService.placeOrder(dto);
            return ResponseEntity.status(200).body(order.displayOrderInfo());
        }
        catch (Exception ex){
                return ResponseEntity.status(400).body(ex.getMessage());
        }

    }
}
