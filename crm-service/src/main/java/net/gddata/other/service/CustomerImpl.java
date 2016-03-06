package net.gddata.other.service;

import net.gddata.other.core.Customer;
import net.gddata.other.dao.CustomerDao;
import net.gddata.other.tools.DateTime.Calculate;
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
        Date birthday = customer.getBirthday();
        customer.setHappyDay(Calculate.getTodayOfYear(birthday));
        customer.setEnroll(new Date());
        return customerDao.add(customer);
    }

    @Override
    public void update(Customer customer) {
        String letter = customer.getLetter();
        if (null == letter || letter.equals("")) {
            String py = getPingYin(customer.getName());
            customer.setLetter(py);
        }
        Date birthday = customer.getBirthday();
        customer.setHappyDay(Calculate.getTodayOfYear(birthday));
        customerDao.update(customer);
    }

    @Override
    public void delete(Integer cid) {
        customerDao.deleteById(cid);
    }

    @Override
    public List<Customer> my(String userId) {
        return customerDao.byUser(userId);
    }

    @Override
    public List<Customer> search(String keyword, String userId) {
        return customerDao.search(keyword, userId);
    }

    @Override
    public List<Customer> willBirthday(String userId) {
        return customerDao.willBirthday(userId);
    }
}
