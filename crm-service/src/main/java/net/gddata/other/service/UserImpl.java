package net.gddata.other.service;

import net.gddata.other.core.User;
import net.gddata.other.dao.UserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by knix on 16/2/19.
 */
@Service("userService")
public class UserImpl implements UserService {
    @Resource
    UserDao userDao;

    @Override
    public User findByUserName(String username) {
        return null;
    }

    @Override
    public User loginByPassword(String username, String password) {
        return userDao.loginByPassword(username, password);
    }
}
