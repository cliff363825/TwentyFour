package design;

import design.factory.IWorkFactory;
import design.factory.StudentWorkFactory;
import design.factory.TeacherWorkFactory;
import org.junit.Test;

public class TestFactory {
    // 工厂方法
    @Test
    public void testFactoryMethod() {
        IWorkFactory factory1 = new TeacherWorkFactory();
        IWorkFactory factory2 = new StudentWorkFactory();
        factory1.getWork().doWork();
        factory2.getWork().doWork();
    }
}
