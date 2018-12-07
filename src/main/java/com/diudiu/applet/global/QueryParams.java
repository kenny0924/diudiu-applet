package com.diudiu.applet.global;

import com.diudiu.applet.utils.ObjectUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class QueryParams extends HashMap<String, Object> {
    private static final String PAGE_NUM = "pageNum";
    private static final String PAGE_SIZE = "pageSize";
    public static QueryParams create() {
        return new QueryParams();
    }

    public static QueryParams create(String key, Object val) {
        assert ObjectUtil.notEmpty(key);

        QueryParams p = new QueryParams();
        p.put(key, val);
        return p;
    }

    public QueryParams append(String key, Object val) {
        assert ObjectUtil.notEmpty(key);
        put(key, val);
        return this;
    }

    public QueryParams pageNum(Integer pageNum) {
        put(PAGE_NUM, pageNum);
        return this;
    }

    public QueryParams pageSize(Integer pageSize) {
        assert ObjectUtil.notEmpty(pageSize);
        put(PAGE_SIZE, pageSize);
        return this;
    }

    public Integer pageNum() {
        return (Integer) getOrDefault(PAGE_NUM, 0);
    }
    public Integer pageSize() {
        return (Integer) getOrDefault(PAGE_SIZE, 0);
    }

    public QueryParams delPageNum() {
        remove(PAGE_NUM);
        return this;
    }

    public QueryParams delPageSize() {
        remove(PAGE_SIZE);
        return this;
    }

    public boolean isPage() {
        return ObjectUtil.notEmpty(get("pageNum")) && ObjectUtil.notEmpty(get("pageSize"));
    }

}
