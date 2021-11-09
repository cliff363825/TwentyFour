package com.onevgo.j2se.design;

import com.onevgo.j2se.design.factory.ConsumerFactory;
import com.onevgo.j2se.design.factory.IExampleFactory;
import com.onevgo.j2se.design.factory.ProducerFactory;

public class FactoryMain {
    // 工厂方法
    public static void main(String[] args) {
        IExampleFactory consumerFactory = new ConsumerFactory();
        IExampleFactory producerFactory = new ProducerFactory();

        consumerFactory.getExample().run();
        producerFactory.getExample().run();
    }
}
