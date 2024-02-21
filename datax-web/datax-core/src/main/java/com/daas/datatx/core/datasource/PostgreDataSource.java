package com.daas.datatx.core.datasource;

import com.daas.datatx.core.enums.DbType;
import com.daas.datatx.core.util.Constants;

/**
 * data source of postgreSQL
 */
public class PostgreDataSource extends BaseDataSource {

  /**
   * gets the JDBC url for the data source connection
   * @return jdbc url
   */
  @Override
  public String driverClassSelector() {
    return Constants.ORG_POSTGRESQL_DRIVER;
  }

  /**
   * @return db type
   */
  @Override
  public DbType dbTypeSelector() {
    return DbType.POSTGRESQL;
  }
}
