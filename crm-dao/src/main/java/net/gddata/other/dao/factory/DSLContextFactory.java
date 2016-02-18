package net.gddata.other.dao.factory;

import lombok.extern.slf4j.Slf4j;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class DSLContextFactory implements FactoryBean<DSLContext> {

    @Resource
    private Configuration configuration;

    @Override
    public DSLContext getObject() throws Exception {
        log.info("Init DSL context");
        return DSL.using(configuration);
    }

    @Override
    public Class<?> getObjectType() {
        return DSLContext.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
