package com.pji.cloud.datax.admin.tool.pojo;

import com.pji.cloud.datax.admin.dto.Range;
import com.pji.cloud.datax.admin.dto.VersionColumn;
import com.pji.cloud.datax.admin.entity.JobDatasource;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author jingwk
 */
@Data
public class DataxHbasePojo {

  private List<Map<String,Object>> columns;

  /**
   * 数据源信息
   */
  private JobDatasource jdbcDatasource;


  private String readerHbaseConfig;

  private String readerTable;

  private String readerMode;

  private String readerMaxVersion;

  private Range readerRange;

  private String writerHbaseConfig;

  private String writerTable;

  private String writerMode;

  private VersionColumn writerVersionColumn;

  private String writerRowkeyColumn;
}
