package jvm;

import java.util.Random;

public class TestGC {
    // -Xms10m -Xmx10m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails
    // -XX:+UseSerialGC (DefNew + Tenured)
    // -XX:+UseParNewGC (ParNew + Tenured)
    // -XX:+UseParallelGC (PSYoungGen + ParOldGen)
    // -XX:+UseParallelOldGC (PSYoungGen + ParOldGen)
    // -XX:+UseConcMarkSweepGC (ParNew + CMS-initial-mark + CMS-concurrent-mark-start + CMS-concurrent-mark + CMS-concurrent-preclean-start + CMS-concurrent-preclean + CMS Final Remark + CMS-concurrent-sweep-start + CMS-concurrent-sweep + CMS-concurrent-reset-start + CMS-concurrent-reset)
    // -XX:+UseSerialOldGC
    // -XX:+UseG1GC
    public static void main(String[] args) {
        String str = "";
        while (true) {
            str += str + new Random().nextInt(11111111) + new Random().nextInt(88888888);
            str.intern();
        }
    }
}
