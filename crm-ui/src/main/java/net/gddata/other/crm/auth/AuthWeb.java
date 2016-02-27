package net.gddata.other.crm.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.gddata.other.core.LoginForm;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;
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

    @POST
    @Path("login")
    @RequiresGuest
    @Consumes(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "获取认证令牌", notes = "获取认证令牌")
    public Map login(@ApiParam LoginForm loginForm) {

        String compact = Jwts.builder()
                .setSubject(loginForm.getUsername())
                .claim("roles", "admin")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey")
                .compact();
        System.out.println(compact);
        Map map = new HashMap<>();
        map.put("status", 200);
        map.put("token", compact);
        return map;
    }
}
