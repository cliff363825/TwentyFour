# Java篇之Spring
## 搭建 Spring 开发环境

1. 把以下 jar 包加入到工程的 classpath 下:
    1. commons-logging-xxx.jar
    2. spring-beans-xxx.jar
    3. spring-context-xxx.jar
    4. spring-core-xxx.jar
    5. spring-expression-xxx.jar
2. Spring 的配置文件: 一个典型的 Spring 项目需要创建一个或多个 Bean 配置文件, 这些配置文件用于在 Spring IOC 容器里配置 Bean. Bean 的配置文件可以放在 classpath 下, 也可以放在其它目录下.

## Spring 中的 Bean 配置

1. 内容提要
    1. IOC & DI 概述
    2. 配置 bean
        1. 配置形式：基于 XML 文件的方式；基于注解的方式
        2. Bean 的配置方式：通过全类名（反射）、通过工厂方法（静态工厂方法 & 实例工厂方法）、FactoryBean
        3. IOC 容器 BeanFactory & ApplicationContext 概述
        4. 依赖注入的方式：属性注入；构造器注入
        5. 注入属性值细节
        6. 自动转配
        7. bean 之间的关系：继承；依赖
        8. bean 的作用域：singleton；prototype；WEB 环境作用域
        9. 使用外部属性文件
        10. spEL 
        11. IOC 容器中 Bean 的生命周期
        12. Spring 4.x 新特性：泛型依赖注入
    
2. IOC 和 DI
    1. IOC(Inversion of Control)：其思想是反转资源获取的方向. 传统的资源查找方式要求组件向容器发起请求查找资源. 作为回应, 容器适时的返回资源. 而应用了 IOC 之后, 则是容器主动地将资源推送给它所管理的组件, 组件所要做的仅是选择一种合适的方式来接受资源. 这种行为也被称为查找的被动形式
    2. DI(Dependency Injection) — IOC 的另一种表述方式：即组件以一些预先定义好的方式(例如: setter 方法)接受来自如容器的资源注入. 相对于 IOC 而言，这种表述更直接
3. 在 Spring 的 IOC 容器里配置 Bean
    1. 在 xml 文件中通过 bean 节点来配置 bean
        ```
        <!-- 配置 bean -->
        <bean id="myBean" class="com.onevgo.MyBean"></bean>
        ```
    2. id：Bean 的名称。
        1. 在 IOC 容器中必须是唯一的
        2. 若 id 没有指定，Spring 自动将权限定性类名作为 Bean 的名字
        3. ~~id~~ name 可以指定多个名字，名字之间可用逗号、分号、或空格分隔
4. Spring 容器
    1. 在 Spring IOC 容器读取 Bean 配置创建 Bean 实例之前, 必须对它进行实例化. 只有在容器实例化后, 才可以从 IOC 容器里获取 Bean 实例并使用.
    2. Spring 提供了两种类型的 IOC 容器实现. 
        1. BeanFactory: IOC 容器的基本实现.
        2. ApplicationContext: 提供了更多的高级特性. 是 BeanFactory 的子接口.
        3. BeanFactory 是 Spring 框架的基础设施，面向 Spring 本身；ApplicationContext 面向使用 Spring 框架的开发者，几乎所有的应用场合都直接使用 ApplicationContext 而非底层的 BeanFactory
        4. 无论使用何种方式, 配置文件时相同的.
    3. ApplicationContext
        1. ApplicationContext 的主要实现类：
            1. ClassPathXmlApplicationContext：从 类路径下加载配置文件
            2. FileSystemXmlApplicationContext: 从文件系统中加载配置文件
        2. ConfigurableApplicationContext 扩展于 ApplicationContext，新增加两个主要方法：refresh() 和 close()， 让 ApplicationContext 具有启动、刷新和关闭上下文的能力
        3. ApplicationContext 在初始化上下文时就实例化所有单例的 Bean。
        4. WebApplicationContext 是专门为 WEB 应用而准备的，它允许从相对于 WEB 根目录的路径中完成初始化工作
5. 从 IOC 容器中获取 Bean
  
    1. 调用 ApplicationContext 的 getBean() 方法
6. 依赖注入的方式
    1. Spring 支持 3 种依赖注入的方式
        1. 属性注入
        2. 构造器注入
        3. 工厂方法注入（很少使用，不推荐）
    2. 属性注入
        1. 属性注入即通过 setter 方法注入Bean 的属性值或依赖的对象
        2. 属性注入使用 `<property>` 元素, 使用 name 属性指定 Bean 的属性名称，value 属性或 `<value>` 子节点指定属性值 
        3. 属性注入是实际应用中最常用的注入方式
            ```
            <!-- 配置 bean -->
            <bean id="myBean" class="com.onevgo.MyBean">
                <!-- 配置 bean 属性 -->
                <property name="username" value="onevgo"></property>
            </bean>
            ```
    3. 构造方法注入
        1. 通过构造方法注入Bean 的属性值或依赖的对象，它保证了 Bean 实例在实例化后就可以使用。
        2. 构造器注入在 `<constructor-arg>` 元素里声明属性, `<constructor-arg>` 中没有 name 属性
        3. 构造方法注入
            1. 按索引匹配入参：
                ```
                <!-- 配置 bean -->
                <bean id="myBean" class="com.onevgo.MyBean">
                    <!-- 构造器注入 按索引 -->
                    <constructor-arg value="onevgo" index="0"></constructor-arg>
                    <constructor-arg value="29" index="1"></constructor-arg>
                </bean>
                ```
            2. 按类型匹配入参：
                ```
                <!-- 配置 bean -->
                <bean id="myBean" class="com.onevgo.MyBean">
                    <!-- 构造器注入 按类型 -->
                    <constructor-arg value="onevgo" type="java.lang.String"></constructor-arg>
                    <constructor-arg value="29" type="java.lang.Integer"></constructor-arg>
                </bean>
                ```
    4. 字面值
        1. 字面值：可用字符串表示的值，可以通过 `<value>` 元素标签或 value 属性进行注入。
        2. 基本数据类型及其封装类、String 等类型都可以采取字面值注入的方式
        3. 若字面值中包含特殊字符，可以使用 `<![CDATA[]]>` 把字面值包裹起来。
    5. 引用其它 Bean
        1. 组成应用程序的 Bean 经常需要相互协作以完成应用程序的功能. 要使 Bean 能够相互访问, 就必须在 Bean 配置文件中指定对 Bean 的引用
        2. 在 Bean 的配置文件中, 可以通过 `<ref>` 元素或 ref  属性为 Bean 的属性或构造器参数指定对 Bean 的引用. 
        3. 也可以在属性或构造器里包含 Bean 的声明, 这样的 Bean 称为内部 Bean
            ```
            <bean id="userDao" class="com.onevgo.UserDaoImpl"></bean>
            <bean id="userService" class="com.onevgo.UserServiceImpl">
                <!-- 为 userDao 属性赋值，因为 userDao 属性是一个 bean 类型，可以使用 ref 执行 ioc 容器中的其他的 bean -->
                <property name="userDao" ref="userDao"></property>
            </bean>
            ```
    6. 内部 Bean
        1. 当 Bean 实例仅仅给一个特定的属性使用时, 可以将其声明为内部 Bean. 内部 Bean 声明直接包含在 `<property>` 或 `<constructor-arg>` 元素里, 不需要设置任何 id 或 name 属性
        2. 内部 Bean 不能使用在任何其他地方
    7. 注入参数详解：null 值和级联属性
        1. 可以使用专用的 `<null/>` 元素标签为 Bean 的字符串或其它对象类型的属性注入 null 值
        2. 和 Struts、Hibernate 等框架一样，Spring 支持级联属性的配置。
    8. 集合属性
        1. 在 Spring中可以通过一组内置的 xml 标签(例如: `<list>`, `<set>` 或 `<map>`) 来配置集合属性.
        2. 配置 java.util.List 类型的属性, 需要指定 `<list>`  标签, 在标签里包含一些元素. 这些标签可以通过 `<value>` 指定简单的常量值, 通过 `<ref>` 指定对其他 Bean 的引用. 通过`<bean>` 指定内置 Bean 定义. 通过 `<null/>` 指定空元素. 甚至可以内嵌其他集合.
        3. 数组的定义和 List 一样, 都使用 `<list>`
        4. 配置 java.util.Set 需要使用 `<set>` 标签, 定义元素的方法与 List 一样.
        5. Java.util.Map 通过 `<map>` 标签定义, `<map>` 标签里可以使用多个 `<entry>` 作为子标签. 每个条目包含一个键和一个值. 
        6. 必须在 `<key>` 标签里定义键
        7. 因为键和值的类型没有限制, 所以可以自由地为它们指定 `<value>`, `<ref>`, `<bean>` 或 `<null>` 元素. 
        8. 可以将 Map 的键和值作为 `<entry>` 的属性定义: 简单常量使用 key 和 value 来定义; Bean 引用通过 key-ref 和 value-ref 属性定义
        9. 使用 `<props>` 定义 java.util.Properties, 该标签使用多个 `<prop>` 作为子标签. 每个 `<prop>` 标签必须定义 key 属性. 
    9. 使用 utility scheme 定义集合
        1. 使用基本的集合标签定义集合时, 不能将集合作为独立的 Bean 定义, 导致其他 Bean 无法引用该集合, 所以无法在不同 Bean 之间共享集合.
        2. 可以使用 util schema 里的集合标签定义独立的集合 Bean. 需要注意的是, 必须在 `<beans>` 根元素里添加 util schema 定义
    10. 使用 p 命名空间
        1. 为了简化 XML 文件的配置，越来越多的 XML 文件采用属性而非子元素配置信息。
        2. Spring 从 2.5 版本开始引入了一个新的 p 命名空间，可以通过 `<bean>` 元素属性的方式配置 Bean 的属性。
        3. 使用 p 命名空间后，基于 XML 的配置方式将进一步简化
    11. XML 配置里的 Bean 自动装配
        1. Spring IOC 容器可以自动装配 Bean. 需要做的仅仅是在 `<bean>` 的 autowire 属性里指定自动装配的模式
        2. byType(根据类型自动装配): 若 IOC 容器中有多个与目标 Bean 类型一致的 Bean. 在这种情况下, Spring 将无法判定哪个 Bean 最合适该属性, 所以不能执行自动装配.
        3. byName(根据名称自动装配): 必须将目标 Bean 的名称和属性名设置的完全相同.
        4. constructor(通过构造器自动装配): 当 Bean 中存在多个构造器时, 此种自动装配方式将会很复杂. 不推荐使用
    12. XML 配置里的 Bean 自动装配的缺点
        1. 在 Bean 配置文件里设置 autowire 属性进行自动装配将会装配 Bean 的所有属性. 然而, 若只希望装配个别属性时, autowire 属性就不够灵活了. 
        2. autowire 属性要么根据类型自动装配, 要么根据名称自动装配, 不能两者兼而有之.
        3. 一般情况下，在实际的项目中很少使用自动装配功能，因为和自动装配功能所带来的好处比起来，明确清晰的配置文档更有说服力一些
7. 继承 Bean 配置
    1. Spring 允许继承 bean 的配置, 被继承的 bean 称为父 bean. 继承这个父 Bean 的 Bean 称为子 Bean
    2. 子 Bean 从父 Bean 中继承配置, 包括 Bean 的属性配置
    3. 子 Bean 也可以覆盖从父 Bean 继承过来的配置
    4. 父 Bean 可以作为配置模板, 也可以作为 Bean 实例. 若只想把父 Bean 作为模板, 可以设置 `<bean>` 的abstract 属性为 true, 这样 Spring 将不会实例化这个 Bean
    5. 并不是 `<bean>` 元素里的所有属性都会被继承. 比如: autowire, abstract 等.
    6. 也可以忽略父 Bean 的 class 属性, 让子 Bean 指定自己的类, 而共享相同的属性配置. 但此时 abstract 必须设为 true
8. 依赖 Bean 配置
    1. Spring 允许用户通过 depends-on 属性设定 Bean 前置依赖的Bean，前置依赖的 Bean 会在本 Bean 实例化之前创建好
    2. 如果前置依赖于多个 Bean，则可以通过逗号，空格或的方式配置 Bean 的名称
9. Bean 的作用域
    1. 在 Spring 中, 可以在 `<bean>` 元素的 scope 属性里设置 Bean 的作用域，已决定这个 Bean 是单实例的还是多实例的。
    
    2. 默认情况下，Spring 只为每个在 IOC 容器里声明的 Bean 创建唯一一个实例，整个 IOC 容器范围内都能共享该实例：所有后续的 getBean() 调用和 Bean 引用都将返回这个唯一的 Bean 实例。该作用域被称为 singleton，它是所有 Bean 的默认作用域。
    
        | 类别      | 说明                                                         |
        | --------- | ------------------------------------------------------------ |
        | singleton | 在 SpringIOC 容器中仅存在一个 Bean 实例，Bean 以单实例的方式存在 |
        | prototype | 每次调用 getBean() 时都会返回一个新的实例                    |
        | request   | 每次HTTP请求都会创建一个新的 Bean，该作用域仅适用于 WebApplicationContext 环境 |
        | session   | 同一个HTTP Session 共享一个 Bean，不同的Http Session 使用不同的 Bean。该作用域仅适用于 WebApplicationContext 环境 |
10. 使用外部属性文件
    1. 在配置文件里配置 Bean 时, 有时需要在 Bean 的配置里混入系统部署的细节信息(例如: 文件路径, 数据源配置信息等). 而这些部署细节实际上需要和 Bean 配置相分离
    2. Spring 提供了一个 PropertyPlaceholderConfigurer 的 BeanFactory 后置处理器, 这个处理器允许用户将 Bean 配置的部分内容外移到属性文件中. 可以在 Bean 配置文件里使用形式为 ${var} 的变量, PropertyPlaceholderConfigurer 从属性文件里加载属性, 并使用这些属性来替换变量.
    3. Spring 还允许在属性文件中使用 ${propName}，以实现属性之间的相互引用。
    4. 注册 PropertyPlaceholderConfigurer 
        1. Spring 2.0:
            ```
            <bean class="org.springframework.beans.factory.config.PropertyPlaceholderConfigurer">
                <property name="location" value="classpath:jdbc.properties"></property>
            </bean>
            ```
        2. Spring 2.5 之后: 可通过 `<context:property-placeholder>` 元素简化:
            1. `<beans>` 中添加 context Schema 定义
            2. 在配置文件中加入如下配置：
                ```
                <context:property-placeholder location="classpath:db.properties"/>
                ```
