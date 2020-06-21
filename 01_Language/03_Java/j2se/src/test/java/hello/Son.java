package hello;

/**
 * 子类的初始化 <clinit>();
 * 1. 调用父类的 <clinit>() 方法
 * 2. 子类的静态类变量 static class variables
 * 3. 子类的静态代码块 static {}
 * 4. 2和3，按代码从上到下顺序执行
 * <p>
 * 子类的实例化方法 <init>();
 * 1. super(); 调用父类的 <init>() 方法
 * 2. 子类的非静态实例变量 non-static instance variables
 * 3. 子类的非静态代码块 non-static {}
 * 4. 2和3，按代码从上到下顺序执行
 * 5. 子类的构造器最后执行
 */
public class Son extends Father {
    private static String english = inEnglish();

    static {
        System.out.print("(9)"); // 5
    }

    private static String chinese = inChinese();

    private String name = initName();
    private int age = initAge();

    {
        System.out.print("(10)"); // 14
    }

    private Integer salary = initSalary();

    public Son() {
        // super(); // 隐式调用父类无参构造器
        System.out.print("(11)"); // 16
    }

    public static String inEnglish() {
        System.out.print("(12)"); // 4
        return "son";
    }

    public static String inChinese() {
        System.out.print("(13)"); // 6
        return "儿子";
    }

    public String initName() {
        System.out.print("(14)"); // 7 12
        return "张狗蛋";
    }

    public int initAge() {
        System.out.print("(15)"); // 8 13
        return 18;
    }

    public Integer initSalary() {
        System.out.print("(16)"); // 10 15
        return 18000;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // idea快捷键：control+enter -> generate equals() and hashCode() -> ...
    @Override
    public boolean equals(Object o) {
        // 如果名字一样，我们就认为是同一个人
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Son son = (Son) o;

        return name != null ? name.equals(son.name) : son.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
