package design;

import design.template.MyTemplate;
import design.template.Template;
import org.junit.Test;

public class TestTemplate {
    // 模板方法设计模式
    @Test
    public void testTemplate() {
        Template template = new MyTemplate();
        template.getTime();
    }
}
