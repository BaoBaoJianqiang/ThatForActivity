package com.example.jianqiang.mypluginlibrary;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RefInvoke {

    public static Object createObject(String className, Class[] pareTyple, Object[] pareVaules) {
        try {
            Class r = Class.forName(className);
            return createObject(r, pareTyple, pareVaules);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Object createObject(Class clazz, Class[] pareTyple, Object[] pareVaules) {
        try {
            Constructor ctor = clazz.getConstructor(pareTyple);
            return ctor.newInstance(pareVaules);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Object invokeInstanceMethod(Object obj, String methodName, Class[] pareTyple, Object[] pareVaules) {
        if (obj == null)
            return null;

        try {
            //调用一个private方法
            Method method = obj.getClass().getDeclaredMethod(methodName, pareTyple); //在指定类中获取指定的方法
            method.setAccessible(true);
            return method.invoke(obj, pareVaules);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Object invokeInstanceMethod(Object obj, String methodName) {
        Class[] pareTyple = {};
        Object[] pareVaules = {};

        return invokeInstanceMethod(obj, methodName, pareTyple, pareVaules);
    }

    public static Object invokeStaticMethod(String className, String method_name) {
        Class[] p1 = null;
        Object[] v1 = null;

        return invokeStaticMethod(className, method_name, p1, v1);
    }

    public static Object invokeStaticMethod(String className, String method_name, Class classType, Object pValue) {
        Class[] p1 = {classType};
        Object[] v1 = {pValue};

        return invokeStaticMethod(className, method_name, p1, v1);
    }

    public static Object invokeStaticMethod(Class clazz, String method_name, Class classType, Object pValue) {
        Class[] p1 = {classType};
        Object[] v1 = {pValue};

        return invokeStaticMethod(clazz, method_name, p1, v1);
    }

    public static Object invokeStaticMethod(String className, String method_name, Class[] pareTyple, Object[] pareVaules) {
        try {
            Class obj_class = Class.forName(className);
            return invokeStaticMethod(obj_class, method_name, pareTyple, pareVaules);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object invokeStaticMethod(Class clazz, String method_name, Class[] pareTyple, Object[] pareVaules) {
        try {
            Method method = clazz.getMethod(method_name, pareTyple);
            return method.invoke(null, pareVaules);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Object getStaticFieldOjbect(String className, String filedName) {
        return getFieldOjbect(className, null, filedName);
    }

    public static Object getStaticFieldOjbect(Class clazz, String filedName) {
        return getFieldOjbect(clazz, null, filedName);
    }

    public static void setStaticFieldOjbect(String classname, Object obj, String filedName, Object filedVaule) {
        setFieldOjbect(classname, null, filedName, filedVaule);
    }

    public static Object getFieldOjbect(Class class1, Object obj, String filedName) {
        try {
            Field field = class1.getDeclaredField(filedName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getFieldOjbect(String className, Object obj, String filedName) {
        try {
            Class obj_class = Class.forName(className);
            return getFieldOjbect(obj_class, obj, filedName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setFieldOjbect(Class class1, Object obj, String filedName, Object filedVaule) {
        try {
            Field field = class1.getDeclaredField(filedName);
            field.setAccessible(true);
            field.set(obj, filedVaule);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setFieldOjbect(String classname, Object obj, String filedName, Object filedVaule) {
        try {
            Class obj_class = Class.forName(classname);
            setFieldOjbect(obj_class, obj, filedName, filedVaule);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}