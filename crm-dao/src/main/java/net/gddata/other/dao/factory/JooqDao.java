package net.gddata.other.dao.factory;

import lombok.extern.slf4j.Slf4j;
import org.jooq.Configuration;
import org.jooq.DSLContext;
import org.jooq.Table;
import org.jooq.UpdatableRecord;
import org.jooq.impl.DAOImpl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Slf4j
public abstract class JooqDao<R extends UpdatableRecord<R>, P, T> extends DAOImpl<R, P, T> {

    private DSLContext dslContext;

    @Resource
    public void init(Configuration configuration) {
        setConfiguration(configuration);
    }

    @Resource
    public void initDslContext(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    protected JooqDao(Table<R> table, Class<P> type) {
        super(table, type);
    }

    @PostConstruct
    public void init() {
        log.info("init {}", getClass().getName());
    }

    protected DSLContext create() {
        return dslContext;
    }

    public void save(P p) {
        insert(p);
    }
}