11. Spring表达式语言：SpEL
    1. Spring 表达式语言（简称SpEL）：是一个支持运行时查询和操作对象图的强大的表达式语言。
    2. 语法类似于 EL：SpEL 使用 #{…} 作为定界符，所有在大框号中的字符都将被认为是 SpEL
    3. SpEL 为 bean 的属性进行动态赋值提供了便利
    4. 通过 SpEL 可以实现：
        1. 通过 bean 的 id 对 bean 进行引用
        2. 调用方法以及引用对象中的属性
        3. 计算表达式的值
        4. 正则表达式的匹配
    5. SpEL：字面量
        1. 字面量的表示：
            1. 整数：`<property name="count" value="#{5}"/>`
            2. 小数：`<property name="frequency" value="#{89.7}"/>`
            3. 科学计数法：`<property name="capacity" value="#{1e4}"/>`
            4. String可以使用单引号或者双引号作为字符串的定界符号：`<property name=“name” value="#{'Chuck'}"/> 或 <property name='name' value='#{"Chuck"}'/>`
            5. Boolean：`<property name="enabled" value="#{false}"/>`
    6. SpEL：引用 Bean、属性和方法
        1. 引用其他对象：
            ```
            <!-- 通过 value 属性和 SpEL 配置 Bean 之间的应用关系 -->
            <property name="prefix" value="#{prefixGenerator}"></property>
            ```
        2. 引用其他对象的属性
            ```
            <!-- 通过 value 属性和 SpEL 配置 suffix 属性值为另一个 Bean 的 suffix 属性值 -->
            <property name="suffix" value="#{sequenceGenerator2.suffix}"></property>
            ```
        3. 调用其他方法，还可以链式操作
            ```
            <!-- 通过 value 属性和 SpEL 配置 suffix 属性值为另一个 Bean 的方法的返回值 -->
            <property name="suffix" value="#{sequenceGenerator2.toString()}"></property>
            <!-- 方法的连缀 -->
            <property name="suffix" value="#{sequenceGenerator2.toString().toUpperCase()}"></property>
            ```
        4. 调用静态方法或静态属性：通过 T() 调用一个类的静态方法，它将返回一个 Class Object，然后再调用相应的方法或属性： 
            ```
            <property name="initValue" value="#{T(java.lang.Math).PI}"></property>
            ```
    7. SpEL支持的运算符号
        1. 算数运算符：+, -, *, /, %, ^：
            ```
            <property name="adjustedAmount" value="#{counter.total + 42}"></property>
            <property name="adjustedAmount" value="#{counter.total - 20}"></property>
            <property name="circumference" value="#{2 * T(java.lang.Math).PI * circle.radius}"></property>
            <property name="average" value="#{counter.total / counter.count}"></property>
            <property name="remainder" value="#{counter.total % counter.count}"></property>
            <property name="area" value="#{T(java.lang.Math).PI * circle.radius ^ 2}"></property>
            ```
        2. 加号还可以用作字符串连接：
            ```
            <constructor-arg value="#{performer.firstName + ' ' + performer.lastName}"></constructor-arg>
            ```
        3. 比较运算符： <, >, ==, <=, >=, lt, gt, eq, le, ge
            ```
            <property name="equal" value="#{counter.total == 100}"></property>
            <property name="hasCapacity" value="#{counter.total le 100000}"></property>
            ```
        4. 逻辑运算符号： and, or, not, |
            ```
            <property name="largeCircle" value="#{shape.kind == 'circle' and shape.perimeter gt 10000}"></property>
            <property name="outOfStock" value="#{!product.available}"></property>
            <property name="outOfStock" value="#{not product.available}"></property>
            ```
        5. if-else 运算符：?: (ternary), ?: (Elvis)
            ```
            <constructor-arg value="#{songSelector.selectSong == 'Jingle Bells' ? 'piano' : ' Jingle Bells '}"></constructor-arg>
            ```
        6. if-else 的变体
            ```
            <constructor-arg value="#{kenny.song ?: 'Greensleeves'}"></constructor-arg>
            ```
        7. 正则表达式：matches
            ```
            <constructor-arg value="#{admin.email matches '[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z{2,4}]'}"></constructor-arg>
            ```
12. IOC 容器中 Bean 的生命周期方法
    1. Spring IOC 容器可以管理 Bean 的生命周期, Spring 允许在 Bean 生命周期的特定点执行定制的任务. 
    2. Spring IOC 容器对 Bean 的生命周期进行管理的过程:
        1. 通过构造器或工厂方法创建 Bean 实例
        2. 为 Bean 的属性设置值和对其他 Bean 的引用
        3. 调用 Bean 的初始化方法
        4. Bean 可以使用了
        5. 当容器关闭时, 调用 Bean 的销毁方法
    3. 在 Bean 的声明里设置 init-method 和 destroy-method 属性, 为 Bean 指定初始化和销毁方法.
    4. 创建 Bean 后置处理器
        1. Bean 后置处理器允许在调用初始化方法前后对 Bean 进行额外的处理.
        2. Bean 后置处理器对 IOC 容器里的所有 Bean 实例逐一处理, 而非单一实例. 其典型应用是: 检查 Bean 属性的正确性或根据特定的标准更改 Bean 的属性.
        3. 对Bean 后置处理器而言, 需要实现 org.springframework.beans.factory.config.BeanPostProcessor 接口. 在初始化方法被调用前后, Spring 将把每个 Bean 实例分别传递给上述接口的以下两个方法:
            1. public Object postProcessBeforeInitialization(Object bean, String beanName)
            2. public Object postProcessAfterInitialization(Object bean, String beanName)
    5. 添加 Bean 后置处理器后 Bean 的生命周期
        1. Spring IOC 容器对 Bean 的生命周期进行管理的过程:
            1. 通过构造器或工厂方法创建 Bean 实例
            2. 为 Bean 的属性设置值和对其他 Bean 的引用
            3. 将 Bean 实例传递给 Bean 后置处理器的 postProcessBeforeInitialization 方法
            4. 调用 Bean 的初始化方法
            5. 将 Bean 实例传递给 Bean 后置处理器的 postProcessAfterInitialization方法
            6. Bean 可以使用了
            7. 当容器关闭时, 调用 Bean 的销毁方法
13. 通过调用静态工厂方法创建 Bean
    1. 调用静态工厂方法创建 Bean是将对象创建的过程封装到静态方法中. 当客户端需要对象时, 只需要简单地调用静态方法, 而不同关心创建对象的细节.
    2. 要声明通过静态方法创建的 Bean, 需要在 Bean 的 class 属性里指定拥有该工厂的方法的类, 同时在 factory-method 属性里指定工厂方法的名称. 最后, 使用 `<constructor-arg>` 元素为该方法传递方法参数.
14. 通过调用实例工厂方法创建 Bean
    1. 实例工厂方法: 将对象的创建过程封装到另外一个对象实例的方法里. 当客户端需要请求对象时, 只需要简单的调用该实例方法而不需要关心对象的创建细节.
    2. 要声明通过实例工厂方法创建的 Bean
        1. 在 bean 的 factory-bean 属性里指定拥有该工厂方法的 Bean
        2. 在 factory-method 属性里指定该工厂方法的名称
        3. 使用 constructor-arg 元素为工厂方法传递方法参数
15. 实现 FactoryBean 接口在 Spring IOC 容器中配置 Bean
    1. Spring 中有两种类型的 Bean, 一种是普通Bean, 另一种是工厂Bean, 即FactoryBean. 
    2. 工厂 Bean 跟普通Bean不同, 其返回的对象不是指定类的一个实例, 其返回的是该工厂 Bean 的 getObject 方法所返回的对象 
16. 在 classpath 中扫描组件
    1. 组件扫描(component scanning):  Spring 能够从 classpath 下自动扫描, 侦测和实例化具有特定注解的组件. 
    2. 特定组件包括:
        1. @Component: 基本注解, 标识了一个受 Spring 管理的组件
        2. @Repository: 标识持久层组件
        3. @Service: 标识服务层(业务层)组件
        4. @Controller: 标识表现层组件
    3. 对于扫描到的组件, Spring 有默认的命名策略: 使用非限定类名, 第一个字母小写. 也可以在注解中通过 value 属性值标识组件的名称
    4. 当在组件类上使用了特定的注解之后, 还需要在 Spring 的配置文件中声明 `<context:component-scan>` ：
        1. base-package 属性指定一个需要扫描的基类包，Spring 容器将会扫描这个基类包里及其子包中的所有类. 
        
        2. 当需要扫描多个包时, 可以使用逗号分隔.
        
        3. 如果仅希望扫描特定的类而非基包下的所有类，可使用 resource-pattern 属性过滤特定的类，示例：
            ```
            <!-- 配置 spring context 自动扫描 -->
            <context:component-scan base-package="com.onevgo.spring.beans" resource-pattern="autowire/*.class" />
            ```
            
        4. `<context:include-filter>` 子节点表示要包含的目标类
        
        5. `<context:exclude-filter>` 子节点表示要排除在外的目标类
        
        6. `<context:component-scan>` 下可以拥有若干个 `<context:include-filter>` 和 `<context:exclude-filter>` 子节点
        
        7. `<context:include-filter>` 和 `<context:exclude-filter>` 子节点支持多种类型的过滤表达式：
          
            | 类别       | 示例                    | 说明                                                         |
            | ---------- | ----------------------- | ------------------------------------------------------------ |
            | annotation | com.onevgo.MyAnnotation | 所有标注了 @MyAnnotation 的类。该类型采用目标类是否标注某个注解进行过滤 |
            | assignable | com.onevgo.MyService    | 所有继承或实现 MyService 的类。该类型采用目标类是否继承某个特定类或实现某个特定接口进行过滤 |
            | aspectj    | com.onevgo..*Service+   | 所有类名以 Service 结束的类及继承或扩展它们的类。该类型采用 Aspectj 表达式进行过滤 |
            | regex      | `com\.onevgo\.anno\..*` | 所有 com.onevgo.anno 包下的类。该类型采用正则表达式根据类的类名进行过滤 |
            | custom     | com.onevgo.MyTypeFilter | 采用 MyTypeFilter 通过代码的方式定义过滤规则。该类必须实现 org.springframework.core.type.TypeFitler 接口 |
17. 组件装配
    1. `<context:component-scan>` 元素还会自动注册 AutowiredAnnotationBeanPostProcessor 实例, 该实例可以自动装配具有 @Autowired 和 @Resource 、@Inject注解的属性.
    2. 使用 @Autowired 自动装配 Bean
        1. @Autowired 注解自动装配具有兼容类型的单个 Bean属性
            1. 构造器, 普通字段(即使是非 public), 一切具有参数的方法都可以应用@Autowired 注解
            2. 默认情况下, 所有使用 @Autowired 注解的属性都需要被设置. 当 Spring 找不到匹配的 Bean 装配属性时, 会抛出异常, 若某一属性允许不被设置, 可以设置 @Autowired 注解的 required 属性为 false
            3. 默认情况下, 当 IOC 容器里存在多个类型兼容的 Bean 时, 通过类型的自动装配将无法工作. 此时可以在 @Qualifier 注解里提供 Bean 的名称. Spring 允许对方法的入参标注 @Qualifier 已指定注入 Bean 的名称
            4. @Autowired 注解也可以应用在数组类型的属性上, 此时 Spring 将会把所有匹配的 Bean 进行自动装配.
            5. @Autowired 注解也可以应用在集合属性上, 此时 Spring 读取该集合的类型信息, 然后自动装配所有与之兼容的 Bean. 
            6. @Autowired 注解用在 java.util.Map 上时, 若该 Map 的键值为 String, 那么 Spring 将自动装配与之 Map 值类型兼容的 Bean, 此时 Bean 的名称作为键值
    3. 使用 @Resource 或 @Inject  自动装配 Bean
        1. Spring 还支持 @Resource 和 @Inject 注解，这两个注解和 @Autowired 注解的功用类似
        2. @Resource 注解要求提供一个 Bean 名称的属性，若该属性为空，则自动采用标注处的变量或方法名作为 Bean 的名称
        3. @Inject 和 @Autowired 注解一样也是按类型匹配注入的 Bean， 但没有 required 属性
        4. 建议使用 @Autowired 注解
18. 泛型依赖注入
  
    1. Spring 4.x 中可以为子类注入子类对应的泛型类型的成员变量的引用
19. 整合多个配置文件
    1. Spring 允许通过 `<import>` 将多个配置文件引入到一个文件中，进行配置文件的集成。这样在启动 Spring 容器时，仅需要指定这个合并好的配置文件就可以。
    
    2. import 元素的 resource 属性支持 Spring 的标准的路径资源
      
        | 地址前缀   | 示例                                     | 对应资源类型                                           |
        | ---------- | ---------------------------------------- | ------------------------------------------------------ |
        | classpath: | classpath:spring-mvc.xml                 | 从类路径下加载资源，classpath: 和 classpath:/ 是等价的 |
        | file:      | file:/conf/security/spring-shiro.xml     | 从文件系统目录中加载资源，可采用绝对或相对路径         |
        | http://    | http://www.onevgo.com/resource/beans.xml | 从 WEB 服务器中加载资源                                |
        | ftp://     | ftp://www.onevgo.com/resource/beans.xml  | 从 FTP 服务器中加载资源                                |

## Spring AOP

