package enum_;

import org.junit.Test;

public class TestEnum {
    @Test
    public void testEnum1() {
        SeasonEnum1 sprint = SeasonEnum1.SPRINT;
        System.out.println(sprint.getSeasonName() + ": " + sprint.getSeasonDesc()); // 春天: 春风又绿江南岸
        System.out.println("枚举的索引：" + sprint.ordinal()); // 枚举的索引：2

        SeasonEnum1[] values = SeasonEnum1.values();
        for (SeasonEnum1 value : values) {
            System.out.println(value.getSeasonName() + ": " + value.getSeasonDesc());
            //春天: 春风又绿江南岸
            //夏天: 映日荷花别样红
            //秋天: 秋水共长天一色
            //冬天: 窗含西岭千秋雪
        }

        System.out.println(SeasonEnum1.valueOf("SUMMER").getSeasonDesc());
    }

    @Test
    public void testEnum2() {
        SeasonEnum2 autumn = SeasonEnum2.AUTUMN;
        System.out.println(autumn.getSeasonName() + ": " + autumn.getSeasonDesc()); // 秋天: 秋水共长天一色
        System.out.println("枚举的索引：" + autumn.ordinal()); // 枚举的索引：2
        System.out.println("枚举的名字：" + autumn.name()); // 枚举的名字：AUTUMN

        for (SeasonEnum2 season : SeasonEnum2.values()) {
            System.out.println(season.getSeasonName() + ": " + season.getSeasonDesc());
            //春天: 春风又绿江南岸
            //夏天: 映日荷花别样红
            //秋天: 秋水共长天一色
            //冬天: 窗含西岭千秋雪
        }

        System.out.println(SeasonEnum2.valueOf("WINTER").getSeasonDesc()); // 窗含西岭千秋雪
    }
}
