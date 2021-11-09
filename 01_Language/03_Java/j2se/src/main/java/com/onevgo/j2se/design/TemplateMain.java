package com.onevgo.j2se.design;

import com.onevgo.j2se.design.template.Calculator;
import com.onevgo.j2se.design.template.ExampleTemplate;

public class TemplateMain {
    // 模板方法设计模式
    public static void main(String[] args) {
        ExampleTemplate template = new Calculator();
        template.exec();
    }
}