1. AOP 简介
    1. AOP(Aspect-Oriented Programming, 面向切面编程): 是一种新的方法论, 是对传统 OOP(Object-Oriented Programming, 面向对象编程) 的补充.
    2. AOP 的主要编程对象是切面(aspect), 而切面模块化横切关注点.
    3. 在应用 AOP 编程时, 仍然需要定义公共功能, 但可以明确的定义这个功能在哪里, 以什么方式应用, 并且不必修改受影响的类. 这样一来横切关注点就被模块化到特殊的对象(切面)里.
    4. AOP 的好处:
        1. 每个事物逻辑位于一个位置, 代码不分散, 便于维护和升级
        2. 业务模块更简洁, 只包含核心业务代码.
2. AOP 术语
    1. 切面(Aspect):  横切关注点(跨越应用程序多个模块的功能)被模块化的特殊对象
    2. 通知(Advice):  切面必须要完成的工作
    3. 目标(Target): 被通知的对象
    4. 代理(Proxy): 向目标对象应用通知之后创建的对象
    5. 连接点（JoinPoint）：程序执行的某个特定位置：如类某个方法调用前、调用后、方法抛出异常后等。连接点由两个信息确定：方法表示的程序执行点；相对点表示的方位。例如 ArithmeticCalculator#add() 方法执行前的连接点，执行点为 ArithmeticCalculator#add()； 方位为该方法执行前的位置
    6. 切点（pointcut）：每个类都拥有多个连接点：例如 ArithmeticCalculator 的所有方法实际上都是连接点，即连接点是程序类中客观存在的事务。AOP 通过切点定位到特定的连接点。类比：连接点相当于数据库中的记录，切点相当于查询条件。切点和连接点不是一对一的关系，一个切点匹配多个连接点，切点通过 org.springframework.aop.Pointcut 接口进行描述，它使用类和方法作为连接点的查询条件。
3. Spring  AOP
    1. AspectJ：Java 社区里最完整最流行的 AOP 框架.
    2. 在 Spring2.0 以上版本中, 可以使用基于 AspectJ 注解或基于 XML 配置的 AOP
4. 在 Spring 中启用 AspectJ 注解支持
    1. 要在 Spring 应用中使用 AspectJ 注解, 必须在 classpath 下包含 AspectJ 类库: aopalliance.jar、aspectj.weaver.jar 和 spring-aspects.jar
    2. 将 aop Schema 添加到 `<beans>` 根元素中.
    3. 要在 Spring IOC 容器中启用 AspectJ 注解支持, 只要在 Bean 配置文件中定义一个空的 XML 元素 `<aop:aspectj-autoproxy>`
    4. 当 Spring IOC 容器侦测到 Bean 配置文件中的 `<aop:aspectj-autoproxy>` 元素时, 会自动为与 AspectJ 切面匹配的 Bean 创建代理.
5. 用 AspectJ 注解声明切面
    1. 要在 Spring 中声明 AspectJ 切面, 只需要在 IOC 容器中将切面声明为 Bean 实例. 当在 Spring IOC 容器中初始化 AspectJ 切面之后, Spring IOC 容器就会为那些与 AspectJ 切面相匹配的 Bean 创建代理.
    2. 在 AspectJ 注解中, 切面只是一个带有 @Aspect 注解的 Java 类. 
    3. 通知是标注有某种注解的简单的 Java 方法.
    4. AspectJ 支持 5 种类型的通知注解: 
        1. @Before: 前置通知, 在方法执行之前执行
        2. @After: 后置通知, 在方法执行之后执行 
        3. @AfterRunning: 返回通知, 在方法返回结果之后执行
        4. @AfterThrowing: 异常通知, 在方法抛出异常之后
        5. @Around: 环绕通知, 围绕着方法执行
6. 前置通知: @Before
    1. 前置通知:在方法执行之前执行的通知
    2. 前置通知使用 @Before 注解, 并将切入点表达式的值作为注解值.
7. 后置通知: @After
    1. 后置通知是在连接点完成之后执行的, 即连接点返回结果或者抛出异常的时候, 下面的后置通知记录了方法的终止. 
    2. 一个切面可以包括一个或者多个通知.
8. 返回通知: @AfterReturn
    1. 无论连接点是正常返回还是抛出异常, 后置通知都会执行. 如果只想在连接点返回的时候记录日志, 应使用返回通知代替后置通知.
    2. 在返回通知中访问连接点的返回值
        1. 在返回通知中, 只要将 returning 属性添加到 @AfterReturning 注解中, 就可以访问连接点的返回值. 该属性的值即为用来传入返回值的参数名称. 
        2. 必须在通知方法的签名中添加一个同名参数. 在运行时, Spring AOP 会通过这个参数传递返回值.
        3. 原始的切点表达式需要出现在 pointcut 属性中
9. 异常通知: @AfterThrowing
    1. 只在连接点抛出异常时才执行异常通知
    2. 将 throwing 属性添加到 @AfterThrowing 注解中, 也可以访问连接点抛出的异常. Throwable 是所有错误和异常类的超类. 所以在异常通知方法可以捕获到任何错误和异常.
    3. 如果只对某种特殊的异常类型感兴趣, 可以将参数声明为其他异常的参数类型. 然后通知就只在抛出这个类型及其子类的异常时才被执行.
10. 环绕通知: @Around
    1. 环绕通知是所有通知类型中功能最为强大的, 能够全面地控制连接点. 甚至可以控制是否执行连接点.
    2. 对于环绕通知来说, 连接点的参数类型必须是 ProceedingJoinPoint . 它是 JoinPoint 的子接口, 允许控制何时执行, 是否执行连接点.
    3. 在环绕通知中需要明确调用 ProceedingJoinPoint 的 proceed() 方法来执行被代理的方法. 如果忘记这样做就会导致通知被执行了, 但目标方法没有被执行.
    4. 注意: 环绕通知的方法需要返回目标方法执行之后的结果, 即调用 joinPoint.proceed(); 的返回值, 否则会出现空指针异常
11. AspectJ 切入点表达式
    1. 利用方法签名编写 AspectJ 切入点表达式
        1. 最典型的切入点表达式时根据方法的签名来匹配各种方法:
            1. execution * com.onevgo.spring.ArithmeticCalculator.*(..): 匹配 ArithmeticCalculator 中声明的所有方法,第一个 * 代表任意修饰符及任意返回值. 第二个 * 代表任意方法. .. 匹配任意数量的参数. 若目标类与接口与该切面在同一个包中, 可以省略包名.
            2. execution public * ArithmeticCalculator.*(..): 匹配 ArithmeticCalculator 接口的所有公有方法.
            3. execution public double ArithmeticCalculator.*(..): 匹配 ArithmeticCalculator 中返回 double 类型数值的方法
            4. execution public double ArithmeticCalculator.*(double, ..): 匹配第一个参数为 double 类型的方法, .. 匹配任意数量任意类型的参数
            5. execution public double ArithmeticCalculator.*(double, double): 匹配参数类型为 double, double 类型的方法.
    2. 合并切入点表达式
        1. 在 AspectJ 中, 切入点表达式可以通过操作符 &&, ||, ! 结合起来. 
    3. 让通知访问当前连接点的细节
        1. 可以在通知方法中声明一个类型为 JoinPoint 的参数. 然后就能访问链接细节. 如方法名称和参数值. 
12. 指定切面的优先级
    1. 在同一个连接点上应用不止一个切面时, 除非明确指定, 否则它们的优先级是不确定的.
    2. 切面的优先级可以通过实现 Ordered 接口或利用 @Order 注解指定.
    3. 实现 Ordered 接口, getOrder() 方法的返回值越小, 优先级越高.
    4. 若使用 @Order 注解, 序号出现在注解中
13. 重用切入点定义
    1. 在编写 AspectJ 切面时, 可以直接在通知注解中书写切入点表达式. 但同一个切点表达式可能会在多个通知中重复出现.
    2. 在 AspectJ 切面中, 可以通过 @Pointcut 注解将一个切入点声明成简单的方法. 切入点的方法体通常是空的, 因为将切入点定义与应用程序逻辑混在一起是不合理的. 
    3. 切入点方法的访问控制符同时也控制着这个切入点的可见性. 如果切入点要在多个切面中共用, 最好将它们集中在一个公共的类中. 在这种情况下, 它们必须被声明为 public. 在引入这个切入点时, 必须将类名也包括在内. 如果类没有与这个切面放在同一个包中, 还必须包含包名.
    4. 其他通知可以通过方法名称引入该切入点.
14. 引入通知
    1. 引入通知是一种特殊的通知类型. 它通过为接口提供实现类, 允许对象动态地实现接口, 就像对象已经在运行时扩展了实现类一样.
    2. 引入通知可以使用两个实现类 MaxCalculatorImpl 和 MinCalculatorImpl, 让 ArithmeticCalculatorImpl 动态地实现 MaxCalculator 和 MinCalculator 接口. 而这与从 MaxCalculatorImpl 和 MinCalculatorImpl 中实现多继承的效果相同. 但却不需要修改 ArithmeticCalculatorImpl 的源代码
    3. 引入通知也必须在切面中声明
    4. 在切面中, 通过为任意字段添加@DeclareParents 注解来引入声明. 
    5. 注解类型的 value 属性表示哪些类是当前引入通知的目标. value 属性值也可以是一个 AspectJ 类型的表达式, 以将一个即可引入到多个类中.  defaultImpl 属性中指定这个接口使用的实现类
15. 用基于 XML 的配置声明切面
    1. 除了使用 AspectJ 注解声明切面, Spring 也支持在 Bean 配置文件中声明切面. 这种声明是通过 aop schema 中的 XML 元素完成的.
    2. 正常情况下, 基于注解的声明要优先于基于 XML 的声明. 通过 AspectJ 注解, 切面可以与 AspectJ 兼容, 而基于 XML 的配置则是 Spring 专有的. 由于 AspectJ 得到越来越多的 AOP 框架支持, 所以以注解风格编写的切面将会有更多重用的机会.
    3. 基于 XML ---- 声明切面
        1. 当使用 XML 声明切面时, 需要在 `<beans>` 根元素中导入 aop Schema
        2. 在 Bean 配置文件中, 所有的 Spring AOP 配置都必须定义在 `<aop:config>` 元素内部. 对于每个切面而言, 都要创建一个 `<aop:aspect>` 元素来为具体的切面实现引用后端 Bean 实例. 
        3. 切面 Bean 必须有一个标示符, 供 `<aop:aspect>` 元素引用
    4. 基于 XML ---- 声明切入点
        1. 切入点使用 `<aop:pointcut>` 元素声明
        2. 切入点必须定义在 `<aop:aspect>` 元素下, 或者直接定义在 `<aop:config>` 元素下.
        3. 定义在 `<aop:aspect>` 元素下: 只对当前切面有效
        4. 定义在 `<aop:config>` 元素下: 对所有切面都有效
        5. 基于 XML 的 AOP 配置不允许在切入点表达式中用名称引用其他切入点. 
    5. 基于 XML ---- 声明通知
        1. 在 aop Schema 中, 每种通知类型都对应一个特定的 XML 元素. 
        2. 通知元素需要使用 `<pointcut-ref>` 来引用切入点, 或用 `<pointcut>` 直接嵌入切入点表达式.  method 属性指定切面类中通知方法的名称.
    6. 声明引入
      
        1. 可以利用 `<aop:declare-parents>` 元素在切面内部声明引入
    7. 示例代码：
        ```
        <!-- 配置切面 bean -->
        <bean id="calculatorLoggingAspect" class="com.onevgo.beans.CalculatorLoggingAspect"></bean>
        <bean id="calculatorValidatorAspect" class="com.onevgo.beans.CalculatorValidatorAspect"></bean>
        
        <!-- 配置 aop -->
        <aop:config>
            <!-- 配置切点 -->
            <aop:pointcut id="testOperation" expression="execution(* com.onevgo.beans.Arithmetic*.*(..))"></aop:pointcut>
            
            <!-- 配置切面 -->
            <aop:aspect ref="calculatorLoggingAspect">
                <!-- 配置增强/通知 -->
                <aop:after method="logBefore" pointcut-ref="testOperation"></aop:after>
                
                <!-- 配置声明 -->
                <aop:declare-parents
                    types-matching="com.onevgo.beans.Arithmetic*"
                    implement-inteface="com.onevgo.beans.MinCalculator"
                    default-impl="com.onevgo.beans.MinCalculatorImpl"/>
            </aop:aspect>
            
            <!-- 配置切面 -->
            <aop:aspect ref="calculatorValidatorAspect">
                <!-- 配置增强/通知 -->
                <aop:before method="validateBefore" pointcut-ref="testOperation"></aop:before>
            </aop:aspect>
        </aop:config>
        ```

## Spring 对 JDBC 的支持

1. 使用 JdbcTemplate 更新数据库
    1. 用 sql 语句和参数更新数据库: `public int update(String sql, Object... args)`
    2. 批量更新数据库: `public int[] batchUpdate(String sql, List<Object[]> batchArgs)`
2. 使用 JdbcTemplate 查询数据库
    1. 查询单行: `public <T> T queryForObject(String sql, ParameterizedRowMapper<T> rm, Object... args)`
    2. 便利的 BeanPropertyRowMapper 实现: `org.springframework.jdbc.core.BeanPropertyRowMapper`
    3. 查询多行: `public <T> List<T> query(String sql, ParameterizedRowMapper<T> rm, Object... args)`
    4. 单值查询: `public <T> T queryForObject(String sql, Class<T> requiredType, Object... args)`
3. 简化 JDBC 模板查询
    1. 每次使用都创建一个 JdbcTemplate 的新实例, 这种做法效率很低下.
    2. JdbcTemplate 类被设计成为线程安全的, 所以可以再 IOC 容器中声明它的单个实例, 并将这个实例注入到所有的 DAO 实例中.
    3. JdbcTemplate 也利用了 Java 1.5 的特定(自动装箱, 泛型, 可变长度等)来简化开发
    4. Spring JDBC 框架还提供了一个 JdbcDaoSupport 类来简化 DAO 实现. 该类声明了 jdbcTemplate 属性, 它可以从 IOC 容器中注入, 或者自动从数据源中创建.
