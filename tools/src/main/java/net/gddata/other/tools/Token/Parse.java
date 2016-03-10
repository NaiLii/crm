package net.gddata.other.tools.Token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import net.gddata.other.tools.Str.Format;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by knix on 16/3/10.
 */
public class Parse {

    public static Claims ToClaim(String token, String secretKey) {
        String base64KeyStr = Format.ToBase64(secretKey);
        try {
            final Claims claims = Jwts
                    .parser()
                    .setSigningKey(base64KeyStr)
                    .parseClaimsJws(token)
                    .getBody();
            return claims;
        } catch (Exception e) {
            return null;
        }
    }

    public static String ToToken(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return "";
        }

        if (authHeader.length() <= 7) {
            return "";
        }
        return authHeader.substring(7); // The part after "Bearer "
    }
}
