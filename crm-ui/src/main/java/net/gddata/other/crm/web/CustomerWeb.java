package net.gddata.other.crm.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.gddata.other.core.Customer;
import net.gddata.other.service.CustomerService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by knix on 16/2/17.
 */

@Path("/customer")
@Api(value="", description = "")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class CustomerWeb extends WebPage {
    @Resource
    CustomerService customerService;

    @GET
    @Path("detail/{id}")
    @ApiOperation(value="客户详情", notes="一条客户的详情")
    public Customer detail(@PathParam("id") Integer id){
        return customerService.detail(id);
    }
}
