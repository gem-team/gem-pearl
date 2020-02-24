package com.gemframework.common.listener;

import java.sql.Timestamp;

/**
 * @Title: GemEntityOpt.java
 * @Package: com.gemframework.listener
 * @Date: 2019/11/28 17:42
 * @Version: v1.0
 * @Description: 自定义监控实体操作

 * @Author: zhangysh
 * @Copyright: Copyright (c) 2019 GemStudio
 * @Company: www.gemframework.com
 */
public interface GemEntityOpt {

    Timestamp getDateCreated();

    void setDateCreated(Timestamp dateCreated);

    Timestamp getLastUpdated();

    void setLastUpdated(Timestamp lastUpdated);

    Long getDateCreatedOn();

    void setDateCreatedOn(Long dateCreatedOn);

    Long getLastUpdatedOn();

    void setLastUpdatedOn(Long lastUpdatedOn);

}
