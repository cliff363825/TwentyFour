package reflection;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URL;

public class DumpProxy<T> implements InvocationHandler {
    // 被代理对象
    private T target;

    public DumpProxy(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        byte[] bytes = ProxyGenerator.generateProxyClass(proxy.getClass().getName(), proxy.getClass().getInterfaces());

        String filename = proxy.getClass().getName().replace('.', '/');
        File file = new File(filename + ".class");
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile != null && !parentFile.exists()) {
                parentFile.mkdirs();
            }

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();

            System.out.println("Dump success! " + file.getPath());
        }

        return method.invoke(target, args);
    }
}
