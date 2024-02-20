package com.dash.datax.admin.tool.query;

import com.pji.cloud.datatx.core.enums.DbType;

/**
 * TODO
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName PostgresqlQueryTool
 * @Version 1.0
 * @since 2019/8/2 11:28
 */
public class PostgresqlQueryTool extends BaseQueryTool implements QueryToolInterface {
    public PostgresqlQueryTool(DbType dbType, String parameter) {
        super(dbType, parameter);
    }

}
