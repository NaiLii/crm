package net.gddata.other.dao.factory;

import org.jooq.ConnectionProvider;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;

public class SpringConnectionProvider implements ConnectionProvider {

    private final DataSource dataSource;

    public SpringConnectionProvider(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Connection acquire() throws DataAccessException {
        return DataSourceUtils.getConnection(dataSource);
    }

    @Override
    public void release(Connection connection) throws DataAccessException {
        DataSourceUtils.releaseConnection(connection, dataSource);
    }

}
