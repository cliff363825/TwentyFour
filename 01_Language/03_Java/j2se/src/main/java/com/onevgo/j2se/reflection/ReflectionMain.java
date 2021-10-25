package com.onevgo.j2se.reflection;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Properties;

@Slf4j
public class ReflectionMain {
    public static void main(String[] args) {
//        testClass1();
//        testClass2();
//        testClassInit();
//        testClassLoader();
//        testNewInstance();
//        testConstructor();
//        testConstructors();
//        testInterfaces();
//        testSuperclass();
//        testMethod1();
//        testMethod2();
//        testAnnotation();
//        testGenericSuperclass();
//        testPackage();
//        testField1();
        testField2();
    }

    private static void testClass1() {
        // 1. 若已知具体的类，通过类的class属性获取，该方法最为安全可靠，程序性能最高
        Class clazz1 = Example.class;
        log.info("{}", clazz1);

        // 2. 已知某个类的实例，调用该实例的getClass()方法获取Class对象
        Example example = new Example();
        Class clazz2 = example.getClass();
        log.info("{}", clazz2);

        // 3. 已知一个类的全类名，且该类在类路径下，可通过Class类的静态方法forName()获取，可能抛出ClassNotFoundException
        try {
            Class clazz3 = Class.forName("com.onevgo.j2se.reflection.Example");
            log.info("{}", clazz3);
        } catch (ClassNotFoundException e) {
            log.error("error=", e);
        }

        // 4. 通过类的加载器
        ClassLoader classLoader = ReflectionMain.class.getClassLoader();
        try {
            Class clazz4 = classLoader.loadClass("com.onevgo.j2se.reflection.Example");
            log.info("{}", clazz4);
        } catch (ClassNotFoundException e) {
            log.error("error=", e);
        }
    }

    private static void testClass2() {
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
        log.info("{}", c10 == c11);
    }

    private static void testClassInit() {
        // 主动引用：一定会导致 ExampleA 和 SuperExample 的初始化
        // ExampleA.<clinit>() {
        //    SuperExample.<clinit>();
        //    静态类变量默认初始化
        //    静态类型变显示初始化和静态代码块从上到下顺序执行
        // }

//        ExampleA exampleA = new ExampleA();
//        log.info("v4={}", exampleA.getV4());

//        try {
//            Class.forName("com.onevgo.j2se.reflection.ExampleA");
//            System.out.println(ExampleA.getV4());
//        } catch (ClassNotFoundException e) {
//            log.error("error=", e);
//        }

        // 被动引用
        ExampleA[] array = new ExampleA[5]; // 不会导致 ExampleA 和 SuperExample 的初始化
//        log.info("{}", ExampleA.getV2()); // 只会初始化 SuperExample
        log.info("{}", ExampleA.M); // 不会导致 ExampleA 和 SuperExample 的初始化
    }

