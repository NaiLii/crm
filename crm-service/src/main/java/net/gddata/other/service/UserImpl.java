package net.gddata.other.service;

import net.gddata.other.core.User;
import org.springframework.stereotype.Service;

/**
 * Created by knix on 16/2/19.
 */
@Service("userService")
public class UserImpl implements UserService {
    @Override
    public User findByUserName(String username) {
        return null;
    }
}
