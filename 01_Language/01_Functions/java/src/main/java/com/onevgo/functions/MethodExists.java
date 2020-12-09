package com.onevgo.functions;

import cn.hutool.core.util.ReflectUtil;

public class MethodExists {
    public static boolean methodExists(Class<?> object, String methodName) {
        return ReflectUtil.getMethodByName(object, methodName) != null;
    }

    public static void main(String[] args) {

    }
}
