package sort1;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class TestSort {
    @Test
    public void testComparable() {
        Goods[] goodsArr = new Goods[4];
        goodsArr[0] = new Goods("红楼梦", 100);
        goodsArr[1] = new Goods("西游记", 80);
        goodsArr[2] = new Goods("三国演义", 140);
        goodsArr[3] = new Goods("水浒传", 120);

        System.out.println("goodsArr = " + Arrays.toString(goodsArr));
        Arrays.sort(goodsArr);
        System.out.println("goodsArr = " + Arrays.toString(goodsArr));
    }

    @Test
    public void testComparator() {
        Goods[] goodsArr = new Goods[4];
        goodsArr[0] = new Goods("红楼梦", 100);
        goodsArr[1] = new Goods("西游记", 80);
        goodsArr[2] = new Goods("三国演义", 140);
        goodsArr[3] = new Goods("水浒传", 120);

        System.out.println("goodsArr = " + Arrays.toString(goodsArr));
        Arrays.sort(goodsArr, new Comparator<Goods>() {
            @Override
            public int compare(Goods o1, Goods o2) {
                if (o1 != null && o2 != null) {
                    if (o1.getName() != null && o2.getName() != null) {
                        return o1.getName().compareTo(o2.getName());
                    }
                    if (o1.getName() != null) {
                        return 1;
                    }
                    if (o2.getName() != null) {
                        return -1;
                    }

                    return 0;
                }
                if (o1 != null) {
                    return 1;
                }
                if (o2 != null) {
                    return -1;
                }

                return 0;
            }
        });
        System.out.println("goodsArr = " + Arrays.toString(goodsArr));
    }
}
