package net.gddata.other.service;

import net.gddata.other.core.Customer;
import net.gddata.other.dao.CustomerDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static net.gddata.other.service.util.CustomerName.getPingYin;

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
    public Customer add(Customer customer) {

        String letter = getPingYin(customer.getName());
        customer.setLetter(letter);
        Date date = new Date();
        customer.setEnroll(date);
        return customerDao.add(customer);
    }

    @Override
    public void update(Customer customer) {
        String letter = customer.getLetter();
        if (null == letter || letter.equals("")) {
            String py = getPingYin(customer.getName());
            customer.setLetter(py);
        }
        customerDao.update(customer);
    }

    @Override
    public void delete(Integer cid) {
        customerDao.deleteById(cid);
    }

    @Override
    public List<Customer> my() {
        String user = "lls";
        return customerDao.byUser(user);
    }

    @Override
    public List<Customer> search(String keyword) {
        return customerDao.search(keyword);
    }
}
