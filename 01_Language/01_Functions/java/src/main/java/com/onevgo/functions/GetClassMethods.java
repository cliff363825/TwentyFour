package com.onevgo.functions;

import java.lang.reflect.Method;

public class GetClassMethods {
    public static void main(String[] args) {
        Method[] methods = myclass.class.getMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }
}


class myclass {
    // constructor
    public myclass() {

    }

    // method 1
    private boolean myfunc1()
    {
        return true;
    }

    // method 2
    public boolean myfunc2()
    {
        return true;
    }
}