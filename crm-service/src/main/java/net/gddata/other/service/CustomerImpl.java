package net.gddata.other.service;

import net.gddata.other.core.Customer;
import net.gddata.other.dao.CustomerDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by knix on 16/2/17.
 */
@Service("customerService")
public class CustomerImpl implements CustomerService {

    @Resource
    CustomerDao customerDao;

    @Override
    public Customer detail(Integer id) {
        return customerDao.detail(id);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerDao.add(customer);
    }
}
