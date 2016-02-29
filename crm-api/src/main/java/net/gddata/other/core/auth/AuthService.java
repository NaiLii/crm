package net.gddata.other.core.auth;

import net.gddata.other.core.User;

public interface AuthService {

    AuthClient login(User user, String base64KeyStr);

    AuthClient resolve(String token, String base64KeyStr);

}
