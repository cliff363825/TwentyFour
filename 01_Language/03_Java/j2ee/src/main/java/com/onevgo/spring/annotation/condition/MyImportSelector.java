package com.onevgo.spring.annotation.condition;

import com.onevgo.spring.annotation.bean.Blue;
import com.onevgo.spring.annotation.bean.Yellow;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyImportSelector implements ImportSelector {
    /**
     * 返回值，就是要导入到容器中的组件全类名
     * AnnotationMetadata 当前标注 @Import 注解的类的所有注解信息
     *
     * @param importingClassMetadata
     * @return
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        // 方法不要返回 null 值
        return new String[]{Blue.class.getName(), Yellow.class.getName()};
    }
}
