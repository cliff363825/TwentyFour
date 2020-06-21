package design.template;

/**
 * 具体实现类
 */
public class MyTemplate extends Template {
    @Override
    public void code() {
        for (int i = 0; i < 10000; i++) {
            System.out.println(i);
        }
    }
}
