package net.gddata.other.dao.auth;

import lombok.extern.slf4j.Slf4j;
import net.gddata.other.core.auth.AuthClient;
import net.gddata.other.core.auth.AuthService;
import net.gddata.other.core.auth.AuthToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.AllowAllCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

@Slf4j
public class LoginRealm extends AuthorizingRealm {

    public static final String NAME = "default";

    @Resource
    private AuthService authService;

    public LoginRealm() {
        setCredentialsMatcher(new AllowAllCredentialsMatcher());
        setAuthenticationTokenClass(AuthToken.class);
        setName(NAME);
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        AuthToken authToken = (AuthToken) token;
        String username = authToken.getUsername();
        String accessToken = authToken.getToken();
        AuthClient authClient = null;
        authClient = authService.login(authToken);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(authClient, null, null, getName());
        return info;
    }

}
