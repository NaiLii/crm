package net.gddata.other.service.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import net.gddata.other.core.User;
import net.gddata.other.core.auth.AuthClient;
import net.gddata.other.core.auth.AuthService;
import net.gddata.other.core.auth.AuthToken;
import net.gddata.other.service.UserService;
import net.gddata.other.util.ReadConf;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

@Service("authService")
public class AuthServiceImpl implements AuthService {

    @Resource
    UserService userService;

    private String secretKey;

    private String base64KeyStr;

    @PostConstruct
    public void init() {
        secretKey = ReadConf.getValue("secret.key");
        base64KeyStr = TextCodec.BASE64.encode(secretKey);
    }


    public AuthClient resolve(String token, Integer cid) {
        Claims claims = Jwts.parser().setSigningKey(base64KeyStr).parseClaimsJws(token).getBody();
        AuthClient authClient = new AuthClient();
        User user = userService.findByUserName(claims.getSubject());
        if (null != user) {
            authClient.setId(user.getId());
            authClient.setToken(token);
            authClient.setUsername(user.getUsername());
        }
        return authClient;
    }

    @Override
    public AuthClient login(AuthToken token) {
        //  验证用户名密码
        AuthClient authClient = new AuthClient();
        User user = userService.findByUserName(token.getUsername());

        if (null == user) {
            return authClient;
        }
        JwtBuilder builder = Jwts.builder();
        builder.setSubject(user.getUsername());
        builder.setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000));
        builder.setIssuedAt(new Date());
        String accessToken = builder.signWith(SignatureAlgorithm.HS256, base64KeyStr).compact();

        authClient.setId(user.getId());
        authClient.setToken(accessToken);
        authClient.setUsername(user.getUsername());
        return authClient;
    }
}
