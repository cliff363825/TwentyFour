package com.onevgo.functions;

import java.lang.reflect.Method;
import java.util.Arrays;

public class GetClassMethods {
    public static Method[] getClassMethods(Class<?> clazz) {
        return clazz.getMethods();
    }

    public static void main(String[] args) {
        Arrays.stream(getClassMethods(myclass.class)).forEach(m -> System.out.println(m.getName()));
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
}
