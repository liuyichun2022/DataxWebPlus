package com.pji.cloud.datax.admin.tool.query;

import com.pji.cloud.datatx.core.enums.DbType;

/**
 * sql server
 *
 * @author zhouhongfa@gz-yibo.com
 * @version 1.0
 * @since 2019/8/2
 */
public class SqlServerQueryTool extends BaseQueryTool implements QueryToolInterface {
    public SqlServerQueryTool(DbType dbType, String parameter) {
        super(dbType,parameter);
    }
}
