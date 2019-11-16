package com.ztcaoll222.beans;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

/**
 * @author ztcaoll222
 * @date 2019/11/13 18:02
 */
public class Beans {
    private Beans() {
    }

    /**
     * 获得要设置字段的 set 函数
     *
     * @param key    字段名
     * @param tClass bean 的 class
     * @param o      bean 的实例
     * @param vClass set 函数的入参类型
     */
    private static <T> Optional<AbstractSetMethod<Object>> findSetMethod0(String key, Class<T> tClass, Object o, Class<?> vClass) {
        try {
            Method method = tClass.getDeclaredMethod("set" + Character.toUpperCase(key.charAt(0)) + key.substring(1), vClass);
            return Optional.of(new AbstractSetMethod<>(method, o) {
                @Override
                public void invoke0(Object value) throws IllegalAccessException, InvocationTargetException {
                    method.invoke(o, value);
                }
            });
        } catch (NoSuchMethodException e) {
            return Optional.empty();
        }
    }

    /**
     * 获得要设置字段的 set 函数
     * <p>
     * 因为有可能出现字段的包装类而对应的 set 函数是其基本类型, 所以两种都搜索一下
     *
     * @param key    字段名
     * @param tClass bean 的 class
     * @param o      bean 的实例
     * @param vClass set 函数的入参类型
     */
    private static <T> Optional<AbstractSetMethod<Object>> findSetMethod(String key, Class<T> tClass, Object o, Class<?> vClass) {
        if (vClass == Boolean.class) {
            return findSetMethod0(key, tClass, o, vClass).or(() -> findSetMethod0(key, tClass, o, boolean.class));
        } else if (vClass == Byte.class) {
            return findSetMethod0(key, tClass, o, vClass).or(() -> findSetMethod0(key, tClass, o, byte.class));
        } else if (vClass == Short.class) {
            return findSetMethod0(key, tClass, o, vClass).or(() -> findSetMethod0(key, tClass, o, short.class));
        } else if (vClass == Integer.class) {
            return findSetMethod0(key, tClass, o, vClass).or(() -> findSetMethod0(key, tClass, o, int.class));
        } else if (vClass == Long.class) {
            return findSetMethod0(key, tClass, o, vClass).or(() -> findSetMethod0(key, tClass, o, long.class));
        } else if (vClass == Character.class) {
            return findSetMethod0(key, tClass, o, vClass).or(() -> findSetMethod0(key, tClass, o, char.class));
        } else if (vClass == Float.class) {
            return findSetMethod0(key, tClass, o, vClass).or(() -> findSetMethod0(key, tClass, o, float.class));
        } else if (vClass == Double.class) {
            return findSetMethod0(key, tClass, o, vClass).or(() -> findSetMethod0(key, tClass, o, double.class));
        } else {
            return findSetMethod0(key, tClass, o, vClass);
        }
    }

    /**
     * 获得要设置的字段
     * <p>
     * 如果
     *
     * @param key    字段名
     * @param tClass bean 的 class
     * @param o      bean 的实例
     * @see com.ztcaoll222.beans.Beans#findSetMethod(java.lang.String, java.lang.Class, java.lang.Object, java.lang.Class)
     * 没有找到对应是 set 函数, 那么就会调用这个函数直接给字段赋值
     */
    private static <T> Optional<AbstractSetMethod<Object>> findSetField(String key, Class<T> tClass, Object o) {
        try {
            Field field = tClass.getDeclaredField(key);
            return Optional.of(new AbstractSetMethod<>(field, o) {
                @Override
                public void invoke0(Object value) throws IllegalAccessException, InvocationTargetException {
                    field.set(o, value);
                }
            });
        } catch (NoSuchFieldException e) {
            return Optional.empty();
        }
    }

    /**
     * map 转 bean
     *
     * @param map      map
     * @param tClass   bean 的 class
     * @param initargs 构造 bean 时的参数
     */
    public static <T> T map2Bean(Map<String, Object> map, Class<T> tClass, Object... initargs) throws Exception {
        T obj = tClass.getDeclaredConstructor().newInstance(initargs);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            var setMethod = findSetMethod(key, tClass, obj, value.getClass()).or(() -> findSetField(key, tClass, obj));
            if (setMethod.isPresent()) {
                setMethod.get().invoke(value);
            }
        }
        return obj;
    }

    /**
     * 获得 bean 的某个字段的值
     *
     * @param field 字段
     * @param o     bean
     */
    private static Object getFieldValue(Field field, Object o) throws IllegalAccessException {
        Object value;
        if (!field.canAccess(o)) {
            field.setAccessible(true);
            value = field.get(o);
            field.setAccessible(false);
        } else {
            value = field.get(o);
        }
        return value;
    }

    /**
     * bean 转 map
     *
     * @param o   bean
     * @param map 初始化好的 map
     * @return map
     */
    public static Map<String, Object> bean2Map(Object o, Map<String, Object> map) throws IllegalAccessException {
        Class<?> oClass = o.getClass();
        for (Field declaredField : oClass.getDeclaredFields()) {
            var value = getFieldValue(declaredField, o);
            map.put(declaredField.getName(), value);
        }
        return map;
    }
}
