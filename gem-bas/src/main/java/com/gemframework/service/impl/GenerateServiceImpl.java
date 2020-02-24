package com.gemframework.service.impl;
import org.apache.commons.io.IOUtils;
import com.gemframework.common.utils.GemGenerate;
import com.gemframework.model.vo.response.PageInfo;
import com.gemframework.repository.*;
import com.gemframework.service.GenerateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

@Slf4j
@Service
@Transactional
public class GenerateServiceImpl implements GenerateService {

    @Resource
    private ModuleRepository moduleRepository;

    /**
     * @Title:  findPageByParams
     * @MethodName:  findPageByParams
     * @Param: [vo, pageable]
     * @Retrun: org.springframework.data.domain.Page
     * @Description: 【分页】根据条件动态查询
     * @Date: 2019-12-05 22:10:59
     */

    @Override
    public PageInfo findTablesPageByParams(String name,int page,int size) {
        page = (page*size);
        List<Map<String, String>> list = moduleRepository.findAllTablesByName(name,page,size);
        PageInfo pageInfo = new PageInfo();
        pageInfo.setRows(list);
        pageInfo.setTotal(moduleRepository.findAllTablesCount(name));
        return pageInfo;
    }

    @Override
    public Map<String, String> getTablePageByName(String tableName) {
        return moduleRepository.getTablesByName(tableName);
    }


    @Override
    public List<Map<String, String>> findColumnsByTableName(String tableName) {
        return moduleRepository.findAllColumnsByTableName(tableName);
    }


    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        for(String tableName : tableNames){
            //查询表信息
            Map<String, String> table = getTablePageByName(tableName);
            //查询列信息
            List<Map<String, String>> columns = findColumnsByTableName(tableName);
            //生成代码
            GemGenerate.generatorCode(table, columns, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
