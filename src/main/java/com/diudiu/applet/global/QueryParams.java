package com.diudiu.applet.global;

import com.diudiu.applet.utils.ObjectUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class QueryParams extends HashMap<String, Object> {
    public static QueryParams create() {
        return new QueryParams();
    }

    public static QueryParams create(String key, Object val) {
        assert ObjectUtil.notEmpty(key);

        QueryParams p = new QueryParams();
        p.put(key, val);
        return p;
    }

    public QueryParams append(String key, String val) {
        assert ObjectUtil.notEmpty(key);
        put(key, val);
        return this;
    }
}
