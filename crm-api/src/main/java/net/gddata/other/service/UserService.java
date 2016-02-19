package net.gddata.other.service;

import net.gddata.other.core.User;

/**
 * Created by knix on 16/2/19.
 */
public interface UserService {
    User findByUserName(String username);
}
