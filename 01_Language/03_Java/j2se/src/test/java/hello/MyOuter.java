package hello;

import java.io.Serializable;

public class MyOuter implements Serializable {
    static final long serialVersionUID = 1L;

    static {
        System.out.println("MyOuter.static initializer");
    }

    class MyInner {
        // static {} // Inner classes cannot have static declarations
        public MyInner() {
            System.out.println("MyInner.MyInner");
        }
    }

    static class MyStaticInner {
        static {
            System.out.println("MyStaticInner.static initializer");
        }

        public MyStaticInner() {
            System.out.println("MyStaticInner.MyStaticInner");
        }
    }

    public MyOuter() {
        System.out.println("MyOuter.MyOuter");
    }
}
