package design.template;

/**
 * 模板类
 */
public abstract class Template {
    public final void getTime() {
        long start = System.currentTimeMillis();
        code();
        long end = System.currentTimeMillis();
        System.out.println("执行时间是：" + (end - start) + " ms");
    }

    public abstract void code();
}
