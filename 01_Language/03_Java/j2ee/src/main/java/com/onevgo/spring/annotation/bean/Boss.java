package com.onevgo.spring.annotation.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 默认加载 ioc 容器中的组件，容器启动会调用无参构造器创建对象，在进行初始化赋值等操作
 */
@Component
public class Boss {
    // @Autowired
    private Car car;

    /**
     * 构造器要用的组件，都是从容器中获取
     *
     * @param car
     */
//    @Autowired
    public Boss(@Autowired Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    /**
     * 标注在方法，Spring容器创建当前对象，就会调用方法，完成赋值
     * 方法使用的参数，自定义类型的值从 IOC 容器中获取
     *
     * @param car
     */
//    @Autowired
    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Boss{" +
                "car=" + car +
                '}';
    }
}
