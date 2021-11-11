package com.onevgo.j2se.enums;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EnumMain {
    public static void main(String[] args) {
//        testEnum1();
        testEnum2();
    }

    private static void testEnum1() {
        SeasonEnum1 sprint = SeasonEnum1.SPRINT;
        log.info("{}", sprint);
        log.info("ordinal={}", sprint.ordinal());

        SeasonEnum1[] values = SeasonEnum1.values();
        for (SeasonEnum1 value : values) {
            log.info("{}: {}", value.getSeasonName(), value.getSeasonDesc());
            //春天: 春风又绿江南岸
            //夏天: 映日荷花别样红
            //秋天: 秋水共长天一色
            //冬天: 窗含西岭千秋雪
        }

        log.info("{}", SeasonEnum1.valueOf("SUMMER").getSeasonDesc());
    }

    private static void testEnum2() {
        SeasonEnum2 autumn = SeasonEnum2.AUTUMN;
        log.info("{}", autumn);
        log.info("ordinal={}", autumn.ordinal());
        log.info("name={}", autumn.name());

        for (SeasonEnum2 season : SeasonEnum2.values()) {
            log.info("{}: {}", season.getSeasonName(), season.getSeasonDesc());
            //春天: 春风又绿江南岸
            //夏天: 映日荷花别样红
            //秋天: 秋水共长天一色
            //冬天: 窗含西岭千秋雪
        }

        log.info(Enum.valueOf(SeasonEnum2.class, "WINTER").getSeasonDesc());
    }
}
