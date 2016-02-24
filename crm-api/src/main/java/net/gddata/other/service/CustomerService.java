package net.gddata.other.service;

import net.gddata.other.core.Customer;

import java.util.List;

/**
 * Created by knix on 16/2/17.
 */
public interface CustomerService {
    Customer detail(Integer id);
    Customer addCustomer(Customer customer);
    List<Customer> my();
}
