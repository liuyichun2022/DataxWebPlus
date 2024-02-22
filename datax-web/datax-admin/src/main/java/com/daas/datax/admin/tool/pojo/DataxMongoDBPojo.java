package com.daas.datax.admin.tool.pojo;

import com.daas.datax.admin.entity.JobDatasource;
import com.daas.datax.admin.dto.UpsertInfo;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 用于传参，构建json
 *
 * @author jingwk
 * @ClassName DataxMongoDBPojo
 * @Version 2.0
 * @since 2020/03/14 11:15
 */
@Data
public class DataxMongoDBPojo {

    /**
     * hive列名
     */
    private List<Map<String, Object>> columns;

    /**
     * 数据源信息
     */
    private JobDatasource jdbcDatasource;

    private String address;

    private String dbName;

    private String readerTable;

    private String writerTable;

    private UpsertInfo upsertInfo;

}