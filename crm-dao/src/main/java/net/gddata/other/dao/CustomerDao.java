package net.gddata.other.dao;

import net.gddata.other.crm.tables.records.CustomerRecord;
import net.gddata.other.dao.factory.JooqDao;
import org.springframework.stereotype.Component;
import net.gddata.other.core.Customer;

import static net.gddata.other.crm.tables.Customer.CUSTOMER;

/**
 * Created by knix on 16/2/17.
 */
@Component
public class CustomerDao extends JooqDao<CustomerRecord, Customer, Integer> {
    protected CustomerDao(){
        super(CUSTOMER,Customer.class);
    }

    @Override
    protected Integer getId(Customer customer) {
        return customer.getId();
    }
}