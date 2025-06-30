package com.welfare.lottery.response;


import com.welfare.lottery.common.exception.WelfareBaseException;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Setter
@Getter
public class WelfareResult<T> implements Serializable {

    public static final int SUCCESSFUL_CODE = 0;
    public static final String SUCCESSFUL_REASON = "成功";

    @Serial
    private static final long serialVersionUID = 8159938444716131896L;
    private int code;
    private String desc;
    private T result;

    public WelfareResult() {
        this(SUCCESSFUL_CODE, SUCCESSFUL_REASON);
    }

    public WelfareResult(T t) {
        this();
        this.result = t;
    }

    public WelfareResult(WelfareBaseException e) {
        this(e.getCode(), e.getReason());
    }

    public WelfareResult(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public WelfareResult(int code, String desc, T t) {
        this.code = code;
        this.desc = desc;
        this.result = t;
    }

    private static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<>();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }

        return map;
    }

    public Map<String, Object> toJsonResponse() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", code);
        map.put("reason", desc);
        if (null != result) {
            try {
                if (result instanceof Map) {
                    map.putAll((Map) result);
                } else {
                    map.putAll(objectToMap(result));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}


