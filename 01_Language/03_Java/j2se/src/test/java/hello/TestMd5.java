package hello;

import cn.hutool.crypto.SecureUtil;
import org.junit.Test;

public class TestMd5 {
    @Test
    public void test1() {
        System.out.println("md5(admin)=" + SecureUtil.md5("admin"));
    }
}
