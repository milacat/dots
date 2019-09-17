package com.dots.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();
    /**
     * 设置数据源名称
     * @param datasource 数据源名称
     */
    public static void setDataSource(String datasource) {
        contextHolder.set(datasource);
    }

    /**
     * 获取数据源名称
     * @return 数据源名称
     */
    public static String getDataSource() {
        return contextHolder.get();
    }

    /**
     * 清除标志
     */
    public static void clearDataSource() {
        contextHolder.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }
}
