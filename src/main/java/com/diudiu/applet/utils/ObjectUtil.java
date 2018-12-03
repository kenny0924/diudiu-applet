package com.diudiu.applet.utils;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;

/**
 * @author 刘智斌
 * @version 0.1
 * @time 2017/6/23
 * @since 0.1
 */
public class ObjectUtil extends BeanUtils {

    /**
     * 判断对象是否为空
     *
     * @param o
     * @return
     */
    public static boolean notEmpty(Object o) {
        if (o == null)
            return false;

        if (o instanceof String) {
            return !("".equals(((String) o).trim()));
        }
        if (o instanceof Map) {
            return !((Map) o).isEmpty();
        }
        if (o instanceof Collection) {
            return !((Collection) o).isEmpty();
        }
        return o != null;
    }

    public static boolean isEmpty(Object o) {
        return !notEmpty(o);
    }

    // 判断对象是否相等
    // 只能对基础类型进行判断
    public static boolean equals(Object one, Object two) {
        return ((one == two) ||
                (one != null && getString(one).equals(getString(two))));
    }

    /**
     * 对象转化成String
     *
     * @param o
     * @author Zhibin Liu
     * @time 9/22/17 11:44
     */
    public static String getString(Object o) {

        return o == null ? "" : String.valueOf(o);
    }

    public static boolean isCollection(Class clazz) {
        String name = clazz.getName();
        if (name.equals(Collection.class.getName())) {
            return true;
        } else {
            Class[] interfaces = clazz.getInterfaces();
            if (interfaces != null) {
                for (Class iClass : interfaces) {
                    if (isCollection(iClass)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 补全字符串
     *
     * @param str    源字符串
     * @param length 补全最终长度
     * @author Zhibin Liu
     * @time 11/13/17 12:13
     */
    public static String prefixFill(String sour, int length, String str) {
        if (sour.length() == length)
            return sour;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - sour.length(); i++) {
            sb.append(str);
        }
        return sb.append(sour).toString();
    }


    public static void copyNonNullProperties(Object source, Object target) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        Class<?> actualEditable = target.getClass();
        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null &&
                            ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }
                            Object value = readMethod.invoke(source);
                            if (value != null) {
                                if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                    writeMethod.setAccessible(true);
                                }
                                writeMethod.invoke(target, value);
                            }
                        } catch (Throwable ex) {
                            throw new FatalBeanException(
                                    "Could not copy property '" + targetPd.getName() + "' from source to target", ex);
                        }
                    }
                }
            }
        }
    }
}
