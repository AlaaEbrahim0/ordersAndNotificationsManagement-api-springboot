package OrdersandNotificationsManagement.Controllers;

import OrdersandNotificationsManagement.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    @GetMapping
    public ResponseEntity<?> listAllNotifications(){
        var queue = notificationService.listAllNotificationsInQueue();
        return ResponseEntity.status(200).body(queue);
    }


}
