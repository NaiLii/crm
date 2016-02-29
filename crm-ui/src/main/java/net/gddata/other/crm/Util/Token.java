package net.gddata.other.crm.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import javax.servlet.http.HttpServletRequest;

import static net.gddata.other.crm.Util.SecretKey.getBase64Key;

/**
 * Created by knix on 16/2/29.
 */
public class Token {

    public static String getUserId(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return "";
        }

        if(authHeader.length()<=7){
            return "";
        }
        final String token = authHeader.substring(7); // The part after "Bearer "

        String base64KeyStr = getBase64Key();
        try {
            final Claims claims = Jwts
                    .parser()
                    .setSigningKey(base64KeyStr)
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (final SignatureException e) {
            return "";
        }
    }
}
