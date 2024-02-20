package com.dash.datax.admin.tool.query;

import com.pji.cloud.datatx.core.enums.DbType;

/**
 * ClickHouse
 */

public class ClickHouseQueryTool extends BaseQueryTool implements QueryToolInterface {

    public ClickHouseQueryTool(DbType dbType, String parameter) {
        super(dbType, parameter);
    }
}
