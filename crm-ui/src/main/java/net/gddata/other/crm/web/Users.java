package net.gddata.other.crm.web;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.gddata.other.core.User;
import net.gddata.other.service.UserService;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import static net.gddata.other.crm.Util.Token.getUserId;

@Path("/users")
@Api(value = "用户", description = "用户")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class Users {
    @Resource
    UserService userService;

    @GET
    @Path("me")
    @ApiOperation(value = "我", notes = "我")
    public User getMe(@Context HttpServletRequest request,
                      @Context HttpServletResponse response) {
        String userId = getUserId(request);
        if(userId.equals("")){
            response.setStatus(401);
            //throw new UnauthorizedException();
        }
        return userService.findByUserId(userId);
    }

}
