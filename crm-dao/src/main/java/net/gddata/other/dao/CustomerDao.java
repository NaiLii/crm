package net.gddata.other.dao;

import net.gddata.other.core.Customer;
import net.gddata.other.crm.tables.records.CustomerRecord;
import net.gddata.other.dao.factory.JooqDao;
import org.springframework.stereotype.Component;

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

    public List<Customer> byUser(String uid) {
        return create()
                .selectFrom(CUSTOMER)
                .where(CUSTOMER.USER.eq(uid))
                .fetch()
                .into(Customer.class);
    }

    public List<Customer> search(String keyword) {
        return create()
                .selectFrom(CUSTOMER)
                .where(CUSTOMER.NAME.like("%" + keyword + "%"))
                .limit(100)
                .fetch()
                .into(Customer.class);
    }
}
