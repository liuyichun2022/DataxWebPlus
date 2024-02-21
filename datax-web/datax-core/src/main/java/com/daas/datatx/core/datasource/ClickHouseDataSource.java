package com.daas.datatx.core.datasource;

import com.daas.datatx.core.enums.DbType;
import com.daas.datatx.core.util.Constants;

/**
 * data source of ClickHouse
 */
public class ClickHouseDataSource extends BaseDataSource {

    /**
     * @return driver class
     */
    @Override
    public String driverClassSelector() {
        return Constants.COM_CLICKHOUSE_JDBC_DRIVER;
    }

    /**
     * @return db type
     */
    @Override
    public DbType dbTypeSelector() {
        return DbType.CLICKHOUSE;
    }
}
