package reflection;

import cn.hutool.core.bean.BeanUtil;
import com.onevgo.j2se.reflection.Example;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class TestBeanUtils {
    @Test
    public void test1() throws Exception {
        Example person = new Example();
        BeanUtil.setProperty(person, "name", "zhangsan");
        BeanUtil.setProperty(person, "age", 30);
        System.out.println(person);
    }

    @Test
    public void test2() throws Exception {
        Example person = new Example();

        Map<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", 30);
        BeanUtil.fillBeanWithMap(map, person, true);

        System.out.println(person);
    }
}
