package net.gddata.other.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import net.gddata.other.core.User;
import net.gddata.other.core.auth.AuthClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Created by knix on 16/2/28.
 */
@Service("authService")
public class AuthServiceImpl implements net.gddata.other.core.auth.AuthService {
    private String secretKey;

    private String base64KeyStr;

//    private Configuration config;

    @PostConstruct
    public void init() {
//        config = InitUtils.getConfig("auth");
//        secretKey = config.getString("secret.key");
        secretKey = "myseckeyofcrm";
        base64KeyStr = TextCodec.BASE64.encode(secretKey);
    }

    @Override
    public AuthClient login(User user) {
        AuthClient authClient = new AuthClient();

        JwtBuilder builder = Jwts.builder();
        builder.setSubject(user.getUsername());
        builder.setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000));
        builder.setIssuedAt(new Date());
        String accessToken = builder.signWith(SignatureAlgorithm.HS256, base64KeyStr).compact();

        authClient.setToken(accessToken);
        authClient.setUsername(user.getUsername());
        return authClient;
    }

    @Override
    public AuthClient resolve(String token) {
        Claims claims = Jwts.parser().setSigningKey(base64KeyStr).parseClaimsJws(token).getBody();
        AuthClient authClient = new AuthClient();
        authClient.setUsername(claims.getSubject());
        return authClient;
    }
}