    private static void testClassLoader() {
        // 1. 获取一个系统类加载器 system loader
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        log.info("systemClassLoader={}", systemClassLoader);

        // 2. 获取系统类加载器的父类加载器，即扩展类加载器 extension loader
        ClassLoader extensionClassLoader = systemClassLoader.getParent();
        log.info("extensionClassLoader={}", extensionClassLoader);

        // 3. 获取扩展类加载起的父类加载器，即引导类加载器 bootstrap loader
        ClassLoader bootstrapClassLoader = extensionClassLoader.getParent();
        log.info("bootstrapClassLoader={}", bootstrapClassLoader);

        // 4. 测试当前类由哪个类加载器进行加载
        try {
            Class exampleClass = Class.forName("com.onevgo.j2se.reflection.Example");
            log.info("Example classLoader={}", exampleClass.getClassLoader());
            String path = exampleClass.getProtectionDomain().getCodeSource().getLocation().getPath();
            log.info("path={}", path);
        } catch (ClassNotFoundException e) {
            log.error("error=", e);
        }

        // 5. 测试JDK提供的Object类由哪个类加载器加载
        try {
            Class objectClass = Class.forName("java.lang.Object");
            log.info("Object classLoader={}", objectClass.getClassLoader());
        } catch (ClassNotFoundException e) {
            log.error("error=", e);
        }

        // 6. 关于类加载器的一个重要方法：
        // getResourceAsStream(String str): 获取类路径下的指定文件的输入流
        // InputStream inputStream = getClass().getClassLoader().getResourceAsStream("reflection/jdbc.properties");
        InputStream inputStream = ReflectionMain.class.getResourceAsStream("/reflection/jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            log.info("{}", properties);
        } catch (IOException e) {
            log.error("error=", e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error("error=", e);
            }
        }

        // 7. 读取 jar 包中的资源文件: cn.hutool.log.LogFactory
        InputStream inputStream1 = ReflectionMain.class.getResourceAsStream("/META-INF/services/cn.hutool.log.LogFactory");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream1));
        try {
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                log.info("{}", line);
            }
        } catch (IOException e) {
            log.error("error=", e);
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void testNewInstance() {
        try {
            Class clazz = Class.forName("com.onevgo.j2se.reflection.Example");
            Example example = (Example) clazz.newInstance();
            log.info("example={}", example);
        } catch (Exception e) {
            log.error("error=", e);
        }
    }

    private static void testConstructor() {
        try {
            Class clazz = Class.forName("com.onevgo.j2se.reflection.Example");
            Constructor constructor = clazz.getConstructor(String.class, int.class);
            Example example = (Example) constructor.newInstance("呵呵", 21);
            log.info("example={}", example);
        } catch (Exception e) {
            log.error("error=", e);
        }
    }

    private static void testConstructors() {
        Class clazz = Example.class;
        Constructor[] constructors = clazz.getConstructors();
        log.info("constructors={}", Arrays.toString(constructors));

        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        log.info("declaredConstructors={}", Arrays.toString(declaredConstructors));

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

    private static void testInterfaces() {
        Class<?>[] interfaces = Example.class.getInterfaces();
        log.info("interfaces={}", Arrays.toString(interfaces));
    }

    private static void testSuperclass() {
        Class<? super Example> superclass = Example.class.getSuperclass();
        log.info("superClass = {}", superclass);
    }

    private static void testMethod1() {
        Class<Example> clazz = Example.class;
        Method[] methods = clazz.getMethods();
        log.info("methods={}", Arrays.toString(methods));

        Method[] declaredMethods = clazz.getDeclaredMethods();
        log.info("declaredMethods={}", Arrays.toString(declaredMethods));
        for (Method m : declaredMethods) {
            System.out.println(Modifier.toString(m.getModifiers()) + " " + m.getReturnType().getName() + " " + m.getDeclaringClass().getName() + " " + m.getName());
        }
    }

    private static void testMethod2() {
        Class<Example> clazz = Example.class;
        try {
            Example person = clazz.newInstance();

            Method setName = clazz.getDeclaredMethod("setName", String.class);
            setName.invoke(person, "张三");

            Method getName = clazz.getDeclaredMethod("getName");
            log.info("getName={}", getName.invoke(person));
        } catch (Exception e) {
            log.error("error=", e);
        }
    }

    private static void testAnnotation() {
        Annotation[] annotations = Example.class.getAnnotations();
        log.info("annotations={}", Arrays.toString(annotations));

        Annotation[] declaredAnnotations = Example.class.getDeclaredAnnotations();
        log.info("declaredAnnotations={}", Arrays.toString(declaredAnnotations));
    }

    private static void testGenericSuperclass() {
        // 获取父类泛型类型
        Type genericSuperclass = ExampleA.class.getGenericSuperclass();
        log.info("genericSuperclass={}", genericSuperclass);

        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType superclass = (ParameterizedType) genericSuperclass;
            // 获取实际的泛型类型参数数组
            Type[] actualTypeArguments = superclass.getActualTypeArguments();
            log.info("actualTypeArguments={}", Arrays.toString(actualTypeArguments));
        }
    }

    private static void testPackage() {
        Package p = Example.class.getPackage();
        log.info("package={}", p);
    }

    private static void testField1() {
        try {
            Class<?> clazz = Class.forName("com.onevgo.j2se.reflection.Example");
            Field[] fields = clazz.getFields();
            log.info("fields={}", Arrays.toString(fields));

            Field[] declaredFields = clazz.getDeclaredFields();
            log.info("declaredFields={}", Arrays.toString(declaredFields));
            for (Field f : declaredFields) {
                System.out.println(f.getDeclaringClass().getName() + ": " + Modifier.toString(f.getModifiers()) + " " + f.getType().getName() + " " + f.getName());
            }
        } catch (ClassNotFoundException e) {
            log.error("error=", e);
        }
    }

    private static void testField2() {
        Class<Example> clazz = Example.class;
        try {
            Field name = clazz.getDeclaredField("name");
            name.setAccessible(true);

            Example example = clazz.newInstance();
            name.set(example, "Jerry");

            log.info("name={}", name.get(example));
        } catch (Exception e) {
            log.error("error=", e);
        }
    }
}
