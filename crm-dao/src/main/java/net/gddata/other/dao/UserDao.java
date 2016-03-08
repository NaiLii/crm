package net.gddata.other.dao;

import net.gddata.other.core.User;
import net.gddata.other.crm.tables.records.UserRecord;
import net.gddata.other.dao.factory.JooqDao;
import org.springframework.stereotype.Component;

import static net.gddata.other.crm.tables.User.USER;

/**
 * Created by knix on 16/2/28.
 */

@Component
public class UserDao extends JooqDao<UserRecord, User, Integer> {
    public UserDao(){
        super(USER, User.class);
    }
    @Override
    protected Integer getId(User user) {
        return 0;
    }

    public User loginByPassword(String username, String password){
        UserRecord userRecord = create()
                .selectFrom(USER)
                .where(USER.USERNAME.eq(username).and(USER.PASSWORD.eq(password)))
                .fetchOne();
        if(null!=userRecord){
            return userRecord.into(User.class);
        }
        return null;
    }

    public User findByUserName(String username){
        return create()
                .selectFrom(USER)
                .where(USER.USERNAME.eq(username))
                .fetchOne()
                .into(User.class);
    }

    public User findByUserId(String userId){
        return create()
                .selectFrom(USER)
                .where(USER.USERNAME.eq(userId))
                .fetchOne()
                .into(User.class);
    }
}
