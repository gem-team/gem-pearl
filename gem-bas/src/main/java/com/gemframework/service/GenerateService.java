package com.gemframework.service;

import com.gemframework.model.vo.DeptVo;
import com.gemframework.model.vo.database.Tables;
import com.gemframework.model.vo.response.PageInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface GenerateService {

    PageInfo findTablesPageByParams(String name,int page,int size);

    Map<String, String> getTablePageByName(String name);

    List<Map<String, String>>  findColumnsByTableName(String tableName);

    byte[] generatorCode(String[] tableNames);

}
