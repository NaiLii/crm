package net.gddata.other.core.auth;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

@Data
public class AuthToken implements AuthenticationToken {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String username;

    private String password;

    private String token;

    private Long expirationTimeMillis;

    private String role;

    @Override
    public Object getPrincipal() {
        return username;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

}