4. 在 JDBC 模板中使用具名参数
    1. 在经典的 JDBC 用法中, SQL 参数是用占位符 ? 表示,并且受到位置的限制. 定位参数的问题在于, 一旦参数的顺序发生变化, 就必须改变参数绑定. 
    2. 在 Spring JDBC 框架中, 绑定 SQL 参数的另一种选择是使用具名参数(named parameter). 
    3. 具名参数: SQL 按名称(以冒号开头)而不是按位置进行指定. 具名参数更易于维护, 也提升了可读性. 具名参数由框架类在运行时用占位符取代
    4. 具名参数只在 NamedParameterJdbcTemplate 中得到支持 
    5. 在 SQL 语句中使用具名参数时, 可以在一个 Map 中提供参数值, 参数名为键
    6. 也可以使用 SqlParameterSource 参数
    7. 批量更新时可以提供 Map 或 SqlParameterSource 的数组
        1. public int update(String sql, Map args) throws DataAccessException
        2. public int update(String sql, SqlParameterSource args) throws DataAccessException
        3. public int[] batchUpdate(String sql, Map[] batchValues)
        4. public int[] batchUpdate(String sql, SqlParameterSource[] batchArgs)

## Spring中的事务管理

1. 事务简介
    1. 事务管理是企业级应用程序开发中必不可少的技术,  用来确保数据的完整性和一致性. 
    2. 事务就是一系列的动作, 它们被当做一个单独的工作单元. 这些动作要么全部完成, 要么全部不起作用
    3. 事务的四个关键属性(ACID)
        1. 原子性(atomicity): 事务是一个原子操作, 由一系列动作组成. 事务的原子性确保动作要么全部完成要么完全不起作用.
        2. 一致性(consistency): 一旦所有事务动作完成, 事务就被提交. 数据和资源就处于一种满足业务规则的一致性状态中.
        3. 隔离性(isolation): 可能有许多事务会同时处理相同的数据, 因此每个事物都应该与其他事务隔离开来, 防止数据损坏.
        4. 持久性(durability): 一旦事务完成, 无论发生什么系统错误, 它的结果都不应该受到影响. 通常情况下, 事务的结果被写到持久化存储器中.
    
2. Spring 中的事务管理
    1. 作为企业级应用程序框架, Spring 在不同的事务管理 API 之上定义了一个抽象层. 而应用程序开发人员不必了解底层的事务管理 API, 就可以使用 Spring 的事务管理机制.
    2. Spring 既支持编程式事务管理, 也支持声明式的事务管理. 
    3. 编程式事务管理: 将事务管理代码嵌入到业务方法中来控制事务的提交和回滚. 在编程式管理事务时, 必须在每个事务操作中包含额外的事务管理代码. 
    4. 声明式事务管理: 大多数情况下比编程式事务管理更好用. 它将事务管理代码从业务方法中分离出来, 以声明的方式来实现事务管理. 事务管理作为一种横切关注点, 可以通过 AOP 方法模块化. Spring 通过 Spring AOP 框架支持声明式事务管理.
    
3. Spring 中的事务管理器
    1. Spring 从不同的事务管理 API 中抽象了一整套的事务机制. 开发人员不必了解底层的事务 API, 就可以利用这些事务机制. 有了这些事务机制, 事务管理代码就能独立于特定的事务技术了.
    2. Spring 的核心事务管理抽象是 org.springframework.transaction.PlatformTransactionManager 它为事务管理封装了一组独立于技术的方法. 无论使用 Spring 的哪种事务管理策略(编程式或声明式), 事务管理器都是必须的.
    
4. Spring 中的事务管理器的不同实现
    1. org.springframework.jdbc.datasource.DataSourceTransactionManager:在应用程序中只需要处理一个数据源, 而且通过 JDBC 存取
    2. org.springframework.transaction.jta.JtaTransactionManager: 在 JavaEE 应用服务器上用 JTA(Java Transaction API) 进行事务管理
    3. org.springframework.hibernate3.HibernateTransactionManager：用 Hibernate 框架存取数据库
    4. ……
    5. 事务管理器以普通的 Bean 形式声明在 Spring IOC 容器中
    
5. 用事务通知声明式地管理事务
    1. 事务管理是一种横切关注点
    2. 为了在 Spring 2.x 中启用声明式事务管理, 可以通过 tx Schema 中定义的 `<tx:advice>` 元素声明事务通知, 为此必须事先将这个 Schema 定义添加到 `<beans>` 根元素中去.
    3. 声明了事务通知后, 就需要将它与切入点关联起来. 由于事务通知是在 `<aop:config>` 元素外部声明的, 所以它无法直接与切入点产生关联. 所以必须在 `<aop:config>` 元素中声明一个增强器通知与切入点关联起来.
    4. 由于 Spring AOP 是基于代理的方法, 所以只能增强公共方法. 因此, 只有公有方法才能通过 Spring AOP 进行事务管理.
        ```
        <!-- 配置bean -->
        <bean id="bookShopService" class="com.onevgo.transaction.BookShopServcieImpl">
            <property name="bookShopDAO" ref="bookDAO"></property>
        </bean>
        
        <!-- 配置事务管理器 -->
        <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
            <property name="dataSource" ref="dataSource"></property>
        </bean>
        
        <!-- 配置事务增强/通知 -->
        <tx:advice id="bookShopTxAdvice" transaction-manager="transactionManager">
            <tx:attributes>
                <!-- 配置事务的传播，隔离级别，异常回滚/不回滚，超时，只读 -->
                <tx:method name="purchase"
                    propagation="REQUIRES_NEW"
                    isolation="READ_COMMITTED"
                    rollback-for="java.io.IOException,java.sql.SQLException"
                    no-rollback-for="java.lang.ArithmeticException"
                    timeout="30"
                    read-only="true"/>
                <tx:method name="get*" read-only="true"/>
                <tx:method name="find*" read-only="true"/>
                <tx:method name="*"/>
            </tx:attributes>
        </tx:advice>
        
        <!-- 配置 aop -->
        <aop:config>
            <!-- 配置切入点 -->
            <aop:pointcut id="bookShopOperation" expression="execution(* *.BookShopService.*(..))"></aop:pointcut>
            
            <!-- 配置增强/通知 -->
            <aop:advisor advice-ref="bookShopTxAdvice" pointcut-ref="bookShopOperation"></aop:advisor>
        </aop:config>
        ```
    
6. 用 @Transactional 注解声明式地管理事务
    1. 除了在带有切入点, 通知和增强器的 Bean 配置文件中声明事务外, Spring 还允许简单地用 @Transactional 注解来标注事务方法. 
    2. 为了将方法定义为支持事务处理的, 可以为方法添加 @Transactional 注解. 根据 Spring AOP 基于代理机制, 只能标注公有方法.
    3. 可以在方法或者类级别上添加 @Transactional 注解. 当把这个注解应用到类上时, 这个类中的所有公共方法都会被定义成支持事务处理的. 
    4. 在 Bean 配置文件中只需要启用 `<tx:annotation-driven>` 元素, 并为之指定事务管理器就可以了. 
    5. 如果事务处理器的名称是 transactionManager, 就可以在`<tx:annotation-driven>` 元素中省略 transaction-manager 属性. 这个元素会自动检测该名称的事务处理器.
        ```
        <!-- 配置事务管理器 -->
        <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
            <property name="dataSource" ref="dataSource"></property>
        </bean>
        
        <!-- 配置事务注解驱动 -->
        <tx:annotation-driven/>
        ```
    
7. 事务传播属性
    1. 当事务方法被另一个事务方法调用时， 必须指定事务应该如何传播。例如: 方法可能继续在现有事务中运行, 也可能开启一个新事务，并在自己的事务中运行。
    2. 事务的传播行为可以由传播属性指定。Spring 定义了 7  种类传播行为。
    
8. Spring 支持的事务传播行为
  
    | 传播属性      | 描述                                                         |
    | ------------- | ------------------------------------------------------------ |
    | REQUIRED      | 如果有事务在运行，当前的方法就在这个事务内运行，否则，就启动一个新的事务，并在自己的事务内运行。 |
    | REQUIRES_NEW  | 当前的方法必须启动新事务，并在它自己的事务内运行。如果有事务正在运行，应该将它挂起。 |
    | SUPPORTS      | 如果有事务在运行，当前的方法就在这个事务内运行，否则它可以不运行在事务中。 |
    | NOT_SUPPORTED | 当前的方法不应该运行在事务中。如果有运行的事务，将它挂起。   |
    | MANDATORY     | 当前的方法必须运行在事务内部，如果没有正在运行的事务，就抛出异常。 |
    | NEVER         | 当前的方法不应该运行在事务中。如果有运行的事务，就抛出异常。 |
    | NESTED        | 如果有事务在运行，当前的方法就应该在这个事务的嵌套事务内运行。否则，就启用一个新的事务，并在它自己的事务内运行。 |
    
    1. REQUIRED 传播行为
        1. 当 bookService 的 purchase() 方法被另一个事务方法 checkout() 调用时, 它默认会在现有的事务内运行. 这个默认的传播行为就是 REQUIRED. 因此在 checkout() 方法的开始和终止边界内只有一个事务. 这个事务只在 checkout() 方法结束的时候被提交, 结果用户一本书都买不了
        2. 事务传播属性可以在 @Transactional 注解的 propagation 属性中定义
    2. REQUIRES_NEW 传播行为
        1. 另一种常见的传播行为是 REQUIRES_NEW. 它表示该方法必须启动一个新事务, 并在自己的事务内运行. 如果有事务在运行, 就应该先挂起它.
    
9. 并发事务所导致的问题
    1. 当同一个应用程序或者不同应用程序中的多个事务在同一个数据集上并发执行时, 可能会出现许多意外的问题
    2. 并发事务所导致的问题可以分为下面三种类型:
        1. 脏读: 对于两个事物 T1, T2, T1  读取了已经被 T2 更新但 还没有被提交的字段. 之后, 若 T2 回滚, T1读取的内容就是临时且无效的.
        2. 不可重复读:对于两个事物 T1, T2, T1  读取了一个字段, 然后 T2 更新了该字段. 之后, T1再次读取同一个字段, 值就不同了.
        3. 幻读:对于两个事物 T1, T2, T1  从一个表中读取了一个字段, 然后 T2 在该表中插入了一些新的行. 之后, 如果 T1 再次读取同一个表, 就会多出几行.

10. 事务的隔离级别
   1. 从理论上来说, 事务应该彼此完全隔离, 以避免并发事务所导致的问题. 然而, 那样会对性能产生极大的影响, 因为事务必须按顺序运行. 
   2. 在实际开发中, 为了提升性能, 事务会以较低的隔离级别运行.
   3. 事务的隔离级别可以通过隔离事务属性指定

11. Spring 支持的事务隔离级别

    | 隔离级别         | 描述                                                         |
    | ---------------- | ------------------------------------------------------------ |
    | DEFAULT          | 使用底层数据库的默认隔离级别。对于大多数数据库来说，默认隔离级别都是READ_COMMITTED |
    | READ_UNCOMMITTED | 允许事务读取未被其他事务提交的变更。脏读，不可重复读和幻读的问题都会出现 |
    | READ_COMMITTED   | 只允许事务读取已经被其他事务提交的变更。可以避免脏读，但不可重复读和幻读问题仍然可能出现 |
    | REPEATABLE_READ  | 确保事务可以多次从一个字段中读取相同的值。在这个事务持续期间，禁止其他事务对这个字段进行更新。可以避免脏读和不可重复读，但幻读的问题仍然存在。 |
    | SERIALIZABLE     | 确保事务可以从一个表中读取相同的行。在这个事务持续期间，禁止其他事务对该表执行插入，更新和删除操作。所有兵法问题都可以避免，但性能十分低下。 |

    1. 事务的隔离级别要得到底层数据库引擎的支持，而不是应用程序或者框架的支持。
    2. Oracle 支持的 2 种事务隔离级别：READ_COMMITTED , SERIALIZABLE
    3. Mysql 支持 4 种事务隔离级别。

12. 设置隔离事务属性
    1. 用 @Transactional 注解声明式地管理事务时可以在 @Transactional 的 isolation 属性中设置隔离级别.
    2. 在 Spring 2.x 事务通知中, 可以在 `<tx:method>` 元素中指定隔离级别

13. 设置回滚事务属性
    1. 默认情况下只有未检查异常(RuntimeException和Error类型的异常)会导致事务回滚. 而受检查异常不会.
    2. 事务的回滚规则可以通过 @Transactional 注解的 rollbackFor 和 noRollbackFor 属性来定义. 这两个属性被声明为 Class[] 类型的, 因此可以为这两个属性指定多个异常类.
        1. rollbackFor:  遇到时必须进行回滚
        2. noRollbackFor: 一组异常类，遇到时必须不回滚
    3. 在 Spring 2.x 事务通知中, 可以在 `<tx:method>` 元素中指定回滚规则. 如果有不止一种异常, 用逗号分隔.

14. 超时和只读属性
    1. 由于事务可以在行和表上获得锁,  因此长事务会占用资源, 并对整体性能产生影响. 
    2. 如果一个事物只读取数据但不做修改, 数据库引擎可以对这个事务进行优化.
    3. 超时事务属性: 事务在强制回滚之前可以保持多久. 这样可以防止长期运行的事务占用资源.
    4. 只读事务属性: 表示这个事务只读取数据但不更新数据, 这样可以帮助数据库引擎优化事务.

15. 设置超时和只读事务属性
    1. 超时和只读属性可以在 @Transactional 注解中定义.超时属性以秒为单位来计算.
    2. 在 Spring 2.x 事务通知中, 超时和只读属性可以在 `<tx:method>` 元素中进行指定.

