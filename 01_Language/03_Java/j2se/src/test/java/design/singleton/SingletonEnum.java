package design.singleton;

/**
 * 枚举类型，表示该类型的对象是有限的几个
 * 我们可以限定为一个，就成了单例
 */
public enum SingletonEnum {
    INSTANCE;

    private SingletonEnum() {
        System.out.println(Thread.currentThread().getName() + ": " + getClass().getName());
    }
}
