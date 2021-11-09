package com.onevgo.j2se.design.factory;

public class ConsumerFactory implements IExampleFactory {
    @Override
    public Example getExample() {
        return new Consumer();
    }
}
