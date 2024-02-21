package com.pji.cloud.datax.admin.tool.datax.reader;

import com.pji.cloud.datax.admin.dto.DataXJsonBuildDTO;
import com.pji.cloud.datax.admin.entity.JobDatasource;
import com.pji.cloud.datax.admin.tool.datax.DataXPluginInterface;

import java.util.Map;

/**
 * 用于构建reader的接口
 *
 * @author zhouhongfa@gz-yibo.com
 * @version 1.0
 * @since 2019/7/30
 */
public interface DataxReaderInterface extends DataXPluginInterface {
    /**
     * 构建reader对象接口，各数据源各自实现接口
     *
     * @param dataxJsonDto
     * @param readerDatasource
     * @return {@link Map < String, Object>}
     * @author Locki
     * @date 2020/12/24
     */
    Map<String, Object> buildReader(DataXJsonBuildDTO dataxJsonDto, JobDatasource readerDatasource);
}
