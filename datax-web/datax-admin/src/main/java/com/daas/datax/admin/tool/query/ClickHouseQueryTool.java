package com.daas.datax.admin.tool.query;


import com.daas.datatx.core.enums.DbType;

/**
 * ClickHouse
 */

public class ClickHouseQueryTool extends BaseQueryTool implements QueryToolInterface {

    public ClickHouseQueryTool(DbType dbType, String parameter) {
        super(dbType, parameter);
    }
}