## Spring  整合 Hibernate

1. Spring 整合 Hibernate
    1. Spring 支持大多数流行的 ORM 框架, 包括 Hibernate JDO, TopLink, Ibatis 和 JPA。
    2. Spring 对这些 ORM 框架的支持是一致的, 因此可以把和 Hibernate 整合技术应用到其他 ORM 框架上.
    3. Spring 2.0 同时支持 Hibernate 2.x 和 3.x. 但 Spring 2.5 只支持 Hibernate 3.1 或更高版本
2. 在 Spring 中配置 SessionFactory
    1. 对于 Hibernate 而言, 必须从原生的 Hibernate API 中构建 SessionFactory. 此外, 应用程序也无法利用 Spring 提供的数据存储机制(例如: Spring 的事务管理机制)
    2. Spring 提供了对应的工厂 Bean, 可以用单实例的形式在 IOC 容器中创建 SessionFactory 实例.
3. 在 Spring 中配置 SessionFactory(1)
    1. 可以利用 LocalSessionFactoryBean 工厂 Bean, 声明一个使用 XML 映射文件的 SessionFactory 实例.
    2. 需要为该工厂 Bean 指定 configLocation 属性来加载 Hibernate 配置文件.
        ```
        <!-- 配置 hibernate 的 SessionFactory -->
        <bean id="sessionFactory" class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
            <property name="configLocation" value="hibernate.cfg.xml"/>
        </bean>
        
        <!-- 注入 sessionFactory -->
        <bean id="courseDAO" class="com.onevgo.CourseDAOImpl">
            <property name="sessionFactory" ref="sessionFactory"/>
        </bean>
        ```
4. 在 Spring 中配置 SessionFactory(2)
    1. 如果在 Spring IOC 容器中配置数据源. 可以将该数据源注入到 LocalSessionFactoryBean 的 dataSource 属性中. 该属性可以指定的数据源会覆盖掉 Hibernate 配置文件里的数据库配置.
        ```
        <!-- 配置 spring context 引入外部属性文件 -->
        <context:property-placeholder location="c3p0.properties" />
        
        <!-- 配置数据源 -->
        <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
            <property name="user" value="${user}"/>
            <property name="password" value="${password}"/>
            <property name="jdbcUrl" value="${jdbcUrl}"/>
            <property name="driverClass" value="${driverClass}"/>
            
            <property name="checkoutTimeout" value="${checkoutTimeout}"/>
            <property name="idleConnectionTestPeriod" value="${idleConnectionTestPeriod}"/>
            <property name="initialPoolSize" value="${initialPoolSize}"/>
            <property name="maxIdleTime" value="${maxIdleTime}"/>
            
            <property name="maxPoolSize" value="${maxPoolSize}"/>
            <property name="minPoolSize" value="${minPoolSize}"/>
            <property name="maxStatements" value="${maxStatements}"/>
        </bean>
        
        <!-- 配置 sessionFactory -->
        <bean id="sessionFactory" class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
            <property name="configLocation" value="hibernate.cfg.xml"/>
            <property name="dataSource" ref="dataSource">
        </bean>
        ```
5. 在 Spring 中配置 SessionFactory(3)
    1. 可以将所有配置合并到 LocalSessionFactoryBean 中,从而忽略 Hibernate 配置文件. 
    2. 可以在 LocalSessionFactoryBean 的 mappingResources 属性中指定 XML 映射文件的位置.该属性为 String[] 类型. 因此可以指定一组映射文件.
    3. 在 hibernateProperties 属性中指定数据库方言等.
        ```
        <!-- 配置 sessionFactory -->
        <bean id="sessionFactory" class="org.springframework.orm.hibernate3.LocalSessionFactoryBean">
            <property name="dataSource" ref="dataSource"/>
            <property name="hibernateProperties">
                <props>
                    <prop key="hibernate.dialect">org.hibernate.dialect.MysqlDialect</prop>
                    <prop key="hibernate.show_sql">true</prop>
                    <prop key="hibernate.hbm2ddl.auto">update</prop>
                </props>
            </property>
            
            <property name="mappingResources">
                <list>
                    <value>com/onevgo/Course.hbm.xml</value>
                </list>
            </property>
        </bean>
        ```
6. 用 Spring 的 ORM 模板持久化对象
    1. 在单独使用 ORM 框架时, 必须为每个 DAO 操作重复某些常规任务. 例如: 打开关闭 Session 对象; 启动, 提交, 回滚事务等.
    2. 同 JDBC 一样, Spring 采取了相同的方法 ------ 定义模板类和 DAO 支持类来简化 ORM 框架的使用. 而且 Spring 在不同的事务管理 API 之上定义了一个事务抽象层. 对于不同的 ORM 框架, 只需要选择相应的事务管理器实现.
7. Spring 对不同数据存储策略的支持类
    ```
    支持类      JDBC                            Hibernate
    模板类      JdbcTemplate                    HibernateTemplate
    DAO支持类   JdbcDaoSupport                  HibernateDaoSupport
    事务管理类   DataSourceTransactionManager    HibernateTransactionManager
    ```
    1. HibernateTemplate 确保了 Hibernate 会话能够正确地打开和关闭. 
    2. HibernateTemplate 也会让原生的 Hibernate 事务参与到 Spring 的事务管理体系中来. 从而利用 Spring 的声明式事务管理事务.
8. 使用 Hibernate 模板
    1. HibernateTemplate 中的模板方法管理会话和事务. 如果在一个支持事务的 DAO 方法中有多个 Hibernate 操作, 模板方法可以确保它们会在同一个会话和事务中运行. 因此没有必要为了会话和事务管理去和 Hibernate API 打交道.
    2. 通过为 DAO 方法添加 @Transactional 注解将其声明为受事务管理的.
    3. HibernateTemplate 类是线程安全的, 因此可以在 Bean 配置文件中只声明一个实例, 并将该实例注入到所有的 Hibernate DAO 中.
9. 使用 Hibernate 模板示例代码
10. 在 HibernateTemplate 中访问 Hibernate 底层 Session
11. 继承 Hibernate 的 DAO 支持类
    1. Hibernate DAO 可以通过继承 HibernateDaoSupport 来继承 setSessionFactory() 和 setHibernateTemplate() 方法. 然后, 只要在 DAO 方法中调用 getHibernateTemplate() 方法就可以获取到模板实例.
    2. 如果为 HibernateDaoSupport 实现类注入了 SessionFactory 实例, 就不需要在为之注入 HibernateTemplate 实例了, 因为HibernateDaoSupport  会根据传入的 SessionFactory 在其构造器内创建 HibernateTemplate 的实例, 并赋给 hibernateTemplate 属性
12. 用 Hibernate 的上下文 Session 持久化对象
    1. Spring 的 HibernateTemplate 可以管理会话和事务, 简化 DAO 实现. 但使用 HibernateTemplate 意味着DAO 必须依赖于 Spring 的 API
    2. 代替 HibernateTemplate 的另一种办法是使用 Hibernate 的上下文 Session 对象. 
    3. Hibernate 上下文 Session 对象和 Spring 的事务管理合作的很好, 但此时需保证所有的DAO 方法都支持事务
    4. 注意此时不需在 beans.xml 文件中配置, 因为 Spring 此时已经开始事务, 所以已经在 ThreadLocal 对象中绑定了 Session 对象 
        ```
        <prop key="hibernate.current_session_context_class">thread</prop>
        ```
13. 用 Hibernate 的上下文 Session 持久化对象
    1. 在 Hibernate 会话中调用原生的方法时, 抛出的异常依旧是原生的 HibernateException. 
    2. 为了保持一致的异常处理方法, 即把 Hibernate 异常转换为 Spring 的 DataAccessException 异常, 那么必须为需要异常转换的 DAO 类添加 @Respository 注解.
    3. 然后在注册一个 org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor 实例, 将原生的 Hibernate 异常转换为 Spring 的 DataAccessException 层次结构中的数据存取异常. 这个 Bean 后置处理器只为添加了@Respository 注解的 Bean 转换异常. 
14. Hibernate 上下文相关的 Session(1)
    1. 从 Hibernate 3 开始, SessionFactory 新增加了 getCurrentSession() 方法, 该方法可直接获取“上下文“相关的 Session. 
    2. Hibernate 通过 CurrentSessionContext 接口的实现类和 配置参数hibernate.current_session_context_class定义 “上下文”
        1. JTASessionContext: 根据 JTA 来跟踪和界定 Session 对象.
        2. ThreadLocalSessionContext: 通过当前正在执行的线程来跟踪和界定 Session 对象
        3. ManagedSessionContext: 通过正在当前执行来跟踪和界定 Session 对象. 但程序需要调用该类的静态方法来绑定 Sessio 对象, 取消绑定, flush 或者关闭 Session 对象.
15. Hibernate 上下文相关的 Session(2)
    1. 如果使用 ThreadLocalSessionContext 策略, Hibernate 的 Session 会随着 getCurrentSession() 方法自动打开, 随着事务提交自动关闭.
    2. 若当前应用是基于 JTA 的分布式事务, 通常采用第一种方式; 而对于独立的 Hibernate 应用则使用第二种应用.
    3. 配置:
        1. 根据 JTA 来跟踪和界定 Session 对象:
            ```
            <property name="hibernate.current_session_context_class">thread</property>
            ```
        2. 通过当前正在执行的线程来跟踪和界定 Session 对象：
            ```
            <property name="hibernate.current_session_context_class">thread</property>
            ```

## Spring  整合 Struts1.x

1. 在通用的 web 应用中访问 Spring
  
    1. 通过注册 Servlet 监听器 ContextLoaderListener, Web 应用程序可以加载 Spring 的ApplicationContext 对象. 这个监听器会将加载好的ApplicationContext 对象保存到 Web 应用程序的 ServletContext 中. 随后, Servlet 或可以访问 ServletContext 的任意对象就能通过一个辅助方法来访问 Spring 的应用程序上下文了.
2. 在通用的 web 应用中访问 Spring 具体实现
    1. 在 web.xml 文件中注册 Spring 提供的 Servlet 监听器 org.springframework.web.context.ContextLoaderListener , 它会在当前 web 应用被加载时将 Spring 的 ApplicationContext 保存到 ServletContext 对象中. 
    2. org.springframework.web.context.ContextLoaderListener 监听器通过查找 web 应用初始化参数 contextConfigLocation 来获取 Bean 配置文件的位置. 如果有多个 Bean 配置文件, 可以通过逗号或空格进行分隔. contextConfigLocation 的默认值为 /WEB-INF/applicationContext.xml. 若实际的文件和默认值一致则可以省略这个 web 应用的初始化参数
3. web.xml 文件示例代码
    ```
    <context-param>
        <param-name>contextConfiguration</param-name>
        <param-value>/WEB-INF/applicationContext.xml</param-value>
    </context-param>
    
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    ```
4. 在 web 应用程序中访问 Spring 的 ApplicationContext 对象
    1. 可以通过 `org.springframework.web.context.support.WebApplicationContextUtils` 的静态方法 `public static WebApplicationContext getRequiredWebApplicationContext(ServletContext sc) throws IllegalStateException` 来获取 Spring 的 ApplicationContext 对象
        ```
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        ```
5. Spring 整合 Struts
    1. 通过注册 Servlet 监听器 org.springframework.web.context.ContextLoaderListener , Struts 应用程序能够加载 Spring 的 ApplicationContext 对象,并像在通用的 Web 应用程序中那样在 Servlet 上下文中对它进行访问. 然而, Spring 还提供了更好的, 特定于 Struts 的解决方案.
        1. 在 struts 配置文件中注册 Struts 插件来加载应用程序上下文, 它会自动引用 Servlet 监听器加载的应用程序上下文作为它的父上下文, 以便可以引用其中声明的 Bean
        2. Spring 提供了一个 ActionSupport 对象, 这是 Action 类的一个子类, 通过它的 getWebApplicationContext() 方法可以获取到 Spring 的应用程序上下文
        3. 在 Spring 的应用程序上下文中声明 Struts 的 Action 对象, 使用Spring 的依赖注入来注入 Spring 应用程序上下文的其他 Bean
    2. 将 Spring 的应用程序上下文加载到 Struts 应用程序中(1)
        1. 将 Spring 的应用程序上下文加载到 Struts 应用程序中
            1. 在 web.xml 文件中注册 Servlet 监听器 org.springframework.web.context.ContextLoaderListener 这个监听器会默认加载 /WEB-INF/applicationContext.xml 作为 Spring 的配置文件.  因而无需显式地指定它的位置
                ```
                <listener>
                    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
                </listener>
                ```
        2. 在 Spring 的 Bean 配置文件中声明 Struts Action
            1. 除了在 struts 动作中通过 Spring 应用程序中主动查找 Spring Bean 之外, 还可以使用依赖注入模式将 Spring 中声明的 Bean 注入到 Struts 动作中. 
            2. 在 applicationContext.xml 中声明 Struts Action 要求该 Bean 的 name 必须和它在 struts-config.xml 文件中的路径一致. 因为该 <bean> 元素的 id 属性不能包含 / 字符, 所以应该用 name 属性代替.
            3. 还必须注册 struts 请求处理器 org.springframework.web.struts.DelegatingRequestProcessor 让 Struts 匹配动作路径和 Bean 名称, 从而在 Spring 的应用程序上下文中查找相应的动作实例. 注册了这个请求处理器之后, 在 struts-config.xml 中就不需要指定 type 属性了 
            4. 若已经在 struts-config.xml 文件中注册了一个请求处理器, 可以将所有 action 节点的 type 属性指定为 org.springframework.web.struts.DelegatingActionProxy
        3. 在 Spring 的 Bean 配置文件中声明 Struts Action 的示例代码
            1. web.xml
                ```
                <listener>
                    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
                </listener>
                ```
            2. struts-config.xml
                ```
                <action-mapping>
                    <action path="/template" type="org.springframework.web.struts.DelegatingActionProxy" />
                    <action path="/test" extends="/template" />
                    <action path="/hello" extends="/template" />
                </action-mapping>
                ```
    3. 在 Spring 的 Bean 配置文件中声明 Struts Action 的示例代码(2)
        ```
        <bean id="userDAO" class="com.onevgo.UserDAO"></bean>
        <bean id="userService" class="com.onevgo.UserSerivce">
            <property name="userDAO" ref="userDAO"></property>
        </bean>
        <bean name="/hello" class="com.onevgo.UserAction">
            <property name="userService" ref="userService"></property>
        </bean>
        <bean name="/test" parent="/hello"></bean>
        ```
        1. 在 struts 配置文件 struts-config.xml 文件中注册 org.framework.web.struts.ContextLoaderPlugin 默认情况下, 该插件会利用 web.xml 文件中注册的 ActionServlet 实例的名称加上 –servlet.xml 后缀作为文件名. 如果想要另外加载一个 Bean 配置文件, 可以在 contextConfigLocation 属性中指定文件名. 但此时需通过 servlet 配置 Spring 容器随 Web 应用的启动而初始化. 而不适用 Listener 配置.
        2. 如果 applicationContext.xml 文件和 action-servlet.xml 文件同时存在, struts 插件加载的 Spring 应用程序上下文会自动引用在 applicationContext.xml 中的配置信息作为父上下文. 业务服务通常配置在 applicationContext.xml 中, 而 web 相关组件配置在 action-servlet.xml 中.
    4. 在 Spring 的 Bean 配置文件中声明 Struts Action 的示例代码(3)
        1. web.xml
            ```
            <listener>
                <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
            </listener>
            ```
        2. struts-config.xml
            ```
            <action-mapping>
                <action path="/template" type="org.springframework.web.struts.DelegatingActionProxy" />
                <action path="/test" extends="/template" />
                <action path="/hello" extends="/template" />
            </action-mapping>
            <message-resource parameter="MessageResources" />
            <plug-in className="org.springframework.web.struts.ContextLoaderPlugIn"/>
            ```
    4. 在 Spring 的 Bean 配置文件中声明 Struts Action 的示例代码(4)

