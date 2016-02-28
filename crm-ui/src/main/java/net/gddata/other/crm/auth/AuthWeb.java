package net.gddata.other.crm.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.gddata.other.core.LoginForm;
import net.gddata.other.core.User;
import net.gddata.other.core.auth.AuthClient;
import net.gddata.other.core.auth.AuthService;
import net.gddata.other.service.UserService;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by knix on 16/2/27.
 */


@Path("/auth")
@Api(value = "认证", description = "认证")
@Produces(MediaType.APPLICATION_JSON)
@Component
public class AuthWeb {
    @Resource
    AuthService authService;

    @Resource
    UserService userService;


    @POST
    @Path("login")
    @RequiresGuest
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取认证令牌", notes = "获取认证令牌")
    public Map login(@ApiParam LoginForm loginForm) {

        String username = loginForm.getUsername();
        String password = loginForm.getPassword();

        User user = userService.loginByPassword(username, password);

        AuthClient authClient = authService.login(user);
        Map map = new HashMap<>();
        if (null != user) {
            map.put("token", authClient.getToken());
            map.put("status", "200");
        } else {
            map.put("status", "400");
        }
        return map;
    }
}
