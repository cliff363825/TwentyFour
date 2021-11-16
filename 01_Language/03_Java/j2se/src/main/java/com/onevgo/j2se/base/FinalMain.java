package com.onevgo.j2se.base;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FinalMain {
    // 1. 声明并赋值
    public final double PI = 3.14;

    // 2. 代码块中赋值
    public final double PI1;

    {
        PI1 = 3.141;
    }

    // 3. 构造器中赋值
    public final double PI2;

    public FinalMain() {
        PI2 = 3.1415;
    }

    public static void main(String[] args) {
        FinalMain finalMain = new FinalMain();
        log.info("{} {} {}", finalMain.PI, finalMain.PI1, finalMain.PI2);
    }
}