## Spring  整合 Struts2

1. 在通用的 web 应用中访问 Spring
  
    1. 通过注册 Servlet 监听器 ContextLoaderListener, Web 应用程序可以加载 Spring 的ApplicationContext 对象. 这个监听器会将加载好的ApplicationContext 对象保存到 Web 应用程序的 ServletContext 中. 随后, Servlet 或可以访问 ServletContext 的任意对象就能通过一个辅助方法来访问 Spring 的应用程序上下文了.
2. 在通用的 web 应用中访问 Spring 具体实现
    1. 在 web.xml 文件中注册 Spring 提供的 Servlet 监听器 org.springframework.web.context.ContextLoaderListener, 它会在当前 web 应用被加载时将 Spring 的 ApplicationContext 保存到 ServletContext 对象中. 
    2. org.springframework.web.context.ContextLoaderListener 监听器通过查找 web 应用初始化参数 contextConfigLocation 来获取 Bean 配置文件的位置. 如果有多个 Bean 配置文件, 可以通过逗号或空格进行分隔. contextConfigLocation 的默认值为 /WEB-INF/applicationContext.xml. 若实际的文件和默认值一致则可以省略这个 web 应用的初始化参数
3. web.xml 文件示例代码
    ```
    <context-param>
        <param-name>contextConfiguration</param-name>
        <param-value>/WEB-INF/applicationContext.xml</param-value>
    </context-param>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
    ```
4. 在 web 应用程序中访问 Spring 的 ApplicationContext 对象
    1. 可以通过 org.springframework.web.context.support.WebApplicationContextUtils 的静态方法 public static WebApplicationContext getRequiredWebApplicationContext(ServletContext sc) throws IllegalStateException 来获取 Spring 的 ApplicationContext 对象
        ```
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        ```
5. 整合 Struts2
    1. Struts2 通过插件实现和 Spring 的整合. 
    2. Struts2 提供了两种和 Spring整合基本的策略:
        1. 将 Action 实例交给 Spring 容器来负责生成, 管理, 通过这种方式, 可以充分利用 Spring 容器的 IOC 特性, 提供最好的解耦
        2. 利用  Spring 插件的自动装配功能, 当 Spring 插件创建 Action 实例后, 立即将 Spring 容器中对应的业务逻辑组件注入 Action 实例. 
6. 让 Spring 管理控制器
    1. 将 Action 实例交给 Spring 容器来负责生成, 管理, 通过这种方式, 可以充分利用 Spring 容器的 IOC 特性, 提供最好的解耦
    2. 整合流程:
        1. 安装 Spring 插件: 把 struts2-spring-plugin-2.2.1.jar 复制到当前 WEB 应用的 WEB-INF/lib 目录下
        2. 在 Spring 的配置文件中配置 Struts2 的 Action 实例
        3. 在 Struts 配置文件中配置 action, 但其 class 属性不再指向该 Action 的实现类, 而是指向 Spring 容器中 Action 实例的 ID
7. 自动装配
    1. 利用  Spring 插件的自动装配功能, 当 Spring 插件创建 Action 实例后, 立即将 Spring 容器中对应的业务逻辑组件注入 Action 实例. 
    2. 配置自动装配策略: Spring 插件的自动装配可以通过 struts.objectFactory.spring.autoWire 常量指定, 该常量可以接受如下值:
        1. name: 根据属性名自动装配. 
        2. type: 根据类型自动装配. 若有多个 type 相同的 Bean, 就抛出一个致命异常; 若没有匹配的 Bean, 则什么都不会发生, 属性不会被设置
        3. auto: Spring 插件会自动检测需要使用哪种方式自动装配方式
        4. constructor: 同 type 类似, 区别是 constructor 使用构造器来构造注入所需的参数
    3. 整合流程:
        1. 安装 Spring 插件
        2. 正常编写 struts 配置文件
        3. 编写 spring 配置文件, 在该配置文件中不需要配置 Action 实例

## Spring注解驱动开发

1. 容器
    1. AnnotationConfigApplicationContext
        1. 配置类
        2. 包扫描
    2. 组件添加
        1. @ComponentScan
            1. value 指定要扫描的包
            2. excludeFilters = Filter[] 指定扫描的时候按照什么规则排除那些组件
            3. includeFilters = Filter[] 指定扫描的时候只需要包含哪些组件
            4. FilterType.ANNOTATION 按照注解
            5. FilterType.ASSIGNABLE_TYPE 按照给定的类型
            6. FilterType.ASPECTJ 使用 aspectj 表达式
            7. FilterType.REGEX 使用正则表达式
            8. FilterType.CUSTOM 使用自定义规则
        2. @Bean
            1. 指定初始化销毁
                1. 指定init-method="" destroy-method=""
            2. 初始化其他方式
                1. InitializingBean（初始化设置值之后）
                2. DisposableBean（销毁）
                3. JSR250
                    1. @PostConstruct 在 bean 创建完成并且属性赋值完成，来执行初始化方法
                    2. @PreDestroy 在容器销毁 bean 之前通知我们进行清理工作
            3. BeanPostProcessor bean的后置处理器
                1. 在 bean 初始化前后进行一些处理工作
                2. postProcessBeforeInitialization 在初始化之前工作
                3. postProcessAfterInitialization 在初始化之后工作
        3. @Configuration
            1. 告诉 spring 这是一个配置类
        4. @Component
        5. @Service
        6. @Controller
        7. @Repository
        8. @Conditional
            1. 标识在类上，类中组件统一设置，满足当前条件，这个类中配置的所有 bean 注册才能生效
            2. 标识在方法上，按照一定的条件进行判断，满足条件给容器中注册bean
        9. @Primary
            1. 让Spring进行自动装配的时候，默认使用首选的 bean
        10. @Lazy
            1. 单实例 bean 默认在容器启动的时候创建对象
            2. 懒加载 容器启动不创建对象。第一次使用（获取）Bean 创建对象，并初始化
        11. @Scope 默认是单实例的
            1. prototype 多实例的 ioc 容器启动并不会去调用方法创建对象放在容器中。每次获取的时候才会调用方法创建对象；
            2. singleton 单实例的 默认值 ioc容器启动会调用方法创建对象放到ioc容器中，以后每次获取就是直接从容器 map.get() 中拿
            3. request 同一个请求创建一个实例
            4. session 同一个session创建一个实例
        12. @Import 快速给容器中导入一个组件
            1. @Import(要导入到容器中的组件)，容器中就会自动组册这个组件，id默认是全类名
            2. ImportSelector 返回需要导入的组件的全类名数组
            3. ImportBeanDefinitionRegistrar 手动注册 bean 到容器中
        13. ImportSelector
        14. 工厂模式
            1. FactoryBean
                1. 默认获取到的是工厂 bean 调用 getObject 创建的对象
                2. 要获取工厂 Bean 本身，我们需要给 id 前面加一个 & (&colorBeanFactory)
                3. &beanName获取Factory本身
    3. 组件赋值
        1. @Value
            1. 使用 @Value 赋值
                1. 基本数值
                2. 可以写 SpEL #{}
                3. 可以写 ${} 取出配置文件 properties 的值(在运行环境变量里面的值)
        2. @Autowired
            1. @Qualifier
            2. 其他方式
                1. @Resources（JSR250）
                    1. @Resource 可以和 @Autowired 一样实现自动装配功能，默认是按照组件名称进行装配的
                    2. 不支持 @Primary 功能，不支持 @Autowired(required = false)
                2. @Inject（JSR330，需要导入javax.inject）
                    1. @Inject
                    2. 需要导入 javax.inject 的包，和Autowired的功能一样。没有required = false的功能
            3. @Autowired Spring定义的，@Resource @Inject都是java规范
        3. @PropertySource
            1. 使用 @PropertySource 读取外部配置文件中的 k/v 保存到运行的环境变量中；
            2. 加载完外部的配置文件以后使用 ${} 取出配置文件的值
        4. @PropertySources
        5. @Profile
            1. @Profile 指定组件在哪个环境的情况下才能被注册到容器中，不指定，任何环境下都能注册这个组件
                1. 加了环境标识的bean，只有这个环境被激活的时候才能注册到容器中。默认是 default 环境
                2. 写在配置类上，只有是指定的环境的时候，整个配置类里面的所有配置才能开始生效
                3. 没有标注环境标识的bean在任何环境下都是加载的
            2. Environment
            3. -Dspring.profiles.active=test
    4. 组件注入
        1. 构造器，参数，方法，属性；都是从容器中获取参数组件的值
        2. 方法参数
            1. 标注在方法位置，@Bean + 方法参数，参数从容器中获取；默认不写 @Autowired，效果是一样的，都能自动装载
            2. 放在参数位置
        3. 构造器注入
            1. 标在构造器上，如果组件只有一个有参构造器，这个有参构造器的 @Autowired 可以省略，参数位置的组件还是可以自动从容器中获取
        4. ApplicationContextAware
            1. ApplicationContextAwareProcessor
            2. AutowiredAnnotationBeanPostProcessor 解析完成自动装配功能
        5. xxxAware
    5. AOP
        1. @EnableAspectJAutoProxy
        2. @Before/@After/@AfterReturning/@AfterThrowing/@Around
        3. @Pointcut
    6. 声明式事务
        1. @EnableTransactionManagement
        2. @Transactional
