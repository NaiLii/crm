package net.gddata.other.service;

import net.gddata.other.core.Customer;

import java.util.List;

/**
 * Created by knix on 16/2/17.
 */
public interface CustomerService {
    Customer detail(Integer id);
    Customer add(Customer customer);
    void update(Customer customer);
    void delete(Integer cid);

    List<Customer> my();
    List<Customer> search(String keyword);
}
