/**
 * Copyright (c) 2009-2013, Data Geekery GmbH (http://www.datageekery.com)
 * All rights reserved.
 * <p>
 * This work is dual-licensed
 * - under the Apache Software License 2.0 (the "ASL")
 * - under the jOOQ License and Maintenance Agreement (the "jOOQ License")
 * =============================================================================
 * You may choose which license applies to you:
 * <p>
 * - If you're using this work with Open Source databases, you may choose
 * either ASL or jOOQ License.
 * - If you're using this work with at least one commercial database, you must
 * choose jOOQ License
 * <p>
 * For more information, please visit http://www.jooq.org/licenses
 * <p>
 * Apache Software License 2.0:
 * -----------------------------------------------------------------------------
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p>
 * jOOQ License and Maintenance Agreement:
 * -----------------------------------------------------------------------------
 * Data Geekery grants the Customer the non-exclusive, timely limited and
 * non-transferable license to install and use the Software under the terms of
 * the jOOQ License and Maintenance Agreement.
 * <p>
 * This library is distributed with a LIMITED WARRANTY. See the jOOQ License
 * and Maintenance Agreement for more details: http://www.jooq.org/licensing
 */
package net.gddata.other.dao.factory;

import lombok.extern.slf4j.Slf4j;
import org.jooq.ExecuteContext;
import org.jooq.impl.DefaultExecuteListener;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.jdbc.support.SQLStateSQLExceptionTranslator;

import javax.sql.DataSource;
import java.sql.SQLException;

@Slf4j
public class ExceptionTranslator extends DefaultExecuteListener {

    private static final long serialVersionUID = 6914082794499325841L;

    private final DataSource ds;

    public ExceptionTranslator(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public void exception(ExecuteContext ctx) {
        SQLException sqlEx = ctx.sqlException();
        log.error("SQL error.", sqlEx);
        ctx.exception(translator().translate("jOOQ", ctx.sql(), sqlEx));
    }

    private SQLExceptionTranslator translator() {
        return ds == null
                ? new SQLStateSQLExceptionTranslator()
                : new SQLErrorCodeSQLExceptionTranslator(ds);
    }

}