2. 扩展原理
    1. BeanFactoryPostProcessor
        1. Spring容器标准初始化之后执行（BeanPostProcessor之前），此时bean还未创建
        2. Spring容器初始化两大步
            1. 加载保存和读取所有bean配置
            2. 按照之前的配置创建bean
    2. BeanDefinitionRegistryPostProcessor
        1. BeanFactoryPostProcessor子类，可自定义添加bean定义
        2. BeanDefinitionRegistry
            1. BeanDefinitionBuilder
    3. ApplicationListener
      
        1. @EventListener
    4. Spring容器创建过程
        1. refresh(); 加载或刷新配置的持久表示，配置可以是XML文件、属性文件或关系数据库模式。由于这是一种启动方法，如果失败，它应该销毁已经创建的单例，以避免挂起资源。换句话说，在调用该方法之后，应该实例化所有或根本不实例化单例。
        2. prepareRefresh(); 准备此上下文用于刷新、设置其启动日期和活动标志以及执行任何属性源的初始化。
            1. initPropertySources(); 在上下文环境中初始化任何占位符属性源
            2. getEnvironment().validateRequiredProperties(); 验证所有标记为必需的属性都是可解析的
            3. this.earlyApplicationEvents = new LinkedHashSet<ApplicationEvent>(); 允许收集早期的ApplicationEvents，一旦多播可用，就可以发布它们
        3. obtainFreshBeanFactory(); 告诉子类刷新内部bean工厂
            1. refreshBeanFactory(); 子类必须实现此方法来执行实际的配置加载。这个方法在任何其他初始化工作之前由refresh()调用。子类将创建一个新的bean工厂并保存对它的引用，或者返回它所保存的一个bean工厂实例。在后一种情况下，如果不止一次刷新上下文，它通常会抛出一个IllegalStateException。
                1. this.beanFactory = new DefaultListableBeanFactory();
                2. this.beanFactory.setSerializationId(getId()); 指定一个用于序列化的id，如果需要，允许将这个BeanFactory从这个id反序列化回BeanFactory对象。
            2. getBeanFactory(); 子类必须在这里返回它们的内部bean工厂。
        	3. 将创建的 BeanFactory (DefaultListableBeanFactory) 返回
        4. prepareBeanFactory(beanFactory); 配置工厂的标准上下文特性，例如上下文的类加载器和后置处理器。
        	1. 告诉内部bean工厂使用上下文的类加载器等等。
        	    1. beanFactory.setBeanClassLoader(getClassLoader()); 设置类加载器
        	    2. beanFactory.setBeanExpressionResolver(new StandardBeanExpressionResolver(beanFactory.getBeanClassLoader())); 设置表达式解析器
        	    3. beanFactory.addPropertyEditorRegistrar(new ResourceEditorRegistrar(this, getEnvironment()));
        	        1. 添加要应用于所有bean创建过程的PropertyEditorRegistry。
        	        2. 这样的注册器创建新的PropertyEditor实例，并在给定的注册表上注册它们，对于每个bean创建尝试都是新鲜的。这避免了在定制编辑器上同步的需要;因此，通常更可取的方法是使用此方法而不是registerCustomEditor。
            2. 使用上下文回调配置bean工厂。
                1. beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));
                    1. 将 ApplicationContext 传递给bean的 BeanPostProcessor 实现，这些bean实现了 EnvironmentAware、EmbeddedValueResolverAware、ResourceLoaderAware、ApplicationEventPublisherAware、MessageSourceAware 和/或 ApplicationContextAware 接口。
                    2. 实现的接口按照上面提到的顺序依次执行回调。
                    3. 应用程序上下文将自动将此注册到它们的底层bean工厂。应用程序不直接使用它。
                2. 忽略给定的自动装配依赖接口。
                    1. 这通常被应用程序上下文用来注册以其他方式解析的依赖项，比如 BeanFactory 通过 BeanFactoryAware 注册 或 ApplicationContext 通过 ApplicationContextAware 注册。
                    2. 默认情况下，只忽略BeanFactoryAware接口。若要忽略其他类型，请为每个类型调用此方法。
                    ```
                    beanFactory.ignoreDependencyInterface(EnvironmentAware.class);
                    beanFactory.ignoreDependencyInterface(EmbeddedValueResolverAware.class);
                    beanFactory.ignoreDependencyInterface(ResourceLoaderAware.class);
                    beanFactory.ignoreDependencyInterface(ApplicationEventPublisherAware.class);
                    beanFactory.ignoreDependencyInterface(MessageSourceAware.class);
                    beanFactory.ignoreDependencyInterface(ApplicationContextAware.class);
                    ```
            3. 注册具有相应自动装配值的特殊依赖项类型。
                1. 这是针对工厂/上下文引用的，这些引用应该是可自动装配的，但在工厂中没有定义为bean:例如，类型为 ApplicationContext 的依赖项解析为bean所在的 ApplicationContext 实例。
                2. 注意:在普通 BeanFactory 中没有注册这样的默认类型，即使对于 BeanFactory 接口本身也没有。
                ```
                beanFactory.registerResolvableDependency(BeanFactory.class, beanFactory);
                beanFactory.registerResolvableDependency(ResourceLoader.class, this);
                beanFactory.registerResolvableDependency(ApplicationEventPublisher.class, this);
                beanFactory.registerResolvableDependency(ApplicationContext.class, this);
                ```
            4. beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(this)); 注册早期后置处理器用于检测内部bean是不是 ApplicationListener。
                1. 检测实现 ApplicationListener 接口的bean的 BeanPostProcessor。这将捕获getBeanNamesForType和相关操作无法可靠检测到的bean，而这些操作只能对顶级bean起作用。
                2. 使用标准Java序列化，这个后置处理器不会作为 DisposableBeanAdapter 的一部分进行序列化。但是，使用可选的序列化机制，DisposableBeanAdapter.writeReplace 可能根本不会被使用，所以我们防御地将这个后置处理器的字段状态标记为 transient。
            5. 检测LoadTimeWeaver并准备编织(如果找到)。
                1. beanFactory.addBeanPostProcessor(new LoadTimeWeaverAwareProcessor(beanFactory));
                    1. 将上下文的默认 LoadTimeWeaver 传递给实现 LoadTimeWeaverAware 接口的bean的BeanPostProcessor实现。
                    2. 如果默认的 LoadTimeWeaver 是可用的，应用程序上下文将自动将其注册到它们的底层bean工厂。
                    3. 应用程序不应该直接使用该类。
                2. beanFactory.setTempClassLoader(new ContextTypeMatchClassLoader(beanFactory.getBeanClassLoader()));
            6. 注册缺省环境bean。
                1. beanFactory.registerSingleton(ENVIRONMENT_BEAN_NAME, getEnvironment());
                2. beanFactory.registerSingleton(SYSTEM_PROPERTIES_BEAN_NAME, getEnvironment().getSystemProperties());
                3. beanFactory.registerSingleton(SYSTEM_ENVIRONMENT_BEAN_NAME, getEnvironment().getSystemEnvironment());
        4. postProcessBeanFactory(beanFactory); 允许在上下文子类中对bean工厂进行后置处理。 
          
            1. 在应用程序上下文的标准初始化之后修改其内部bean工厂。所有bean定义都已加载，但还没有实例化bean。这允许在特定的 ApplicationContext 实现中注册特殊的 BeanPostProcessor 等。
        5. **以上是BeanFactory的创建及预准备工作**
        6. invokeBeanFactoryPostProcessors(beanFactory); 调用上下文中注册为bean的工厂处理器。
            1. 实例化并调用所有已注册的BeanFactoryPostProcessor bean，如果给定显式顺序，则遵循显式顺序。
            2. 必须在单例实例化之前调用。
            3. BeanDefinitionRegistryPostProcessor: 扩展到标准的 BeanFactoryPostProcessor SPI，允许在常规的 BeanFactoryPostProcessor 检测开始之前注册更多的bean定义。特别是，BeanDefinitionRegistryPostProcessor 可以注册更多的bean定义，这些bean定义又定义 BeanFactoryPostProcessor 实例。
                1. 如果有的话，首先调用 BeanDefinitionRegistryPostProcessor。
                    ```
                    BeanDefinitionRegistryPostProcessor registryProcessor = (BeanDefinitionRegistryPostProcessor) postProcessor;
                    registryProcessor.postProcessBeanDefinitionRegistry(registry);
                    ```
                2. 将实现 PriorityOrdered、Ordered 与常规的 BeanDefinitionRegistryPostProcessor 分开。
                3. 首先，调用实现 PriorityOrdered 的 BeanDefinitionRegistryPostProcessor。
                    ```
                    if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
                        currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
                    }
                    invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
                    ```
                4. 接下来，调用实现 Ordered 的 BeanDefinitionRegistryPostProcessor。
                    ```
                    if (!processedBeans.contains(ppName) && beanFactory.isTypeMatch(ppName, Ordered.class)) {
                        currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
                    }
                    invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
                    ```
                5. 最后，调用所有其他 BeanDefinitionRegistryPostProcessor，直到不再出现其他bean为止。
                    ```
                    if (!processedBeans.contains(ppName)) {
                        currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
                    }
                    invokeBeanDefinitionRegistryPostProcessors(currentRegistryProcessors, registry);
                    ```
                6. 现在，调用到目前为止处理的所有处理器的postProcessBeanFactory回调。
                    ```
                    invokeBeanFactoryPostProcessors(registryProcessors, beanFactory);
                    invokeBeanFactoryPostProcessors(regularPostProcessors, beanFactory);
                    ```
            4. BeanFactoryPostProcessor: 
                1. 允许自定义修改应用程序上下文的bean定义，调整上下文的底层bean工厂的bean属性值。
                2. 应用程序上下文可以自动检测bean定义中的BeanFactoryPostProcessor bean，并在创建任何其他bean之前应用它们。
                3. 适用于针对覆盖在应用程序上下文中配置的bean属性的系统管理员的自定义配置文件。
                4. 有关解决此类配置需求的开箱即用解决方案，请参见PropertyResourceConfigurer及其具体实现。
                5. BeanFactoryPostProcessor可以与bean定义交互并修改bean定义，但不能与bean实例交互。这样做可能会导致过早的bean实例化，破坏容器并导致意想不到的副作用。如果需要bean实例交互，则考虑实现BeanPostProcessor。
                6. 将实现 PriorityOrdered、Ordered 和常规的 BeanFactoryPostProcessor 分开。
                7. 首先，调用实现 PriorityOrdered 的 BeanFactoryPostProcessor。
                    ```
                    priorityOrderedPostProcessors.add(beanFactory.getBean(ppName, BeanFactoryPostProcessor.class));
                    invokeBeanFactoryPostProcessors(priorityOrderedPostProcessors, beanFactory);
                    ```
                8. 接下来，调用实现 Ordered 的 BeanFactoryPostProcessor。
                    ```
                    orderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
                    invokeBeanFactoryPostProcessors(orderedPostProcessors, beanFactory);
                    ```
                9. 最后，调用所有其他 BeanFactoryPostProcessor。
                    ```
                    nonOrderedPostProcessors.add(beanFactory.getBean(postProcessorName, BeanFactoryPostProcessor.class));
                    invokeBeanFactoryPostProcessors(nonOrderedPostProcessors, beanFactory);
                    ```
                10. beanFactory.clearMetadataCache(); 清除缓存的合并bean定义，因为后置处理程序可能修改了原始元数据，例如替换值中的占位符...
        7. registerBeanPostProcessors(beanFactory); 注册拦截bean创建的bean处理器。
            1. 实例化并调用所有已注册的BeanPostProcessor bean，如果给定显式顺序，则遵循显式顺序。
            2. 必须在应用程序bean的任何实例化之前调用。
            3. beanFactory.addBeanPostProcessor(new BeanPostProcessorChecker(beanFactory, beanProcessorTargetCount));
              
                1. 注册 BeanPostProcessorChecker，当bean在 BeanPostProcessor 实例化过程中被创建时，即当一个bean没有资格被所有 BeanPostProcessor 处理时，它记录一条信息消息。
            4. 在实现 PriorityOrdered、Ordered 和常规的 BeanPostProcessor 之间进行分离。
            5. 首先，注册实现 PriorityOrdered 的 BeanPostProcessor。
                ```
                priorityOrderedPostProcessors.add(pp);
                registerBeanPostProcessors(beanFactory, priorityOrderedPostProcessors);
                ```
            6. 接下来，注册实现 Ordered 的 BeanPostProcessor。
                ```
                orderedPostProcessors.add(pp);
                registerBeanPostProcessors(beanFactory, orderedPostProcessors);
                ```
            7. 现在，注册所有常规的 BeanPostProcessor。
                ```
                nonOrderedPostProcessors.add(pp);
                registerBeanPostProcessors(beanFactory, nonOrderedPostProcessors);
                ```
            8. 最后，重新注册所有内部 BeanPostProcessor。
                ```
                if (pp instanceof MergedBeanDefinitionPostProcessor) {
                    internalPostProcessors.add(pp);
                }
                registerBeanPostProcessors(beanFactory, internalPostProcessors);
                ```
            9. beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(applicationContext));
                1. 重新注册后置处理器用于检测内部bean是不是 ApplicationListener，将其移动到处理器链的末尾(用于获取代理等)。
                2. this.applicationContext.addApplicationListener((ApplicationListener<?>) bean);
        8. initMessageSource(); 初始化此上下文的消息源。
            1. ConfigurableListableBeanFactory beanFactory = getBeanFactory();
            2. beanFactory.containsLocalBean(MESSAGE_SOURCE_BEAN_NAME) 容器中是否存在id为 messageSource 的 MessageSource。
                1. 如果有，赋值给应用程序上下文。
                    ```
                    this.messageSource = beanFactory.getBean(MESSAGE_SOURCE_BEAN_NAME, MessageSource.class);
                    ```
                2. 如果没有，自己创建一个 DelegatingMessageSource。并将其注册到容器中，以后获取国际化配置文件的值的时候，可以自动注入 MessageSource。
                    ```
                    DelegatingMessageSource dms = new DelegatingMessageSource();
                    dms.setParentMessageSource(getInternalParentMessageSource());
                    this.messageSource = dms;
                    beanFactory.registerSingleton(MESSAGE_SOURCE_BEAN_NAME, this.messageSource);
                    ```
                3. org.springframework.context.MessageSource:
                    1. 用于解析消息的策略接口，支持此类消息的参数化和国际化。
                    2. Spring为生产提供了两种开箱即用的实现:
                        1. org.springframework.context.support。ResourceBundleMessageSource，构建在标准java.util.ResourceBundle之上
                        2. org.springframework.context.support。ReloadableResourceBundleMessageSource，能够在不重启VM的情况下重新加载消息定义
        9. initApplicationEventMulticaster(); 初始化此上下文的事件多播程序。
            1. ConfigurableListableBeanFactory beanFactory = getBeanFactory();
            2. beanFactory.containsLocalBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME) 容器中是否存在id为 applicationEventMulticaster 的 ApplicationEventMulticaster。
                1. 如果有，赋值给应用程序上下文。
                    ```
                    this.applicationEventMulticaster = beanFactory.getBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, ApplicationEventMulticaster.class);
                    ```
        	    2. 如果没有，自己创建一个 SimpleApplicationEventMulticaster。并将其注册到容器中，以后可以自动注入 ApplicationEventMulticaster。
        	        ```
        	        this.applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
                    beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, this.applicationEventMulticaster);
        	        ```
        10. onRefresh(); 在特定上下文子类中初始化其他特殊bean。
            1. 模板方法，可以重写该方法以添加特定于上下文的刷新工作。在实例化单例之前，在初始化特殊bean时调用。
            2. 这个实现是空的。
        11. registerListeners(); 检查侦听器bean并注册它们。
            1. 添加实现 ApplicationListener 作为监听器的bean。不影响其他不以bean形式添加的监听器。
            2. getApplicationEventMulticaster().addApplicationListener(listener); 首先注册静态指定的监听器。
            3. String[] listenerBeanNames = getBeanNamesForType(ApplicationListener.class, true, false); 从容器中拿到所有的 ApplicationListener 的bean名称。
            4. getApplicationEventMulticaster().addApplicationListenerBean(listenerBeanName); 将每个监听器添加到事件派发器中。
            5. getApplicationEventMulticaster().multicastEvent(earlyEvent); 发布早期的应用程序事件。
        12. finishBeanFactoryInitialization(beanFactory); 实例化所有剩余的(非懒加载)单例。
            1. beanFactory.containsBean(CONVERSION_SERVICE_BEAN_NAME) && beanFactory.isTypeMatch(CONVERSION_SERVICE_BEAN_NAME, ConversionService.class) 容器中是否存在id为 conversionService 的 ConversionService 组件。
                1. 如果有，则初始化此上下文的转换服务。
                    ```
                    beanFactory.setConversionService(beanFactory.getBean(CONVERSION_SERVICE_BEAN_NAME, ConversionService.class));
                    ```
            2. 如果之前没有任何bean后处理器(例如PropertyPlaceholderConfigurer bean)注册，则注册一个默认的嵌入式值解析器:此时，主要用于在注释属性值中解析。
                ```
                beanFactory.addEmbeddedValueResolver(new StringValueResolver() {
                    @Override
                    public String resolveStringValue(String strVal) {
                        return getEnvironment().resolvePlaceholders(strVal);
                    }
                });
                ```
            3. 尽早初始化LoadTimeWeaverAware bean，以便尽早注册它们的转换器。
                1. String[] weaverAwareNames = beanFactory.getBeanNamesForType(LoadTimeWeaverAware.class, false, false);
                2. getBean(weaverAwareName);
            4. beanFactory.setTempClassLoader(null); 停止使用临时类加载器进行类型匹配。
            5. beanFactory.freezeConfiguration(); 允许缓存所有bean定义元数据，不期望有进一步的更改。
        	6. beanFactory.preInstantiateSingletons(); 实例化所有剩余的(非懒加载)单例。
        		1. List<String> beanNames = new ArrayList<String>(this.beanDefinitionNames); 获取容器中的所有bean定义。
        		2. 触发所有非懒加载单例bean的初始化。
        		3. RootBeanDefinition bd = getMergedLocalBeanDefinition(beanName); 返回合并的 RootBeanDefinition，如果指定的bean对应于子bean定义，则遍历父bean定义。
        		4. !bd.isAbstract() && bd.isSingleton() && !bd.isLazyInit() bean定义不是抽象的，是单实例的，不是懒加载的。
        			1. isFactoryBean(beanName) 判断是否是 FactoryBean；是否是实现 FactoryBean 接口的Bean；
        			2. 不是 FactoryBean。利用 getBean(beanName); 创建对象
        				1. getBean(beanName);
        				    1. 返回指定bean的一个实例，该实例可以是共享的，也可以是独立的。
                            2. 该方法允许使用Spring BeanFactory作为 Singleton 或 Prototype 设计模式的替代。在单例bean的情况下，调用者可以保留对返回对象的引用。
                            3. 将别名翻译回相应的规范bean名称。如果在此工厂实例中找不到bean将询问父工厂。
        				2. doGetBean(name, null, null, false); 返回指定bean的一个实例，该实例可以是共享的，也可以是独立的。
        				3. Object sharedInstance = getSingleton(beanName); 急切地检查单例缓存中手动注册的单例。
        				    1. 返回在给定名称下注册的(原始)单例对象。
                            2. 检查已实例化的单例，并允许对当前创建的单例的早期引用(解析循环引用)。
        				4. 缓存中获取不到，开始Bean的创建对象流程；
        				5. markBeanAsCreated(beanName);
        				    1. 将指定的bean标记为已经创建(或即将创建)。
                            2. 这允许bean工厂为重复创建指定的bean优化缓存。
        				6. final RootBeanDefinition mbd = getMergedLocalBeanDefinition(beanName); 获取Bean的定义信息；
        				7. 获取当前Bean依赖的其他Bean;如果有按照getBean()把依赖的Bean先创建出来；
        				8. 启动单实例Bean的创建流程；
                            1. createBean(beanName, mbd, args); 为给定的合并bean定义(和参数)创建bean实例。
        					2. Object bean = resolveBeforeInstantiation(beanName, mbdToUse); 让 BeanPostProcessor 有机会返回代理而不是目标bean实例。
        					    1. bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName); 
        					        1. 将 InstantiationAwareBeanPostProcessor 应用到指定的bean定义(通过类和名称)，调用它们的 postProcessBeforeInstantiation 方法。
                                    2. 任何返回的对象都将用作bean，而不是实际实例化目标bean。后置处理器返回null值将会导致实例化目标bean。
                                2. 如果后置处理器不返回null
                                    1. bean = applyBeanPostProcessorsAfterInitialization(bean, beanName); 将 BeanPostProcessor 应用于给定的现有bean实例，调用它们的 postProcessAfterInitialization 方法。返回的bean实例可能是原始bean的包装器。
        					3. 如果前面的 InstantiationAwareBeanPostProcessor 没有返回代理对象，执行步骤4。
        					4. Object beanInstance = doCreateBean(beanName, mbdToUse, args); 创建Bean
        						 1. 创建Bean实例 createBeanInstance(beanName, mbd, args);
        						    1. 利用工厂方法或者对象的构造器创建出Bean实例；
        						 2. applyMergedBeanDefinitionPostProcessors(mbd, beanType, beanName);
        						    1. 将 MergedBeanDefinitionPostProcessor 应用于指定的bean定义，调用它们的 postProcessMergedBeanDefinition() 方法。
        						 3. populateBean(beanName, mbd, instanceWrapper); 用bean定义中的属性值填充给定BeanWrapper中的bean实例。
        						    1. InstantiationAwareBeanPostProcessor.postProcessAfterInstantiation(); 给任何 InstantiationAwareBeanPostProcessor 在属性设置之前修改bean状态的机会。例如，这可以用来支持字段注入的样式。
        						    2. InstantiationAwareBeanPostProcessor.postProcessPropertyValues();
        						        1. 在工厂将给定的属性值应用于给定bean之前，对它们进行后处理。允许检查是否满足所有依赖项，例如基于bean属性设置器上的“Required”注释。
                                        2. 还允许替换要应用的属性值，通常是基于原始属性值创建一个新的MutablePropertyValues实例，添加或删除特定的值。
                                    3. applyPropertyValues(beanName, mbd, bw, pvs); 应用给定的属性值，解析此bean工厂中对其他bean的任何运行时引用。必须使用深度复制，因此我们不会永久修改此属性。
        						 4. exposedObject = initializeBean(beanName, exposedObject, mbd); 初始化给定的bean实例，应用工厂回调以及init方法和bean后处理器。为传统定义的bean从createBean调用，为现有bean实例从initializeBean调用。
        						 	1. invokeAwareMethods(beanName, bean); 执行Aware接口方法
        						 		1. BeanNameAware
        						 		2. BeanClassLoaderAware
        						 		3. BeanFactoryAware
        						 	2. wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName); 将 BeanPostProcessor 应用于给定的现有bean实例，调用它们的 postProcessBeforeInitialization 方法。返回的bean实例可能是原始bean的包装器。
        						 	3. invokeInitMethods(beanName, wrappedBean, mbd); 现在，bean的所有属性都设置好了，并有机会了解它所拥有的bean工厂(此对象)。这意味着检查bean是否实现了InitializingBean或定义了自定义init方法，如果实现了，则调用必要的回调函数。
        						 		1. 是否是 InitializingBean 接口的实现；执行接口规定的初始化；
        						 		2. 是否自定义初始化方法；
        						 	4. applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName); 将BeanPostProcessor应用于给定的现有bean实例，调用它们的 postProcessAfterInitialization 方法。返回的bean实例可能是原始bean的包装器。
        						 5. registerDisposableBeanIfNecessary(beanName, bean, mbd); 将给定bean添加到此工厂的一次性bean列表中，注册其一次性bean接口和/或在工厂关闭时调用的给定销毁方法(如果适用)。只适用于单例。
        					5. addSingleton(beanName, singletonObject); 将创建的Bean添加到缓存中singletonObjects；
        				    6. IOC容器就是这些Map；很多的Map里面保存了单实例Bean，环境信息...
        		4. 为所有适用的bean触发初始化后回调。
        		    1. singletonInstance instanceof SmartInitializingSingleton 检查所有的Bean是不是 SmartInitializingSingleton 接口的。
        		    2. 如果是，就执行 SmartInitializingSingleton.afterSingletonsInstantiated();
        13. finishRefresh(); 最后一步:发布相应的事件。
            1. 完成此上下文的刷新，调用LifecycleProcessor的onRefresh()方法并发布ContextRefreshedEvent。
        	2. initLifecycleProcessor(); 初始化此上下文的生命周期处理器。
        	    1. ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        	    2. beanFactory.containsLocalBean(LIFECYCLE_PROCESSOR_BEAN_NAME) 容器中是否存在id为 lifecycleProcessor 的 LifecycleProcessor 组件。
        	        1. 如果有，赋值给应用上下文。
        	          
                        1. this.lifecycleProcessor = beanFactory.getBean(LIFECYCLE_PROCESSOR_BEAN_NAME, LifecycleProcessor.class);
                    2. 如果没有，创建一个 DefaultLifecycleProcessor，并注册到容器中。
                        ```
                        DefaultLifecycleProcessor defaultProcessor = new DefaultLifecycleProcessor();
                        defaultProcessor.setBeanFactory(beanFactory);
                        this.lifecycleProcessor = defaultProcessor;
                        beanFactory.registerSingleton(LIFECYCLE_PROCESSOR_BEAN_NAME, this.lifecycleProcessor);
        		        ```
        		3. getLifecycleProcessor().onRefresh(); 上下文刷新通知，例如用于自动启动组件。
        		4. publishEvent(new ContextRefreshedEvent(this)); 发布容器刷新完成事件；
        		5. LiveBeansView.registerApplicationContext(this);
        14. **总结**
        	1. Spring容器在启动的时候，先会保存所有注册进来的Bean的定义信息；
        		1. xml注册bean；`<bean>`
        		2. 注解注册Bean；@Service、@Component、@Bean、xxx
        	2. Spring容器会合适的时机创建这些Bean
        		1. 用到这个bean的时候；利用getBean创建bean；创建好以后保存在容器中；
        		2. 统一创建剩下所有的bean的时候；finishBeanFactoryInitialization()；
        	3. 后置处理器；BeanPostProcessor
        		1. 每一个bean创建完成，都会使用各种后置处理器进行处理；来增强bean的功能；
                    1. AutowiredAnnotationBeanPostProcessor:处理自动注入
        			2. AnnotationAwareAspectJAutoProxyCreator:来做AOP功能；
        			3. 增强的功能注解：AsyncAnnotationBeanPostProcessor
        			4. ...
        	4. 事件驱动模型；
        		1. ApplicationListener；事件监听；
        		2. ApplicationEventMulticaster；事件派发：
