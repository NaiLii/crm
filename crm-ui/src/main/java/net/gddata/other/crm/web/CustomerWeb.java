package net.gddata.other.crm.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.gddata.other.core.Customer;
import net.gddata.other.service.CustomerService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

import static net.gddata.other.crm.Util.Token.getUserId;

/**
 * Created by knix on 16/2/17.
 */

@Path("/customer")
@Api(value = "客户", description = "客户")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class CustomerWeb extends WebPage {
    @Resource
    CustomerService customerService;

    @GET
    @Path("detail/{id}")
    @ApiOperation(value = "客户详情", notes = "一条客户的详情")
    public Customer detail(@PathParam("id") Integer id) {
        return customerService.detail(id);
    }

    @GET
    @Path("my")
    @ApiOperation(value = "我的客户", notes = "我的客户")
    public List<Customer> my(@Context HttpServletRequest request) {
        String userId = getUserId(request);
        if (userId.equals("")) {
            return null;
        }
        return customerService.my(userId);
    }

    @POST
    @Path("add")
    @ApiOperation(value = "新增客户", notes = "新增客户")
    public Customer add(@ApiParam("customer") Customer customer,
                        @Context HttpServletRequest request) {
        String userId = getUserId(request);
        if (!userId.equals("")) {
            customer.setUser(userId);
            return customerService.add(customer);
        }
        return null;
    }

    @PUT
    @Path("update")
    @ApiOperation(value = "更新客户信息", notes = "更新客户信息")
    public void update(@ApiParam("customer") Customer customer,
                       @Context HttpServletRequest request) {
        String userId = getUserId(request);
        if (!userId.equals("") && customer.getUser().equals(userId)) {
            customerService.update(customer);
        }
    }

    @GET
    @Path("search")
    @ApiOperation(value = "搜索我的用户", notes = "搜索我的用户")
    public List<Customer> search(@QueryParam("key") String key,
                                 @Context HttpServletRequest request) {
        String userId = getUserId(request);
        if (!userId.equals("")) {
            return customerService.search(key, userId);
        }
        return null;
    }
}
