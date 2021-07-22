package com.onevgo.j2se.annotation;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        testAnnotation();
    }

    // @SuppressWarnings：抑制编译器警告
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void testSuppressWarnings(List list, Object value) {
        list.add(value);
    }

    private static void testAnnotation() {
        Method[] methods = Main.class.getDeclaredMethods();
        for (Method method : methods) {
            int modifiers = method.getModifiers();
            if (Modifier.isStatic(modifiers)) {
                continue;
            }

            if (method.isAnnotationPresent(Employee.class)) {
                Employee e = method.getAnnotation(Employee.class);
                log.info("method={}, @Employee name={} age={}", method.getName(), e.name(), e.age());
            }

            if (method.isAnnotationPresent(Department.class)) {
                Department d = method.getAnnotation(Department.class);
                for (Employee e : d.value()) {
                    log.info("method={}, @Department.employee name={} age={}", method.getName(), e.name(), e.age());
                }
            }

            Employee[] employeeAnnotations = method.getAnnotationsByType(Employee.class);
            if (employeeAnnotations != null && employeeAnnotations.length > 0) {
                for (Employee e : employeeAnnotations) {
                    log.info("method={}, Repeatable @Employee name={} age={}", method.getName(), e.name(), e.age());
                }
            }
        }
    }

    // @Override：限定重写父类方法，该注释只能用于方法
    @Override
    public String toString() {
        return "Main{}";
    }

    // @Deprecated：用于表示某个程序元素（类，方法等）已过时。通常是因为锁修饰的结构危险或存在更好的选择
    @Deprecated
    public void deprecated() {

    }

    @Employee(value = "小红", name = "小红", age = 20)
    private void employee() {

    }

    @Employee(value = "小明", name = "小明", age = 30)
    @Employee(value = "小暗", name = "小暗", age = 31)
    private void repeatableEmployee() {

    }

    @Department(value = {
            @Employee(value = "小白", name = "小白", age = 40),
            @Employee(value = "小黑", name = "小黑", age = 41)
    })
    private void department() {

    }
}