3. web
    1. servlet3.0
        1. ServletContainerInitializer
        2. Registration
            1. ServletRegistration
            2. FilterRegistration
        3. ServletContext
    2. 异步请求
        1. servlet3.0异步处理
            1. 在Servlet 3.0之前，Servlet采用Thread-Per-Request的方式处理请求。
            2. 即每一次Http请求都由某一个线程从头到尾负责处理。
            3. 如果一个请求需要进行IO操作，比如访问数据库、调用第三方服务接口等，那么其所对应的线程将同步地等待IO操作完成， 而IO操作是非常慢的，所以此时的线程并不能及时地释放回线程池以供后续使用，在并发量越来越大的情况下，这将带来严重的性能问题。即便是像Spring、Struts这样的高层框架也脱离不了这样的桎梏，因为他们都是建立在Servlet之上的。为了解决这样的问题，Servlet 3.0引入了异步处理，然后在Servlet 3.1中又引入了非阻塞IO来进一步增强异步处理的性能。
        2. 返回Callable
        3. 返回DeferredResult
    3. Shared libraries（共享库） / runtimes pluggability（运行时插件能力）
        1. Servlet容器启动会扫描，当前应用里面每一个jar包的
       	    1. ServletContainerInitializer的实现
        2. 提供ServletContainerInitializer的实现类；
       	    1. 必须绑定在，META-INF/services/javax.servlet.ServletContainerInitializer
       	    2. 文件的内容就是ServletContainerInitializer实现类的全类名；
        3. 总结：容器在启动应用的时候，会扫描当前应用每一个jar包里面
            1. META-INF/services/javax.servlet.ServletContainerInitializer
            2. 指定的实现类，启动并运行这个实现类的方法；传入感兴趣的类型；
                1. ServletContainerInitializer；
                2. @HandlesTypes；
    4. SpringMVC
        1. web容器在启动的时候，会扫描每个jar包下的META-INF/services/javax.servlet.ServletContainerInitializer
        2. 加载这个文件指定的类SpringServletContainerInitializer
        3. spring的应用一启动会加载感兴趣的WebApplicationInitializer接口的下的所有组件；
        4. 并且为WebApplicationInitializer组件创建对象（组件不是接口，不是抽象类）
            1. AbstractContextLoaderInitializer：创建根容器；createRootApplicationContext()；
            2. AbstractDispatcherServletInitializer：
                1. 创建一个web的ioc容器；createServletApplicationContext();
                2. 创建了DispatcherServlet；createDispatcherServlet()；
                3. 将创建的DispatcherServlet添加到ServletContext中；
                    1. getServletMappings();
            3. AbstractAnnotationConfigDispatcherServletInitializer：注解方式配置的DispatcherServlet初始化器
                1. 创建根容器：createRootApplicationContext()
                    1. getRootConfigClasses();传入一个配置类
                2. 创建web的ioc容器： createServletApplicationContext();
                    1. 获取配置类；getServletConfigClasses();
        5. 总结：
            1. 以注解方式来启动SpringMVC；继承AbstractAnnotationConfigDispatcherServletInitializer；
            2. 实现抽象方法指定DispatcherServlet的配置信息；
        6. 定制SpringMVC；
            1. @EnableWebMvc:开启SpringMVC定制配置功能；
                1. `<mvc:annotation-driven/>`
            2. 配置组件（视图解析器、视图映射、静态资源映射、拦截器。。。）
                1. `extends WebMvcConfigurerAdapter`
