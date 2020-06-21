package annotation;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestAnnotation {
    @Test
    public void testAnnotation() {
        MyObject<String> myObject = new MyObject<>();

        List list = new ArrayList();
        myObject.addElement(list, 123);
        System.out.println("list = " + list);

        // class annotations=[@annotation.MyAnnotation(name=MyAnnotation, items=[], value=MyObject)]
        // method annotations=[@annotation.MyAnnotation(name=MyAnnotation, items=[@annotation.MyItem(value=item1), @annotation.MyItem(value=item2)], value=showAnnotation)]
        myObject.showAnnotation();
    }
}
