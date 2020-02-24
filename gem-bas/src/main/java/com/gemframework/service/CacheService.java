package com.gemframework.service;

import com.gemframework.model.po.User;

public interface CacheService {

    String put(String key, String value);
    String get(String key);
    void remove(String key);


    User getById(Long id);
}
