package reflection;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Properties;

public class TestReflection {
    @Test
    public void testClass1() {
        // 1. 若已知具体的类，通过类的class属性获取，该方法最为安全可靠，程序性能最高
        Class clazz1 = Person.class;
        System.out.println("clazz1 = " + clazz1);

        // 2. 已知某个类的实例，调用该实例的getClass()方法获取Class对象
        Person person = new Person();
        Class clazz2 = person.getClass();
        System.out.println("clazz2 = " + clazz2);

        // 3. 已知一个类的全类名，且该类在类路径下，可通过Class类的静态方法forName()获取，可能抛出ClassNotFoundException
        try {
            Class clazz3 = Class.forName("reflection.Person");
            System.out.println("clazz3 = " + clazz3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 4. 通过类的加载器
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            Class clazz4 = classLoader.loadClass("reflection.Person");
            System.out.println("clazz4 = " + clazz4);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testClass2() {
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        System.out.println("c10 == c11 = " + (c10 == c11));
    }

    @Test
    public void testClassInit() throws ClassNotFoundException {
        // 主动引用：一定会导致Son和Father的初始化
        // Son.<clinit>() {
        //    Father.<clinit>();
        //    Son显示赋值，静态代码块，代码从上往下依次执行
        // }
//        Son son = new Son();
//        System.out.println("Son.m = " + Son.m);
//        Class.forName("reflection.Son");

        // 被动引用
//        Son[] array = new Son[5]; // 不会导致Son和Father的初始化
//        System.out.println("Son.b = " + Son.b); // 只会初始化Father
//        System.out.println("Son.M = " + Son.M); // 不会导致A和Father的初始化
    }

    @Test
    public void testClassLoader() throws Exception {
        // 1. 获取一个系统类加载器 system loader
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("systemClassLoader = " + systemClassLoader);

        // 2. 获取系统类加载器的父类加载器，即扩展类加载器 extension loader
        ClassLoader extensionClassLoader = systemClassLoader.getParent();
        System.out.println("extensionClassLoader = " + extensionClassLoader);

        // 3. 获取扩展类加载起的父类加载器，即引导类加载器 bootstrap loader
        ClassLoader bootstrapClassLoader = extensionClassLoader.getParent();
        System.out.println("bootstrapClassLoader = " + bootstrapClassLoader);

        // 4. 测试当前类由哪个类加载器进行加载
        Class personClass = Class.forName("reflection.Person");
        System.out.println("personClass.getClassLoader() = " + personClass.getClassLoader());
        String path = personClass.getProtectionDomain().getCodeSource().getLocation().getPath();
        System.out.println("path = " + path);

        // 5. 测试JDK提供的Object类由哪个类加载器加载
        Class<?> objectClass = Class.forName("java.lang.Object");
        System.out.println("objectClass.getClassLoader() = " + objectClass.getClassLoader());

        // 6. 关于类加载器的一个重要方法：
        // getResourceAsStream(String str): 获取类路径下的指定文件的输入流
        // InputStream inputStream = getClass().getClassLoader().getResourceAsStream("reflection/jdbc.properties");
        InputStream inputStream = getClass().getResourceAsStream("/reflection/jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            System.out.println("properties = " + properties);
        } finally {
            inputStream.close();
        }

        // 7. 读取 jar 包中的资源文件: mchange-config-resource-paths.txt
        InputStream inputStream1 = getClass().getResourceAsStream("/mchange-config-resource-paths.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream1));
        try {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } finally {
            bufferedReader.close();
        }
    }

    @Test
    public void testNewInstance() throws Exception {
        Class clazz = Class.forName("reflection.Person");
        Person person = (Person) clazz.newInstance();
        System.out.println("person = " + person);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testConstructor() throws Exception {
        Class clazz = Class.forName("reflection.Person");
        Constructor constructor = clazz.getConstructor(String.class, int.class);
        Person person = (Person) constructor.newInstance("呵呵", 21);
        System.out.println("person = " + person);
    }

    @Test
    public void testConstructors() {
        Class clazz = Person.class;
        Constructor[] constructors = clazz.getConstructors();
        System.out.println("constructors = " + Arrays.toString(constructors));

        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        System.out.println("declaredConstructors = " + Arrays.toString(declaredConstructors));

        for (Constructor c : declaredConstructors) {
            Parameter[] parameters = c.getParameters();
            StringBuilder sb = new StringBuilder();
            sb.append("(");
            for (int i = 0; i < parameters.length; i++) {
                sb.append(parameters[i].getType().getName());
                if (i != parameters.length - 1) {
                    sb.append(",");
                }
            }
            sb.append(")");
            System.out.println(Modifier.toString(c.getModifiers()) + " " + c.getName() + " " + sb);
        }
    }

    @Test
    public void testInterfaces() {
        Class<?>[] interfaces = Person.class.getInterfaces();
        System.out.println("interfaces = " + Arrays.toString(interfaces));
    }

    @Test
    public void testSuperclass() {
        Class<? super Person> superclass = Person.class.getSuperclass();
        System.out.println("superclass = " + superclass);
    }

    @Test
    public void testMethod1() {
        Class<Person> clazz = Person.class;
        Method[] methods = clazz.getMethods();
        System.out.println("methods = " + Arrays.toString(methods));

        Method[] declaredMethods = clazz.getDeclaredMethods();
        System.out.println("declaredMethods = " + Arrays.toString(declaredMethods));
        for (Method m : declaredMethods) {
            System.out.println(Modifier.toString(m.getModifiers()) + " " + m.getReturnType().getName() + " " + m.getDeclaringClass().getName() + " " + m.getName());
        }
    }

    @Test
    public void testMethod2() throws Exception {
        Class<Person> clazz = Person.class;
        Person person = clazz.newInstance();

        Method setName = clazz.getDeclaredMethod("setName", String.class);
        setName.invoke(person, "张三");

        Method getName = clazz.getDeclaredMethod("getName");
        System.out.println(getName.invoke(person));

        Method display = clazz.getDeclaredMethod("display", String.class);
        display.invoke(person, "China");
    }

    @Test
    public void testAnnotation() {
        Annotation[] annotations = Person.class.getAnnotations();
        System.out.println("annotations = " + Arrays.toString(annotations));

        Annotation[] declaredAnnotations = Person.class.getDeclaredAnnotations();
        System.out.println("declaredAnnotations = " + Arrays.toString(declaredAnnotations));
    }

    @Test
    public void testGenericSuperclass() {
        // 获取父类泛型类型
        Type genericSuperclass = Son.class.getGenericSuperclass();
        System.out.println("genericSuperclass = " + genericSuperclass);

        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType superclass = (ParameterizedType) genericSuperclass;
            // 获取实际的泛型类型参数数组
            Type[] actualTypeArguments = superclass.getActualTypeArguments();
            System.out.println("actualTypeArguments = " + Arrays.toString(actualTypeArguments));
        }
    }

    @Test
    public void testPackage() {
        Package aPackage = Person.class.getPackage();
        System.out.println("aPackage = " + aPackage);
    }

    @Test
    public void testField1() throws Exception {
        Class<?> clazz = Class.forName("reflection.Person");
        Field[] fields = clazz.getFields();
        System.out.println("fields = " + Arrays.toString(fields));

        Field[] declaredFields = clazz.getDeclaredFields();
        System.out.println("declaredFields = " + Arrays.toString(declaredFields));
        for (Field f : declaredFields) {
            System.out.println(Modifier.toString(f.getModifiers()) + " " + f.getType().getName() + " " + f.getDeclaringClass().getName() + " " + f.getName());
        }
    }

    @Test
    public void testField2() throws Exception {
        Class<Person> clazz = Person.class;
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);

        Person person = clazz.newInstance();
        name.set(person, "Jerry");

        System.out.println("name.get(person) = " + name.get(person));
    }
}
