package com.gemframework.model.vo.database;

import com.gemframework.model.vo.BaseVo;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Title: RoleVo.java
 * @Package: com.gemframework.model.vo
 * @Date: 2019/11/30 22:40
 * @Version: v1.0
 * @Description: VO

 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */

public class Tables{
    //表的名称
    private String tableName;
    //表的备注
    private String comments;
    //表的主键
    private Column pk;
    //表的列名(不包含主键)
    private List<Column> columns;

    //类名(第一个字母大写)，如：sys_user => SysUser
    private String className;
    //类名(第一个字母小写)，如：sys_user => sysUser
    private String classname;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Column getPk() {
        return pk;
    }

    public void setPk(Column pk) {
        this.pk = pk;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }
}
