package OrdersandNotificationsManagement.Contracts;

import OrdersandNotificationsManagement.Entities.Customer;

public interface ICustomerRepository {
    void addCustomer(Customer customer) throws Exception;
    Customer getCustomerByEmail(String email);
    Customer getCustomerById(int id);
}

