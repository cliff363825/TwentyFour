package enum_;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class SeasonEnum1 {
    // 1. 枚举类对象的属性不应允许被改动，所以应该使用private final修饰
    private final String seasonName;
    private final String seasonDesc;

    public final static SeasonEnum1 SPRINT = new SeasonEnum1("春天", "春风又绿江南岸");
    public final static SeasonEnum1 SUMMER = new SeasonEnum1("夏天", "映日荷花别样红");
    public final static SeasonEnum1 AUTUMN = new SeasonEnum1("秋天", "秋水共长天一色");
    public final static SeasonEnum1 WINTER = new SeasonEnum1("冬天", "窗含西岭千秋雪");

    private final static SeasonEnum1[] ENUMS;

    static {
        List<SeasonEnum1> enumList = new ArrayList<>();
        Field[] declaredFields = SeasonEnum1.class.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            // public static final SeasonEnum1
            int modifiers = declaredField.getModifiers();
            if (Modifier.isPublic(modifiers) &&
                    Modifier.isStatic(modifiers) &&
                    Modifier.isFinal(modifiers) &&
                    declaredField.getType() == SeasonEnum1.class
            ) {
                try {
                    enumList.add((SeasonEnum1) declaredField.get(SeasonEnum1.class));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        ENUMS = enumList.toArray(new SeasonEnum1[0]);
    }

    // 2. 枚举类的使用 private final 修饰的属性应该在构造器中为其赋值
    // 3. 若枚举类显示的定义了带参数的构造器，则在列出枚举值时也必须对应的传入参数
    private SeasonEnum1(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    public static SeasonEnum1[] values() {
        return ENUMS;
    }

    public static SeasonEnum1 valueOf(String name) {
        switch (name) {
            case "SPRINT":
                return SPRINT;
            case "SUMMER":
                return SUMMER;
            case "AUTUMN":
                return AUTUMN;
            case "WINTER":
                return WINTER;
            default:
                throw new IllegalArgumentException("No enum constant " + SeasonEnum1.class.getCanonicalName() + "." + name);
        }
    }

    public int ordinal() {
        for (int i = 0; ; i++) {
            if (ENUMS[i] == this) {
                return i;
            }
        }
    }
}
