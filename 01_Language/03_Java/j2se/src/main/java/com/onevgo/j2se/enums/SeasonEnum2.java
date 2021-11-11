package com.onevgo.j2se.enums;

public enum SeasonEnum2 {
    //
    SPRINT("春天", "春风又绿江南岸"),
    SUMMER("夏天", "映日荷花别样红"),
    AUTUMN("秋天", "秋水共长天一色"),
    WINTER("冬天", "窗含西岭千秋雪"),
    ;

    // 1. 枚举类对象的属性不应允许被改动，所以应该使用private final修饰
    private final String seasonName;
    private final String seasonDesc;

    // 2. 枚举类的使用 private final 修饰的属性应该在构造器中为其赋值
    // 3. 若枚举类显示的定义了带参数的构造器，则在列出枚举值时也必须对应的传入参数
    SeasonEnum2(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }
}
