package OrdersandNotificationsManagement.Utilities;

import OrdersandNotificationsManagement.Contracts.IShippingFeesCalculator;
import OrdersandNotificationsManagement.Entities.AbstractOrder;
import org.springframework.stereotype.Service;

@Service
public class FakeShippingFeesCalculator implements IShippingFeesCalculator {
    public double calculateShippingFee(AbstractOrder order) {
        return 10.0;
    }
}