package OrdersandNotificationsManagement.Utilities;

public class NotificationMessageTemplate {
    public static String orderPlacedMessageTemplate(int orderId, String email){
        return
            "Dear {" + email + "} , your booking of the order: {" + orderId + "} is confirmed.\n" +
            " thanks for using our store :)";
    }

    public static String shippingStartedMessageTemplate(int orderId, String email){
        return
                "Dear {" + email + "} , shipping of the order: {" + orderId + "} has started.\n" +
                        " thanks for using our store :)";
    }
}
