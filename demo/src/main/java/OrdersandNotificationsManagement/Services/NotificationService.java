package OrdersandNotificationsManagement.Services;

import OrdersandNotificationsManagement.Contracts.IOrderObserver;
import OrdersandNotificationsManagement.Entities.AbstractOrder;
import OrdersandNotificationsManagement.Entities.Customer;
import OrdersandNotificationsManagement.Entities.Notification;
import OrdersandNotificationsManagement.Enums.NotificationTemplates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NotificationService {
    private final Queue<Notification> notificationQueue;
    private final CustomerService customerService;
    private  final NotificationTemplateService notificationTemplateService;

    @Autowired
    public NotificationService(CustomerService customerService, NotificationTemplateService notificationTemplateService) {
        this.customerService = customerService;
        this.notificationTemplateService = notificationTemplateService;
        this.notificationQueue = new LinkedList<>();
    }
    private void addToNotificationQueue(Notification notificationContent) {
        notificationQueue.add(notificationContent);
    }
    public void notify(AbstractOrder order, NotificationTemplates template) {
        Customer customer = customerService.getCustomerById(order.getCustomerId());

        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("customerEmail", customer.getEmail());
        placeholders.put("orderId", String.valueOf(order.getId()));

        Notification notification = notificationTemplateService.createNotification(template, placeholders);
        addToNotificationQueue(notification);
    }
    public String listAllNotificationsInQueue() {
        StringJoiner result = new StringJoiner("\n");
        result.add("List of Notifications in Queue:");
        result.add("-----------------------------------");

        for (Notification notification : notificationQueue) {
            result.add("Subject: " + notification.getSubject());
            result.add("Content: " + notification.getContent());
            result.add("Recipient: " + notification.getReceipent());
            result.add("-----------------------------------");
        }

        if (notificationQueue.isEmpty()) {
            result.add("No notifications in the queue.");
        }
        return result.toString();
    }

}