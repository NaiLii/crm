package net.gddata.other.dao.factory;

import lombok.extern.slf4j.Slf4j;
import org.jooq.Configuration;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Slf4j
@Component
public class JooqConfigurationFactory implements FactoryBean<Configuration> {

    @Resource
    private DataSource dataSource;

    @Override
    public Configuration getObject() throws Exception {
        Configuration configuration = new DefaultConfiguration()
                .set(new SpringConnectionProvider(dataSource))
                .set(SQLDialect.MYSQL)
                .set(new DefaultExecuteListenerProvider(new ExceptionTranslator(dataSource)));
        return configuration;
    }

    @Override
    public Class<?> getObjectType() {
        return Configuration.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
