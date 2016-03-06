package net.gddata.other.dao;

import net.gddata.other.core.Customer;
import net.gddata.other.crm.tables.records.CustomerRecord;
import net.gddata.other.dao.factory.JooqDao;
import net.gddata.other.tools.DateTime.Calculate;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static net.gddata.other.crm.tables.Customer.CUSTOMER;

/**
 * Created by knix on 16/2/17.
 */
@Component
public class CustomerDao extends JooqDao<CustomerRecord, Customer, Integer> {
    protected CustomerDao() {
        super(CUSTOMER, Customer.class);
    }

    @Override
    protected Integer getId(Customer customer) {
        return customer.getId();
    }

    public Customer detail(Integer id) {
        return create()
                .selectFrom(CUSTOMER)
                .where(CUSTOMER.ID.eq(id))
                .fetchOne()
                .into(Customer.class);
    }

    public Customer add(Customer customer) {
        CustomerRecord record = create().newRecord(CUSTOMER);
        record.from(customer);
        record.insert();
        customer.setId(record.getId());
        return customer;
    }

    public List<Customer> byUser(String userId) {
        List<Customer> list = create()
                .selectFrom(CUSTOMER)
                .where(CUSTOMER.USER.eq(userId))
                .fetch()
                .into(Customer.class);
        Date date = new Date();

        int today = Calculate.getTodayOfYear(date);
        int nextMonthToday = Calculate.getNextMonthDayOfYear(date);

        if (nextMonthToday < today) {
            nextMonthToday += 365;
        }
        for (Customer c : list) {
            int birthDay = c.getHappyDay();
            if (today <= birthDay && nextMonthToday >= birthDay) {
                c.setWillBirthday(true);
            }
        }
        return list;
    }

    public List<Customer> search(String keyword, String userId) {
        return create()
                .selectFrom(CUSTOMER)
                .where(CUSTOMER.NAME.like("%" + keyword + "%")
                        .and(CUSTOMER.USER.eq(userId)))
                .limit(100)
                .fetch()
                .into(Customer.class);
    }

    public List<Customer> willBirthday(String userId) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int today = calendar.get(Calendar.DAY_OF_YEAR);
        calendar.add(Calendar.MONTH, 1);
        int i = calendar.get(Calendar.DAY_OF_YEAR);
        return create()
                .selectFrom(CUSTOMER)
                .where(CUSTOMER.USER.eq(userId)
                        .and(CUSTOMER.HAPPY_DAY.lt(i))
                        .and(CUSTOMER.HAPPY_DAY.lt(today)))
                .orderBy(CUSTOMER.HAPPY_DAY.asc())
                .fetch()
                .into(Customer.class);
    }
}
