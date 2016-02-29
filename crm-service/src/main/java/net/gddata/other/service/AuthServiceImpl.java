package net.gddata.other.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.gddata.other.core.User;
import net.gddata.other.core.auth.AuthClient;
import net.gddata.other.core.auth.AuthService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by knix on 16/2/28.
 */
@Service("authService")
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthClient login(User user, String base64KeyStr) {
        AuthClient authClient = new AuthClient();

        JwtBuilder builder = Jwts.builder();
        builder.setSubject(user.getUserId());
        builder.setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000));
        builder.setIssuedAt(new Date());
        String accessToken = builder.signWith(SignatureAlgorithm.HS256, base64KeyStr).compact(
        authClient.setToken(accessToken);
        authClient.setUserId(user.getUserId());
        return authClient;
    }

    @Override
    public AuthClient resolve(String token, String base64KeyStr) {
        Claims claims = Jwts
                .parser()
                .setSigningKey(base64KeyStr)
                .parseClaimsJws(token)
                .getBody();
        AuthClient authClient = new AuthClient();
        authClient.setUserId(claims.getSubject());
        return authClient;
    }
}
