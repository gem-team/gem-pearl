package com.gemframework.common.utils;

import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.dozer.DozerBeanMapper;

public class BeanMapper {
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    public BeanMapper() {
    }

    public static <T> T map(Object source, Class<T> destinationClass) {
        return dozer.map(source, destinationClass);
    }

    public static <T> List<T> mapList(Collection<?> sourceList, Class<T> destinationClass) {
        List<T> destinationList = Lists.newArrayList();
        Iterator i$ = sourceList.iterator();

        while(i$.hasNext()) {
            Object sourceObject = i$.next();
            T destinationObject = dozer.map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }

        return destinationList;
    }

    public static void copy(Object source, Object destinationObject) {
        dozer.map(source, destinationObject);
    }
}
