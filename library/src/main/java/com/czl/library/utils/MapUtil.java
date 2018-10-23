package com.czl.library.utils;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author 陈振林
 * @time 2018/8/17 9:02
 */
public class MapUtil {
    private static final String FIELD_SEPARATOR = ".";

    /**
     * 将对象转成form表单形式
     *
     * @param prefix 表单字段的前缀
     * @param object 需要传输的对象
     * @return
     */
    @Nullable
    public static Map<String, RequestBody> toMap(String prefix, String packagePrefix, Object object) {
        if (null == object) {
            return null;
        }
        if (null == prefix) {
            prefix = "";
        }
        //需要加上分隔符
        if (!TextUtils.isEmpty(prefix) && !prefix.endsWith(FIELD_SEPARATOR)) {
            prefix += FIELD_SEPARATOR;
        }
        Map<String, RequestBody> params = new HashMap<>();
        //获取实体类的所有属性，返回Field数组
        Field[] fields = object.getClass().getDeclaredFields();
        //遍历所有属性
        for (Field field : fields) {
            field.setAccessible(true);
            //获取属性的名字
            String name = field.getName();
            //获取属性的类型
            String type = field.getGenericType().toString();
            try {
                Object subObject = field.get(object);
                if (null != subObject) {
                    if (type.startsWith("java.util.List")) {
                        List list = (List) field.get(object);
                        if (null != list && !list.isEmpty()) {
                            int size = list.size();
                            for (int i = 0; i < size; i++) {
                                params.putAll(toMap(prefix + name + "[" + i + "]", packagePrefix, list.get(i)));
                            }
                        }
                    } else if ("class java.lang.Integer".equals(type)
                            || "class java.lang.String".equals(type)
                            || "class java.lang.Long".equals(type)
                            || "class java.math.BigDecimal".equals(type)) {
                        if (null != field.get(object)) {
                            String value = subObject.toString();
                            params.put(prefix + name, RequestBody.create(MediaType.parse("multipart/form-data"), value));
                        }
                    } else if (type.startsWith("class java.util.Date")) {
                        String value = String.valueOf(((Date) subObject).getTime());
                        params.put(prefix + name, RequestBody.create(MediaType.parse("multipart/form-data"), value));
                    } else if (type.startsWith(packagePrefix)) {
                        params.putAll(toMap(prefix + name, packagePrefix, field.get(object)));
                    }
                }
            } catch (Exception e) {
            }
        }
        return params;
    }
}
