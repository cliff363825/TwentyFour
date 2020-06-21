package com.onevgo.spring.annotation.config;

import com.onevgo.spring.annotation.bean.Boss;
import com.onevgo.spring.annotation.bean.Car;
import com.onevgo.spring.annotation.bean.Color;
import com.onevgo.spring.annotation.dao.BookDAO;
import com.onevgo.spring.annotation.service.BookService;
import org.springframework.context.annotation.*;

/**
 * 自动装配
 * 1. Spring 利用依赖注入（DI） 完成对 IOC 容器中各个组件的依赖关系赋值
 * 2. @Autowired
 * <ul>
 * <li>1. 默认优先按照类型去容器中去找对应的组件 applicationContext.getBean(BookDAO.class);</li>
 * <li>2. 如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找 applicationContext.getBean("bookDAO1");</li>
 * <li>3. @Qualifier("bookDAO")，使用 @Qualifier 指定需要装配的组件的id，而不是使用属性名</li>
 * <li>4. 自动装配，一定要将属性赋值好，没有就会报错</li>
 * <li>可以使用 @Autowired(required = false)</li>
 * <li>5. @Primary 让Spring进行自动装配的时候，默认使用首选的 bean</li>
 * <li>也可以继续使用 @Qualifier 指定需要装配的 bean 的名字</li>
 * </ul>
 * 3. Spring 还支持 @Resource (JSR250) 和 @Inject (JSR330)
 * <ul>
 * <li>1. @Resource 可以和 @Autowired 一样实现自动装配功能，默认是按照组件名称进行装配的</li>
 * <li>不支持 @Primary 功能，不支持 @Autowired(required = false)</li>
 * <li>2. @Inject</li>
 * <li>需要导入 javax.inject 的包，和Autowired的功能一样。没有required = false的功能</li>
 * <li>3. @Autowired Spring定义的，@Resource @Inject都是java规范</li>
 * <li>AutowiredAnnotationBeanPostProcessor 解析完成自动装配功能</li>
 * </ul>
 * 4. @Autowired 构造器，参数，方法，属性；都是从容器中获取参数组件的值
 * <ul>
 * <li>1. 标注在方法位置，@Bean + 方法参数，参数从容器中获取；默认不写 @Autowired，效果是一样的，都能自动装载</li>
 * <li>2. 标在构造器上，如果组件只有一个有参构造器，这个有参构造器的 @Autowired 可以省略，参数位置的组件还是可以自动从容器中获取</li>
 * <li>3. 放在参数位置</li>
 * </ul>
 * 5. 自定义组件想要使用 Spring 容器底层的一些组件 ApplicationContext BeanFactory xxx</li>
 * <ul>
 * <li>1. 自定义组件实现 XxxAware，在创建对象的时候，会调用接口规定的方法注入相关组件</li>
 * <li>2. 把 Spring 底层一些组件注入到自定义的 Bean 中，</li>
 * <li>3. XxxAware 功能使用XxxProcessor</li>
 * <ul>
 * <li>1. ApplicationContextAware ==> ApplicationContextAwareProcessor</li>
 * </ul>
 * </ul>
 */
@Import({Car.class, Boss.class, BookService.class})
@Configuration
public class MainConfigOfAutowired {
    @Primary
    @Bean("bookDAO2")
    public BookDAO bookDAO() {
        BookDAO bookDAO = new BookDAO();
        bookDAO.setLabel("2");
        return bookDAO;
    }

    /**
     * <code>@Bean</code> 标注的方法创建对象的时候，方法参数的值从容器中获取
     * 方法参数的值可以省略 @Autowired
     *
     * @param car
     * @return
     */
    @Bean
    public Color color(/*@Autowired*/ Car car) {
        Color color = new Color();
        color.setCar(car);
        return color;
    }
}
