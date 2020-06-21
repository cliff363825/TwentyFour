package design.singleton;

/**
 * 饿汉式：在类初始化时直接创建实例对象，不管你是否需要这个对象都会创建
 * <p>
 * 1. 构造器私有化
 * 2. 自行创建，并且用静态变量保存
 * 3. 向外提供这个实例
 * 4. 强调这是一个单例，我们可以用final修饰
 */
public class Singleton {
    private final static Singleton INSTANCE = new Singleton();

    private Singleton() {
        System.out.println(Thread.currentThread().getName() + ": " + getClass().getName());
    }

    public static Singleton getInstance() {
        return INSTANCE;
    }
}
