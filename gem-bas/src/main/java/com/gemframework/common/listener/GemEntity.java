package com.gemframework.common.listener;

import javax.persistence.EntityListeners;
import java.sql.Timestamp;


/**
 * @Title: GemEntity.java
 * @Package: com.gemframework.listener
 * @Date: 2019/11/28 17:42
 * @Version: v1.0
 * @Description: 自定义实体操作监控实现

 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
@EntityListeners(GemEntityListener.class)
public class GemEntity implements GemEntityOpt {

    private Timestamp createTime;
    private Timestamp lastUpdateTime;
    private Long createOn;
    private Long lastUpdateOn;

    @Override
    public Timestamp getDateCreated() {
        return createTime;
    }

    @Override
    public void setDateCreated(Timestamp dateCreated) {
        createTime = dateCreated;
    }

    @Override
    public Timestamp getLastUpdated() {
        return lastUpdateTime;
    }

    @Override
    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdateTime = lastUpdated;
    }

    @Override
    public Long getDateCreatedOn() {
        return createOn;
    }

    @Override
    public void setDateCreatedOn(Long dateCreatedOn) {
        createOn = dateCreatedOn;
    }

    @Override
    public Long getLastUpdatedOn() {
        return lastUpdateOn;
    }

    @Override
    public void setLastUpdatedOn(Long lastUpdatedOn) {
        this.lastUpdateOn = lastUpdatedOn;
    }
}
