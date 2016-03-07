package net.gddata.other.crm.web;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.gddata.other.core.Customer;
import net.gddata.other.service.CustomerService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static net.gddata.other.crm.Util.Token.getUserId;

@Path("/birthday")
@Api(value = "生日", description = "生日")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class BirthdayWeb {
    @Resource
    CustomerService customerService;

    @GET
    @Path("will")
    @ApiOperation(value = "即将过生日的我的客户", notes = "即将过生日的我的客户")
    public List<Customer> myWillBirthdayCustomer(@Context HttpServletRequest request) {
        String userId = getUserId(request);
        return customerService.willBirthday(userId);
    }

}
