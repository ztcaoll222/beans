package com.ztcaoll222.beans;

import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

/**
 * @author ztcaoll222
 * @date 2019/11/16 16:48
 */
public class Urls {
    private Urls() {
    }

    /**
     * bean 转 query
     * <p>
     * 要注意的是如果 bean 有基本类型的字段, 那么转换出来的值会是默认值, 尽管其从来没有被赋值
     *
     * @param o bean
     */
    public static String bean2Query(Object o) throws IllegalAccessException {
        Map<String, Object> map = Beans.bean2Map(o, new HashMap<>(16));
        var stringJoiner = new StringJoiner("&");
        map.forEach((k, v) -> {
            if (v == null) {
                stringJoiner.add(k + "=");
            } else {
                stringJoiner.add(k + "=" + v.toString());
            }
        });
        return stringJoiner.toString();
    }

    /**
     * bean 转 url
     *
     * @param o        bean
     * @param basePath 路径
     */
    public static String bean2Url(Object o, String basePath) throws IllegalAccessException {
        if (!basePath.endsWith("?")) {
            basePath = basePath + "?";
        }
        return basePath + bean2Query(o);
    }
}
