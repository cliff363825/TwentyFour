package com.onevgo.j2se.design.factory;

public class ProducerFactory implements IExampleFactory {
    @Override
    public Example getExample() {
        return new Producer();
    }
}
