package net.gddata.other.core.auth;

public interface AuthService {

    AuthClient login(AuthToken authToken);

    AuthClient resolve(String token, Integer cid);

}
