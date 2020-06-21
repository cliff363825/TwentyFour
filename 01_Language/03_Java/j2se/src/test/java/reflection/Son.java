package reflection;

public class Son extends Father<String> {
    static {
        System.out.println("Son.static initializer");
        m = 300;
    }

    static int m = 100;
    static final int M = 1;
}
