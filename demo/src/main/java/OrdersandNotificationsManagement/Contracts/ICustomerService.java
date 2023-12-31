package OrdersandNotificationsManagement.Contracts;

import OrdersandNotificationsManagement.Entities.Customer;

public interface ICustomerService {
    Customer getCustomerById(int customerId);
    void updateCustomerBalance(int customerId, double amount) throws Exception;
}
