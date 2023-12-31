package OrdersandNotificationsManagement.Services;

import OrdersandNotificationsManagement.Entities.Notification;
import OrdersandNotificationsManagement.Entities.NotificationTemplate;
import OrdersandNotificationsManagement.Enums.NotificationTemplates;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationTemplateService {
    private final Map<NotificationTemplates, NotificationTemplate> notificationTemplates;
    public NotificationTemplateService() {
        this.notificationTemplates = new HashMap<>();
        var placementTemplate = new NotificationTemplate();
        placementTemplate.setSubject("Order Placement Confirmation");
        placementTemplate.setContent("Dear {customerEmail}, your order with ID {orderId} has been placed. Thank you for choosing our store!");
        placementTemplate.setAvailableLanguages(Arrays.asList("English"));
        placementTemplate.setPlaceholders(Arrays.asList("customerName", "orderId"));

        notificationTemplates.put(NotificationTemplates.ORDER_PLACEMENT, placementTemplate);

        NotificationTemplate shipmentTemplate = new NotificationTemplate();
        shipmentTemplate.setSubject("Order Shipment Notification");
        shipmentTemplate.setContent("Dear {customerEmail}, your order with ID {orderId} has been shipped. Thank you for shopping with us!");
        shipmentTemplate.setAvailableLanguages(Arrays.asList("English"));
        shipmentTemplate.setPlaceholders(Arrays.asList("customerEmail", "orderId", "productName"));

        notificationTemplates.put(NotificationTemplates.ORDER_SHIPPING, shipmentTemplate);
    }
    public Notification createNotification(NotificationTemplates type, Map<String, String> placeholders) {
        if (!notificationTemplates.containsKey(type)) {
            throw new IllegalArgumentException("Template not found: " + type);
        }

        NotificationTemplate template = notificationTemplates.get(type);
        String templateContent = template.getContent();

        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            String placeholder = "{" + entry.getKey() + "}";
            templateContent = templateContent.replace(placeholder, entry.getValue());
        }

        var notification = new Notification(template.getSubject(), templateContent, placeholders.get("customerEmail"));
        return notification;
    }
}
