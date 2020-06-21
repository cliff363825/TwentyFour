package design.singleton;

/**
 * 懒汉式：延迟创建这个实例对象
 * <p>
 * 1. 构造器私有化
 * 2. 用一个静态变量保存这个唯一的实例
 * 3. 提供一个静态方法，获取这个实例对象
 */
public class SingletonLazy {
    private static SingletonLazy instance = null;

    private SingletonLazy() {
        System.out.println(Thread.currentThread().getName() + ": " + getClass().getName());
    }

    public static SingletonLazy getInstance() {
        // 存在线程安全问题
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
}
