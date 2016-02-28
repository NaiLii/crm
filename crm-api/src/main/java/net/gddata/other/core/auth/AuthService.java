package net.gddata.other.core.auth;

import net.gddata.other.core.User;

public interface AuthService {

    AuthClient login(User user);

    AuthClient resolve(String token);

}
