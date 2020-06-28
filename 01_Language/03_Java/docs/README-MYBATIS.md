# 好脑子不如烂笔头系列之java - MyBatis
===========================
1. 内容概要
    1. MyBatis简介
    2. MyBatis-HelloWorld
    3. MyBatis-全局配置文件
    4. MyBatis-映射文件
    5. MyBatis-动态SQL
    6. MyBatis-缓存机制
    7. MyBatis-Spring整合
    8. MyBatis-逆向工程
    9. MyBatis-工作原理
    10. MyBatis-插件开发
2. MyBatis简介
    1. MyBatis简介
        1. MyBatis 是支持定制化 SQL、存储过程以及高级映射的优秀的持久层框架。
        2. MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。
        3. MyBatis可以使用简单的XML或注解用于配置和原始映射，将接口和Java的POJO(Plain Old Java Objects，普通的Java对象)映射成数据库中的记录.
    2. MyBatis历史
        1. 原是Apache的一个开源项目iBatis, 2010年6月这个项目由Apache Software Foundation 迁移到了 Google Code，随着开发团队转投Google Code 旗下， iBatis3.x正式更名为MyBatis ，代码于 2013年11月迁移到Github(下载地址见后)。
        2. iBatis一词来源于“internet”和“abatis”的组合，是一个基于Java的持久层框架。 iBatis提供的持久层框架包括SQL Maps和Data Access Objects (DAO)
    3. 为什么要使用MyBatis?
        1. MyBatis是一个半自动化的持久化层框架。
        2. JDBC
            1. SQL夹在Java代码块里，耦合度高导致硬编码内伤
            2. 维护不易且实际开发需求中sql是有变化，频繁修改的情况多见
        3. Hibernate和JPA
            1. 长难复杂SQL，对于Hibernate而言处理也不容易
            2. 内部自动生产的SQL，不容易做特殊优化。
            3. 基于全映射的全自动框架，大量字段的POJO进行部分映射时比较困难。 导致数据库性能下降。
        4. 对开发人员而言，核心sql还是需要自己优化
        5. sql和java编码分开，功能边界清晰，一个专注业务、 一个专注数据。
    4. 去哪里找MyBatis? • https://github.com/mybatis/mybatis-3/
3. MyBatis-HelloWorld
    1. HelloWorld简单版
        1. 创建一张测试表
        2. 创建对应的javaBean
        3. 创建mybatis配置文件，sql映射文件
        4. 测试
    2. MyBatis操作数据库
        1. 创建MyBatis全局配置文件
            1. MyBatis 的全局配置文件包含了影响 MyBatis 行为甚深 的设置(settings)和属性(properties)信息、如数据 库连接池信息等。指导着MyBatis进行工作。我们可以 参照官方文件的配置示例。
        2. 创建SQL映射文件
            1. 映射文件的作用就相当于是定义Dao接口的实现类如何 工作。这也是我们使用MyBatis时编写的最多的文件。
    3. 测试
        1. 根据全局配置文件，利用 SqlSessionFactoryBuilder创建SqlSessionFactory
        2. 使用SqlSessionFactory获取sqlSession对象。一个 SqlSession对象代表和数据库的一次会话。
    4. 使用SqlSession根据方法id进行操作
    5. HelloWorld-接口式编程
        1. 创建一个Dao接口
        2. 修改Mapper文件
        3. 测试
    6. 使用SqlSession获取映射器进行操作
    7. SqlSession
        1. SqlSession 的实例不是线程安全的，因此是不能 被共享的。
        2. SqlSession每次使用完成后需要正确关闭，这个 关闭操作是必须的
        3. SqlSession可以直接调用方法的id进行数据库操 作，但是我们一般还是推荐使用SqlSession获取 到Dao接口的代理类，执行代理对象的方法，可 以更安全的进行类型检查操作
4. MyBatis-全局配置文件
    1. MyBatis的配置文件包含了影响MyBatis行为甚深的 设置(settings)和属性(properties)信息。文档的 顶层结构如下:
    2. configuration 配置
        1. properties 属性
        2. settings 设置
        3. typeAliases 类型命名
        4. typeHandlers 类型处理器
        5. objectFactory 对象工厂
        6. plugins 插件
        7. environments 环境
            1. environment 环境变量
                1. transactionManager 事务管理器
                2. dataSource 数据源
        8. databaseIdProvider 数据库厂商标识
        9. mappers 映射器
    3. 在Eclipse中引入XML的dtd约束文件，方便编写XML的时候有提示
    4. properties属性
        ```
        dbconfig.properties
        driver=com.mysql.jdbc.Driver
        url=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8
        username=root
        password=root
        
        <properties resource="dbconfig.properties"></properties>
        ```
        1. 如果属性在不只一个地方进行了配置，那么 MyBatis 将按 照下面的顺序来加载:
            1. 在 properties 元素体内指定的属性首先被读取。
            2. 然后根据 properties 元素中的 resource 属性读取类路径下属性文件或根据 url 属性指定的路径读取属性文件，并覆盖已读取的同名属性。
            3. 最后读取作为方法参数传递的属性，并覆盖已读取的同名属性。
    5. settings设置
        1. 这是 MyBatis 中极为重要的调整设置，它们会改变 MyBatis 的运行时行为。
            ```
            设置参数                    描述                                                 有效值                   默认值
            cacheEnabled               该配置影响的所有映射器中配置的缓存的全局开关               true | false            TRUE
            lazyLoadingEnabled         延迟加载的全局开关。当开启时，所有关联对象都会延迟加载。     true | false            FALSE
                                       特定关联关系中可通过 fetchType 属性来覆盖该项的开关状态。
            useColumnLabel             使用列标签代替列名。不同的驱动在这方面会有不同的表现，       true | false            TRUE
                                       具体可参考相关驱动文档或通过测试这两种不同的模式来观察
                                       所用驱动的结果。
            defaultStatementTimeout    设置超时时间，它决定驱动等待数据库响应的秒数。              Any positive integer    Not Set(null)
            mapUnderscoreToCamelCase   是否开启自动驼峰命名规则(camel case)映射，即从经典数据库    true | false            FALSE
                                       列名 A_COLUMN 到经典 Java 属性名 aColumn 的类似映射
            ```
            ```
            <settings>
                <setting name="mapUnderscoreToCamelCase" value="true"/>
            </settings>
            ```
    6. typeAliases别名处理器
        1. 类型别名是为 Java 类型设置一个短的名字，可以方便我们 引用某个类。
            ```
            <typeAliases>
                <typeAlias type="com.onevgo.bean.Employee" alias="employee"></typeAlias>
                <typeAlias type="com.onevgo.bean.Department" alias="department"></typeAlias>
            </typeAliases>
            ```
        2. 类很多的情况下，可以批量设置别名这个包下的每一个类 创建一个默认的别名，就是简单类名小写。
            ```
            <typeAliases>
                <package name="com.onevgo.bean"></package>
            </typeAliases>
            ```
        3. 也可以使用@Alias注解为其指定一个别名
            ```
            @Alias("emp")
            public class Employee {...}
            ```
        4. 值得注意的是，MyBatis已经为许多常见的 Java 类型内建了相应的类型别名。它们都是大小写不敏感的，我们在起别名的时候千万不要占用已有的别名。
            ```
            别名      映射的类型
            _byte       byte
            _long       long
            _short      short
            _int        int
            _integer    int
            _double     double
            _float      float
            _boolean    boolean
            string      String
            byte        Byte
            long        Long
            short       Short
            int         Integer
            integer     Integer
            double      Double
            float       Float
            boolean     Boolean
            date        Date
            decimal     BigDecimal
            bigdecimal  BigDecimal
            object      Object
            map         Map
            hashmap     HashMap
            list        List
            arraylist   ArrayList
            collection  Collection
            iterator    Iterator
            ```
    7. typeHandlers类型处理器
        1. 无论是 MyBatis 在预处理语句(PreparedStatement)中 设置一个参数时，还是从结果集中取出一个值时， 都会 用类型处理器将获取的值以合适的方式转换成 Java 类型。
            ```
            类型处理器               Java 类型                        JDBC 类型
            BooleanTypeHandler      java.lang.Boolean, boolean      数据库兼容的 BOOLEAN
            ByteTypeHandler         java.lang.Byte, byte            数据库兼容的 NUMERIC 或 BYTE
            ShortTypeHandler        java.lang.Short, short          数据库兼容的 NUMERIC 或 SHORT INTEGER
            IntegerTypeHandler      java.lang.Integer, int          数据库兼容的 NUMERIC 或 INTEGER
            LongTypeHandler         java.lang.Long, long            数据库兼容的 NUMERIC 或 LONG INTEGER
            FloatTypeHandler        java.lang.Float, float          数据库兼容的 NUMERIC 或 FLOAT
            DoubleTypeHandler       java.lang.Double, double        数据库兼容的 NUMERIC 或 DOUBLE
            BigDecimalTypeHandler   java.math.BigDecimal            数据库兼容的 NUMERIC 或 DECIMAL
            StringTypeHandler       java.lang.String                CHAR, VARCHAR
            ```
    8. 日期类型的处理
        1. 日期和时间的处理，JDK1.8以前一直是个头疼的 问题。我们通常使用JSR310规范领导者Stephen Colebourne创建的Joda-Time来操作。1.8已经实 现全部的JSR310规范了。
        2. 日期时间处理上，我们可以使用MyBatis基于 JSR310(Date and Time API)编写的各种日期 时间类型处理器。
        3. MyBatis3.4以前的版本需要我们手动注册这些处 理器，以后的版本都是自动注册的
            ```
            <typeHandlers>
                <typeHandler handler="org.apache.ibatis.type.InstantTypeHandler"></typeHandler>
                <typeHandler handler="org.apache.ibatis.type.LocalDateTimeTypeHandler"></typeHandler>
                <typeHandler handler="org.apache.ibatis.type.LocalDateTypeHandler"></typeHandler>
                <typeHandler handler="org.apache.ibatis.type.LocalTimeTypeHandler"></typeHandler>
                <typeHandler handler="org.apache.ibatis.type.OffsetDateTimeTypeHandler"></typeHandler>
                <typeHandler handler="org.apache.ibatis.type.OffsetTimeTypeHandler"></typeHandler>
                <typeHandler handler="org.apache.ibatis.type.ZonedDateTimeTypeHandler"></typeHandler>
                <typeHandler handler="org.apache.ibatis.type.YearTypeHandler"></typeHandler>
                <typeHandler handler="org.apache.ibatis.type.MonthTypeHandler"></typeHandler>
                <typeHandler handler="org.apache.ibatis.type.YearMonthTypeHandler"></typeHandler>
                <typeHandler handler="org.apache.ibatis.type.JapaneseDateTypeHandler"></typeHandler>
            </typeHandlers>
            ```
    9. 自定义类型处理器
        1. 我们可以重写类型处理器或创建自己的类型处理 器来处理不支持的或非标准的类型。
        2. 步骤:
            1. 实现org.apache.ibatis.type.TypeHandler接口或者继承org.apache.ibatis.type.BaseTypeHandler
            2. 指定其映射某个JDBC类型(可选操作)
            3. 在mybatis全局配置文件中注册
    10. plugins插件
        1. 插件是MyBatis提供的一个非常强大的机制，我们 可以通过插件来修改MyBatis的一些核心行为。插 件通过动态代理机制，可以介入四大对象的任何 一个方法的执行。后面会有专门的章节我们来介 绍mybatis运行原理以及插件
        2. Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
        3. ParameterHandler (getParameterObject, setParameters)
        4. ResultSetHandler (handleResultSets, handleOutputParameters)
        5. StatementHandler (prepare, parameterize, batch, update, query)
    11. environments环境
        1. MyBatis可以配置多种环境，比如开发、测试和生 产环境需要有不同的配置。
        2. 每种环境使用一个environment标签进行配置并指 定唯一标识符
        3. 可以通过environments标签中的default属性指定 一个环境的标识符来快速的切换环境
        4. environment-指定具体环境
            1. id:指定当前环境的唯一标识
            2. transactionManager、和dataSource都必须有
                ```
                <environments default="development">
                    <environment id="test">
                        <transactionManager type="JDBC"></transactionManager>
                        <dataSource type="POOLED">
                            <property name="driver" value="${jdbc.driverClass}"/>
                            <property name="url" value="${jdbc.jdbcUrl}"/>
                            <property name="username" value="${jdbc.user}"/>
                            <property name="password" value="${jdbc.password}"/>
                        </dataSource>
                    </environment>
                    
                    <environment id="development">
                        <transactionManager type="JDBC"/>
                        <dataSource type="POOLED">
                            <property name="driver" value="${jdbc.driverClass}"/>
                            <property name="url" value="${jdbc.jdbcUrl}"/>
                            <property name="username" value="${jdbc.user}"/>
                            <property name="password" value="${jdbc.password}"/>
                        </dataSource>
                    </environment>
                </environments>
                ```
            3. transactionManager
                1. type: JDBC | MANAGED | 自定义
                    1. JDBC:使用了 JDBC 的提交和回滚设置，依赖于从数 据源得到的连接来管理事务范围。 JdbcTransactionFactory
                    2. MANAGED:不提交或回滚一个连接、让容器来管理 事务的整个生命周期(比如 JEE 应用服务器的上下 文)。 ManagedTransactionFactory
                    3. 自定义:实现TransactionFactory接口，type=全类名/ 别名
            4. dataSource
                1. type: UNPOOLED | POOLED | JNDI | 自定义
                2. UNPOOLED:不使用连接池， UnpooledDataSourceFactory
                3. POOLED:使用连接池， PooledDataSourceFactory
                4. JNDI: 在EJB 或应用服务器这类容器中查找指定的数据源
                5. 自定义:实现DataSourceFactory接口，定义数据源的 获取方式。
            5. 实际开发中我们使用Spring管理数据源，并进行 事务控制的配置来覆盖上述配置
    12. databaseIdProvider环境
        1. MyBatis 可以根据不同的数据库厂商执行不同的语句。
            ```
            <databaseIdProvider type="DB_VENDOR">
                <!-- 为不同的数据库厂商起别名 -->
                <property name="MySQL" value="mysql"></property>
                <property name="Oracle" value="oracle"></property>
                <property name="SQL Server" value="sqlserver"></property>
            </databaseIdProvider>
            ```
        2. Type: DB_VENDOR
            1. 使用MyBatis提供的VendorDatabaseIdProvider解析数据库厂商标识。也可以实现DatabaseIdProvider接口来自定义。
            2. Property-name:数据库厂商标识
            3. Property-value:为标识起一个别名，方便SQL语句使用 databaseId属性引用
                ```
                <select id="getEmpById" resultType="com.onevgo.mybatis.bean.Employee" databaseId="mysql">
                    select id, last_name lastName, email, gender from tbl_employee where id = #{id}
                </select>
                ```
            4. DB_VENDOR
                1. 会通过DatabaseMetaData#getDatabaseProductName()返回的字符 串进行设置。由于通常情况下这个字符串都非常长而且相同产品的不 同版本会返回不同的值，所以最好通过设置属性别名来使其变短
            5. MyBatis匹配规则如下:
                1. 如果没有配置databaseIdProvider标签，那么databaseId=null
                2. 如果配置了databaseIdProvider标签，使用标签配置的name去匹 配数据库信息，匹配上设置databaseId=配置指定的值，否则依旧为 null
                3. 如果databaseId不为null，他只会找到配置databaseId的sql语句
                4. MyBatis 会加载不带 databaseId 属性和带有匹配当前数据库 databaseId 属性的所有语句。如果同时找到带有 databaseId 和不带 databaseId 的相同语句，则后者会被舍弃。
    13. mapper映射
        1. mapper逐个注册SQL映射文件
            ```
            <mappers>
                <mapper resource="com/onevgo/mybatis/mapper/EmployeeMapper.xml"/>
                <mapper class="com.onevgo.mybatis.dao.EmployeeMapper"></mapper>
                <mapper class="com.onevgo.mybatis.dao.EmployeeMapperAnnotation"></mapper>
            </mappers>
            ```
        2. 或者使用批量注册:
            1. 这种方式要求SQL映射文件名必须和接口名相同并且在同一目录下
                ```
                <mappers>
                    <!-- 批量注册 -->
                    <package name="com.onevgo.mybatis.dao"></package>
                </mappers>
                ```
5. MyBatis-映射文件
    1. 映射文件指导着MyBatis如何进行数据库增删改查， 有着非常重要的意义;
        1. cache –命名空间的二级缓存配置
        2. cache-ref – 其他命名空间缓存配置的引用。
        3. resultMap – 自定义结果集映射
        4. parameterMap – 已废弃!老式风格的参数映射
        5. sql –抽取可重用语句块。
        6. insert – 映射插入语句
        7. update – 映射更新语句
        8. delete – 映射删除语句
        9. select – 映射查询语句
    2. insert、update、delete元素
        ```
        id                  命名空间中的唯一标识符
        parameterType       将要传入语句的参数完全限定类名或别名。这个属性是可选，因为 MyBatis 可以通过 TypeHandler 推断出具体传入语句的参数类型，默认值为 unset。
        flushCache          将其设置为 true，任何时候只要语句被调用，都会导致本地缓存和二级缓存都会被清空，默认值：true（对应插入，更新和删除语句）
        timeout             这个设置是在抛出异常之前，驱动程序等待数据库返回请求结果的秒数，默认值为 unset（依赖驱动）
        statementType       STATEMENT，PREPARED 或 CALLABLE 的一个。这会让 MyBatis 分别使用 Statement，PreparedStatement 或 CallableStatement，默认值：PREPARED
        useGeneratedKeys    （仅对 insert 和 update 有用）这会令 MyBatis 使用 JDBC 的 getGeneratedKeys 方法来取出由数据库内部生成的主键（比如：像 MySQL 和 SQL Server 这样的关系数据库管理系统的自动递增字段），默认值：false
        keyProperty         （仅对 insert 和 update 有用）唯一标记一个属性，MyBatis 会通过 getGeneratedKeys 的返回值或者通过 insert 语句的 selectKey 子元素设置它的键值，默认：unset
        keyColumn           （仅对 insert 和 update 有用）通过生成的键值设置表中的列名，这个设置仅在某些数据库（像 PostgreSQL）是必须的，当主键列不是表中的第一列的时候需要设置。如果希望得到多个生成的列，也可以是逗号分隔的属性名称列表。
        databaseId          如果配置了 databaseIdProvider，MyBatis 会加载所有的不带 databaseId 或匹配当前 databaseId 的语句；如果带或者不带的语句都有，则不带的会被忽略。
        ```
    3. 主键生成方式
        1. 若数据库支持自动生成主键的字段(比如MySQL 和 SQL Server)，则可以设置 useGeneratedKeys=”true”，然后再把 keyProperty 设置到目标属性上。
            ```
            <insert id="insertCustomer" databaseId="mysql" useGeneratedKeys="true" keyProperty="id">
                INSERT INTO customer(last_name, email, age) VALUES (#{lastName}, #{email}, #{age})
            </insert>
            ```
        2. 而对于不支持自增型主键的数据库(例如 Oracle)，则可以使用 selectKey 子元素: selectKey 元素将会首先运行，id 会被设置，然 后插入语句会被调用
            ```
            <insert id="insertCustomer" databaseId="oracle" parameterType="customer">
                <selectKey order="BEFORE">
                    SELECT crm_seq.nextval FROM dual
                </selectKey>
                INSERT INTO customers2 (id, last_name, email, age) VALUES (#{id}, #{lastName}, #{email}, #{age})
            </insert>
            ```
        3. selectKey
            ```
            keyProperty     selectKey 语句结果应该被设置的目标属性。
            keyColumn       匹配属性的返回结果集中的列名称。
            resultType      结果的类型。MyBatis 通常可以推算出来，但是为了更加确定写上也不会有什么问题。MyBatis允许任何简单类型用作主键的类型，包括字符串。
            order           可以被设置为 BEFORE 或 AFTER。如果设置为 BEFORE，那么它会首先选择主键，设置 keyProperty 然后执行插入语句。如果设置为 AFTER，那么先执行插入语句，然后是 selectKey 元素
            statementType   与前面相同，MyBatis 支持 STATEMENT，PREPARED 和 CALLABLE 语句的映射类型，分别代表 PreparedStatement 和 CallableStatement 类型
            ```
    3. 参数(Parameters)传递
        1. 单个参数
            1. 可以接受基本类型，对象类型，集合类型的值。这种情况MyBatis可直接使用这个参数，不需要经过任何处理。
        2. 多个参数
            1. 任意多个参数，都会被MyBatis重新包装成一个Map传入。Map的key是param1，param2，0，1...，值就是参数的值。
        3. 命名参数
            1. 为参数使用@Param起一个名字，MyBatis就会将这些参数封装进map中，key就是我们自己指定的名字
        4. POJO
            1. 当这些参数属于我们业务POJO时，我们直接传递POJO
        5. Map
            1. 我们也可以封装多个参数为map，直接传递
    4. 参数处理
        1. 参数也可以指定一个特殊的数据类型:
            ```
            #{property, javaType=int, jdbcType=NUMBERIC}
            #{height, javaType=double, jdbcType=NUMBERIC}
            ```
        2. javaType 通常可以从参数对象中来去确定
        3. 如果 null 被当作值来传递，对于所有可能为空的列， jdbcType 需要被设置
        4. 对于数值类型，还可以设置小数点后保留的位数:
        5. mode 属性允许指定 IN，OUT 或 INOUT 参数。如果参数 为 OUT 或 INOUT，参数对象属性的真实值将会被改变， 就像在获取输出参数时所期望的那样。
    5. 参数处理
        1. 参数位置支持的属性
            1. javaType、jdbcType、mode、numericScale、resultMap、typeHandler、jdbcTypeName、expression
        2. 实际上通常被设置的是: 可能为空的列名指定 jdbcType
        3. #{key}:获取参数的值，预编译到SQL中。安全。
        4. ${key}:获取参数的值，拼接到SQL中。有SQL注入问题。ORDER BY ${name}
    6. select元素
        1. select元素来定义查询操作。
        2. id:唯一标识符。
            1. 用来引用这条语句，需要和接口的方法名一致
        3. parameterType:参数类型。
            1. 可以不传，MyBatis会根据TypeHandler自动推断
        4. resultType:返回值类型。
            1. 别名或者全类名，如果返回的是集合，定义集合中元素的类型。不能和resultMap同时使用
            ```
            parameterType   将会传入这条语句的参数类的完全限定名或别名。这个属性是可选的，因为 MyBatis 可以通过 TypeHandler 推断出具体传入语句的参数，默认值为 unset。
            resultType      从这条语句中返回的期望类型的类的完全限定名或别名。注意如果是集合，那应该是集合可以包含的类型，而不能是集合本身。该属性和 resultMap，不能同时使用。
            resultMap       外部 resultMap 的命名引用。和 resultType 属性不能同时使用。
            flushCache      将其设置为 true，任何时候只要语句被调用，都会导致本地缓存和二级缓存都会被清空，默认值：false
            useCache        将其设置为 true，将会导致本条语句的结果被二级缓存，默认值：对 select 元素为 true。
            timeout         这个设置是在抛出异常之前，驱动程序等待数据库返回请求结果的秒数。默认值为 unset（依赖驱动）。
            fetchSize       影响驱动程序每次批量返回的结果行数。默认值为 unset（依赖驱动）。
            statementType   STATEMENT，PREPARED 或 CALLABLE 的一个。这会让 MyBatis 分别使用 Statement，PreparedStatement 或 CallableStatement，默认值：PREPARED
            resultSetType   FOREARD_ONLY，SCROLL_SENSITIVE 或 SCROLL_INSENSITIVE 中的一个，默认值为 unset（依赖驱动）
            databaseId      如果匹配了 databaseIdProvider，MyBatis 会加载所有的不带 databaseId 或匹配当前 databaseID 的语句；如果带或者不带的语句都有，则不带的会被忽略。
            resultOrdered   这个设置仅针对嵌套结果 select 语句适用：如果为 true，就假设包含了嵌套结果集或是分组，这样就返回一个主结果行，就不会发生有对前面结果集引用的情况。这就使得在获取嵌套的结果集的时候不至于导致内存不够用。默认值：false
            resultSets      这个设置仅对多结果集的情况适用，他将列出语句执行后返回的结果集并每个结果集给一个名称，名称是逗号分隔的
            ```
    7. 自动映射
        1. 全局setting设置
            1. autoMappingBehavior默认是PARTIAL，开启自动映射的功能。唯一的要求是列名和javaBean属性名一致
            2. 如果autoMappingBehavior设置为null则会取消自动映射
            3. 数据库字段命名规范，POJO属性符合驼峰命名法，如 A_COLUMN->aColumn，我们可以开启自动驼峰命名规则映射功能，mapUnderscoreToCamelCase=true。
        2. 自定义resultMap，实现高级结果集映射。
    8. resultMap
        1. constructor - 类在实例化时, 用来注入结果到构造方法中
            1. idArg - ID 参数; 标记结果作为 ID 可以帮助提高整体效能
            2. arg - 注入到构造方法的一个普通结果
        2. id – 一个 ID 结果; 标记结果作为 ID 可以帮助提高整体效能
        3. result – 注入到字段或 JavaBean 属性的普通结果
        4. association – 一个复杂的类型关联;许多结果将包成这种类型 – 嵌入结果映射 – 结果映射自身的关联,或者参考一个
        5. collection – 复杂类型的集
            1. 嵌入结果映射 – 结果映射自身的集,或者参考一个
        6. discriminator – 使用结果值来决定使用哪个结果映射
            1. case – 基于某些值的结果映射
                1. 嵌入结果映射–这种情形结果也映射它本身,因此可以包含很多相同的元 素,或者它可以参照一个外部的结果映射。
        7. id & result
            1. id和result映射一个单独列的值到简单数据类型 (字符串,整型,双精度浮点数,日期等)的属性或字段。
                ```
                property    映射到列结果的字段或属性。例如："username" 或 "address.street.number"
                column      数据表的列名。通常和 resultSet.getString(columnName) 的返回值一致
                javaType    一个 Java 类的完全限定名，或一个类型别名。如果映射到一个 JavaBean，MyBatis 通常可以判断类型
                jdbcType    JDBC 类型是仅仅需要对插入，更新和删除操作可能为空的列进行处理。
                typeHandler 类型处理器。使用这个属性，可以覆盖默认的类型处理器。这个属性值是类的完全限定名或者是一个类型处理器的实现，或者是类型别名。
                ```
        8. association
            1. 复杂对象映射
            2. POJO中的属性可能会是一个对象
            3. 我们可以使用联合查询，并以级联属性的方式封装对象。
                ```
                <!-- select e.id,e.last_name,e.dept_id,d.name as dept_name from employee e left join department d on d.id=e.dept_id where e.id=#{id} -->
                <resultMap type="com.onvgo.mybatis.bean.Employee" id="myEmployee">
                    <id column="id" property="id"></id>
                    <result column="last_name" property="lastName"></result>
                    <result column="dept_id" property="department.id"></result>
                    <result column="dept_name" property="department.name"></result>
                </resultMap>
                ```
            4. 使用association标签定义对象的封装规则
        9. association-嵌套结果集
            ```
            <!-- select e.id,e.last_name,e.dept_id,d.name as dept_name from employee e left join department d on d.id=e.dept_id where d.id=#{id} -->
            <resultMap type="com.onevgo.mybatis.bean.Employee" id="myEmployee2">
                <id column="id" property="id"></id>
                <result column="last_name" property="lastName"></result>
                <association property="department" javaType="com.onevgo.mybatis.bean.Department">
                    <id column="dept_id" property="id"></id>
                    <result column="dept_name" property="name"></column>
                </association>
            </resultMap>
            ```
        10. association-分段查询
            ```
            <!--
                1. select id,last_name,dept_id from employee where id=#{id}
                2. select id,name from department where id=#{$1.dept_id}
            -->
            <resultMap type="com.onevgo.mybatis.bean.Employee" id="myEmployee3">
                <id column="id" property="id"></id>
                <result column="last_name" property="lastName"></result>
                <association property="department" select="com.onevgo.mybatis.bean.DepartmentMapper.getById" 
                    column="dept_id"></association>
            </resultMap>
            ```
            1. select:调用目标的方法查询当前属性的值
            2. column:将指定列的值传入目标方法
        11. association-分段查询&延迟加载
            1. 开启延迟加载和属性按需加载
                ```
                <settings>
                    <setting name="lazyLoadingEnabled" value="true"></setting>
                    <setting name="aggressiveLazyLoading" value="false"></setting>
                </settings>
                ```
            2. 旧版本的MyBatis需要额外的支持包
                1. asm-3.3.1.jar
                2. cglib-2.2.2.jar
        12. Collection-集合类型&嵌套结果集
            ```
            <!-- select d.id,d.name,e.id as e_id,e.last_name,e.gender,e.email from department d left join employee e on e.dept_id=d.id where d.id=#{id} -->
            <resultMap type="com.onevgo.mybatis.bean.Department" id="myDept">
                <id column="id" property="id"></id>
                <result column="name" property="name"></result>
                <collection property="emps" ofType="com.onevgo.mybatis.bean.Employee">
                    <id column="e_id" property="id"></id>
                    <result column="last_name" property="lastName"></result>
                    <result column="gender" property="gender"></property>
                    <result column="email" property="email"></property>
                </collection>
            </resultMap>
            ```
        13. Collection-分步查询&延迟加载
            ```
            <!--
                1. select * from department where id=#{id}
                2. select * from employee where dept_id=#{$1.id}
            -->
            <resultMap type="com.onevgo.mybatis.bean.Department" id="myDept2">
                <id column="id" property="id"></id>
                <result column="name" property="name"></result>
                <collection property="emps" select="com.onevgo.mybatis.bean.Employee.getById" column="id"></collection>
            </resultMap>
            ```
        14. 扩展-多列值封装map传递
            1. 分步查询的时候通过column指定，将对应的列的数据传递过去，我们有时需要传递多列数据。
            2. 使用{key1=column1,key2=column2...}的形式
                ```
                <!--
                    1. select * from department where id=#{id}
                    2. select * from employee where dept_id=#{deptId=$1.id}
                -->
                <resultMap type="com.onevgo.mybatis.bean.Department" id="myDept2">
                    <id column="id" property="id"></id>
                    <result column="name" property="name"></result>
                    <collection property="emps" select="com.onevgo.mybatis.bean.Employee.getById" column="{deptId=id}"></collection>
                </resultMap>
                ```
            3. association或者collection标签的 fetchType=eager/lazy可以覆盖全局的延迟加载策略， 指定立即加载(eager)或者延迟加载(lazy)
6. MyBatis-动态SQL
    1. 动态 SQL是MyBatis强大特性之一。极大的简化我们拼装 SQL的操作。
    2. 动态 SQL 元素和使用 JSTL 或其他类似基于 XML 的文本处 理器相似。
    3. MyBatis 采用功能强大的基于 OGNL 的表达式来简化操作。
        1. if
        2. choose (when, otherwise)
        3. trim (where, set)
        4. foreach
    4. if
        ```
        <select id="getEmpWhereIf" resultType="com.onevgo.mybatis.bean.Employee">
            select * from employee where 1=1
            <if test="id != null">
                and id = #{id}
            </if>
            <if test="lastName != null and lastName != ''">
                and last_name like #{lastName}
            </if>
            <if test="gender == 0 and gender == 1">
                and gender = #{gender}
            </if>
        </select>
        ```
    5. choose (when, otherwise)
        ```
        <select id="getEmpChoose" resultType="com.onevgo.mybatis.bean.Employee">
            select * from employee
            <where>
                <choose>
                    <when test="id != null">
                        id = #{id}
                    </when>
                    <when test="lastName != null and lastName !=''">
                        last_name = #{lastName}
                    </when>
                    <otherwise>
                        1 = 1
                    </otherwise>
                </choose>
            </where>
        </select>
        ```
    6. trim (where, set)
        1. where
            ```
            <select id="getEmpWhereIf" resultType="com.onevgo.mybatis.bean.Employee">
                select * from employee
                <where>
                    <if test="id != null">
                        id = #{id}
                    </if>
                    <if test="lastName != null and lastName !=''">
                        and last_name = #{lastName}
                    </if>
                    <if test="gender == 0 or gender == 1">
                        and gender = #{gender}
                    </if>
                </where>
            </select>
            ```
        2. set
            ```
            <update id="updateEmployee" parameterType="com.onevgo.mybatis.bean.Employee">
                update employee
                <set>
                    <if test="lastName != null">
                        last_name = #{lastName},
                    </if>
                    <if test="gender != null and (gender == 0 or gender == 1)">
                        gender = #{gender},
                    </if>
                    <if test="email != null">
                        email = #{email}
                    </if>
                </set>
                where id = #{id}
            </update>
            ```
        3. trim
            ```
            <select id="getEmpWhereTrim" resultType="com.onevgo.mybatis.bean.Employee">
                select * from employee
                <trim prefix="where" suffixOverrides="and">
                    <if test="id != null">
                        id = #{id} and
                    </if>
                    <if test="lastName != null and lastName != ''">
                        last_name = #{lastName} and
                    </if>
                    <if test="gender == 0 or gender == 1">
                        gender = #{gender}
                    </if>
                </trim>
            </select>
            ```
    7. foreach
        1. 动态 SQL 的另外一个常用的必要操作是需要对一个集合 进行遍历，通常是在构建 IN 条件语句的时候。
            ```
            <select id="getEmpByIdIn" resultType="com.onevgo.mybatis.bean.Employee">
                select * from employee where id in
                <foreach collection="ids" item="item_id" open="(" separator="," close=")">
                    #{item_id}
                </foreach>
            </select>
            ```
        2. 当迭代列表、集合等可迭代对象或者数组时
            1. index是当前迭代的次数，item的值是本次迭代获取的元素
        3. 当使用字典(或者Map.Entry对象的集合)时
            1. index是键，item是值
    8. bind
        1. bind 元素可以从 OGNL 表达式中创建一个变量并将其绑定到上下文。比如:
            ```
            <select id="getEmpByLastNameLike" resultType="com.onevgo.mybatis.bean.Employee">
                <bind name="_lastName" value="'%' + lastName + '%'"></bind>
                select * from employee where last_name like #{_lastName}
            </select>
            ```
    9. Multi-db vendor support
        1. 若在 mybatis 配置文件中配置了 databaseIdProvider , 则可以使用 “_databaseId”变量，这样就可以根据不同的数据库厂商构建特定的语句
            ```
            <databaseIdProvider type="DB_VENDOR">
                <property name="Mysql" value="mysql"/>
                <property name="Oracle" value="oracle"/>
            </databaseIdProvider>
            
            <select id="getEmpPage" resultType="com.onevgo.mybatis.bean.Employee">
                <if test="_databaseId == 'mysql'">
                    select * from employee where limit 0,5
                </if>
                <if test="_databaseId == 'oracle'">
                    select * from (select e.*, rownum as r1 from employee e where rownum lte 5)
                    where r1 gte 1
                </if>
            </select>
            ```
    10. OGNL: [http://commons.apache.org/proper/commons-ognl/language-guide.html]
        1. OGNL( Object Graph Navigation Language )对象图导航语言，这是一种强大的 表达式语言，通过它可以非常方便的来操作对象属性。 类似于我们的EL，SpEL等
        2. 访问对象属性:      person.name
        3. 调用方法:         person.getName()
        4. 调用静态属性/方法: @java.lang.Math@PI @java.util.UUID@randomUUID()
        5. 调用构造方法:      new com.onevgo.mybatis.bean.Person('admin').name
        6. 运算符:           +,-,*,/,%
        7. 逻辑运算符:       in,not in,>,>=,<,<=,==,!=
            1. 注意：xml中特殊字符如",>,<等这些都需要使用转义字符
        8. 访问集合伪属性:
            ```
            类型              伪属性             伪属性对应的 Java 方法
            List、Set、Map    size、isEmpty      List/Set/Map.size(),List/Set/Map.isEmpty()
            List、Set        iterator           List.iterator()、Set.iterator()
            Map              keys、values       Map.keySet()、Map.values()
            Iterator         next、hasNext      Iterator.next()、Iterator.hasNext()
            ```
7. MyBatis-缓存机制
    1. MyBatis包含一个非常强大的查询缓存特性,它可以非 常方便地配置和定制。缓存可以极大的提升查询效率。
    2. MyBatis系统中默认定义了两级缓存。
    3. 一级缓存和二级缓存。
        1. 默认情况下，只有一级缓存(SqlSession级别的缓存， 也称为本地缓存)开启。
        2. 二级缓存需要手动开启和配置，他是基于namespace级 别的缓存。
        3. 为了提高扩展性。MyBatis定义了缓存接口Cache。我们 可以通过实现Cache接口来自定义二级缓存
    4. 一级缓存
        1. 一级缓存(local cache), 即本地缓存, 作用域默认 为sqlSession。当 Session flush 或 close 后, 该 Session 中的所有 Cache 将被清空。
        2. 本地缓存不能被关闭, 但可以调用 clearCache() 来清空本地缓存, 或者改变缓存的作用域.
        3. 在mybatis3.1之后,可以配置本地缓存的作用域. 在 mybatis.xml 中配置
            ```
            localCacheScope     MyBatis 利用本地缓存机制（Local Cache）防止循环引用（circular references）和加速重复嵌套查询。  SESSION | STATEMENT     SESSION
                                默认值为 SESSION，这种情况下会缓存一个会话中执行的所有查询。
                                若设置为 STATEMENT，本地会话仅用在语句执行上，对相同 SqlSession 的不同调用将不会共享数据。
            ```
    5. 一级缓存演示&失效情况
        1. 同一次会话期间只要查询过的数据都会保存在当 前SqlSession的一个Map中
            1. key:hashCode+查询的SqlId+编写的sql查询语句+参数
        2. 一级缓存失效的四种情况
            1. 不同的SqlSession对应不同的一级缓存
            2. 同一个SqlSession但是查询条件不同
            3. 同一个SqlSession两次查询期间执行了任何一次增 删改操作
            4. 同一个SqlSession两次查询期间手动清空了缓存
    6. 二级缓存
        1. 二级缓存(second level cache)，全局作用域缓存
        2. 二级缓存默认不开启，需要手动配置
        3. MyBatis提供二级缓存的接口以及实现，缓存实现要求 POJO实现Serializable接口
        4. 二级缓存在SqlSession关闭或提交之后才会生效
        5. 使用步骤
            1. 全局配置文件中开启二级缓存
                1. `<setting name="cacheEnabled" value="true"/>`
            2. 需要使用二级缓存的映射文件处使用cache配置缓存
                1. `<cache />`
            3. 注意:POJO需要实现Serializable接口
    7. 缓存相关属性
        1. eviction=“FIFO”:缓存回收策略:
            1. LRU – 最近最少使用的:移除最长时间不被使用的对象。
            2. FIFO – 先进先出:按对象进入缓存的顺序来移除它们。
            3. SOFT – 软引用:移除基于垃圾回收器状态和软引用规则的对象。
            4. WEAK – 弱引用:更积极地移除基于垃圾收集器状态和弱引用规则的对象。
            5. 默认的是 LRU。
        2. flushInterval:刷新间隔，单位毫秒
            1. 默认情况是不设置，也就是没有刷新间隔，缓存仅仅调用语句时刷新
        3. size:引用数目，正整数
            1. 代表缓存最多可以存储多少个对象，太大容易导致内存溢出
        4. readOnly:只读，true/false
            1. true:只读缓存;会给所有调用者返回缓存对象的相同实例。因此这些对象不能被修改。这提供了很重要的性能优势。
            2. false:读写缓存;会返回缓存对象的拷贝(通过序列化)。这会慢一些， 但是安全，因此默认是 false。
    8. 缓存有关设置
        1. 全局setting的cacheEnable:
            1. 配置二级缓存的开关。一级缓存一直是打开的。
        2. select标签的useCache属性:
            1. 配置这个select是否使用二级缓存。一级缓存一直是使用的
        3. sql标签的flushCache属性:
            1. 增删改默认flushCache=true。sql执行以后，会同时清空一级和二级缓存。查询默认flushCache=false。
        4. sqlSession.clearCache():
            1. 只是用来清除一级缓存。
        5. 当在某一个作用域 (一级缓存Session/二级缓存 Namespaces) 进行了 C/U/D 操作后，默认该作用域下所 有 select 中的缓存将被clear。
    9. 第三方缓存整合
        1. EhCache是一个纯Java的进程内缓存框架，具有快速、精 干等特点，是Hibernate中默认的CacheProvider。
        2. MyBatis定义了Cache接口方便我们进行自定义扩展。 • 步骤:
            1. 导入ehcache包，以及整合包，日志包
                1. ehcache-core-2.6.8.jar
                2. mybatis-ehcache-1.0.3.jar
                3. slf4j-api-1.6.1.jar
                4. slf4j-log4j12-1.6.2.jar
            2. 编写ehcache.xml配置文件
            3. 配置cache标签
                1. `<cache type="org.mybatis.caches.ehcache.EhcacheCache"></cache>`
        3. 参照缓存:若想在命名空间中共享相同的缓存配置和实例。 可以使用 cache-ref 元素来引用另外一个缓存。
        4. 当执行一条查询SQL时，流程为
            1. 从二级缓存中进行查询
            2. 进入一级缓存中查询
            3. 执行 JDBC 查询。
8. MyBatis-Spring整合
    1. 查看不同MyBatis版本整合Spring时使用的适配包;
        1. http://www.mybatis.org/spring/
    2. 下载整合适配包
        1. https://github.com/mybatis/spring/releases
            ```
            MyBatis-Spring      MyBatis             Spring
            1.0.0 and 1.0.1     3.0.1 to 3.0.5      3.0.0 or higher
            1.0.2               3.0.6               3.0.0 or higher
            1.1.0 or higher     3.1.0 or higher     3.0.0 or higher
            1.3.0 or higher     3.4.0 or higher     3.0.0 or higher
            ```
    3. 官方整合示例，jpetstore
        1. https://github.com/mybatis/jpetstore-6
    4. 整合关键配置
        ```
        <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
            <!-- 指定mybatis全局配置文件位置 -->
            <property name="configLocation" value="classpath:mybatis/mybatis-config.xml"></property> <!--指定数据源 -->
            <property name="dataSource" ref="dataSource"></property> <!--mapperLocations:所有sql映射文件所在的位置 -->
            <property name="mapperLocations" value="classpath:mybatis/mapper/*.xml"></property> <!--typeAliasesPackage:批量别名处理-->
            <property name="typeAliasesPackage" value="com.atguigu.bean"></property>
        </bean>
        <!--自动的扫描所有的mapper的实现并加入到ioc容器中 -->
        <bean id="configure" class="org.mybatis.spring.mapper.MapperScannerConfigurer">
            <!– basePackage:指定包下所有的mapper接口实现自动扫描并加入到ioc容器中 -->
            <property name="basePackage" value="com.atguigu.dao"></property>
        </bean>
        ```
9. MyBatis-逆向工程
    1. MyBatisGenerator:
        1. 简称MBG，是一个专门为MyBatis框架使用者定 制的代码生成器，可以快速的根据表生成对应的 映射文件，接口，以及bean类。支持基本的增删 改查，以及QBC风格的条件查询。但是表连接、 存储过程等这些复杂sql的定义需要我们手工编写
        2. 官方文档地址 http://www.mybatis.org/generator/
        3. 官方工程地址 https://github.com/mybatis/generator/releases
    2. MBG使用
        1. 使用步骤:
            1. 编写MBG的配置文件(重要几处配置)
                1. jdbcConnection配置数据库连接信息
                2. javaModelGenerator配置javaBean的生成策略
                3. sqlMapGenerator 配置sql映射文件生成策略
                4. javaClientGenerator配置Mapper接口的生成策略
                5. table 配置要逆向解析的数据表
                    1. tableName:表名
                    2. domainObjectName:对应的javaBean名
            2. 运行代码生成器生成代码
        2. 注意:
            1. Context标签
            2. targetRuntime=“MyBatis3“可以生成带条件的增删改查
            3. targetRuntime=“MyBatis3Simple“可以生成基本的增删改查
            4. 如果再次生成，建议将之前生成的数据删除，避免xml向后追加内容出现的问题。
        3. MBG配置文件
            ```
            <generatorConfiguration>
                <context id="DB2Tables" targetRuntime="MyBatis3">
                    //数据库连接信息配置
                    <jdbcConnection driverClass="com.mysql.jdbc.Driver" connectionURL="jdbc:mysql://localhost:3306/bookstore0629" userId="root" password="123456"></jdbcConnection>
                    //javaBean的生成策略
                    <javaModelGenerator targetPackage="com.atguigu.bean" targetProject=".\src">
                        <property name="enableSubPackages" value="true" />
                        <property name="trimStrings" value="true" />
                    </javaModelGenerator>
                    //映射文件的生成策略
                    <sqlMapGenerator targetPackage="mybatis.mapper" targetProject=".\conf">
                        <property name="enableSubPackages" value="true" />
                    </sqlMapGenerator>
                    //dao接口java文件的生成策略
                    <javaClientGenerator type="XMLMAPPER" targetPackage="com.atguigu.dao" targetProject=".\src">
                        <property name="enableSubPackages" value="true" />
                    </javaClientGenerator>
                    //数据表与javaBean的映射
                    <table tableName="books" domainObjectName="Book"></table>
                </context>
            </generatorConfiguration>
            ```
        4. 生成器代码
            ```
            public static void main(String[] args) throws Exception {
                List<String> warnings = new ArrayList<String>();
                boolean overwrite = true;
                File configFile = new File("mbg.xml");
                ConfigurationParser cp = new ConfigurationParser(warnings);
                Configuration config = cp.parseConfiguration(configFile);
                DefaultShellCallback callback = new DefaultShellCallback(overwrite);
                MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
                myBatisGenerator.generate(null);
            }
            ```
            1. 测试查询: QBC风格的带条件查询
                ```
                @Test
                public void test01(){
                    SqlSession openSession = build.openSession();
                    DeptMapper mapper = openSession.getMapper(DeptMapper.class); DeptExample example = new DeptExample(); //所有的条件都在example中封装
                    Criteria criteria = example.createCriteria();
                    //select id, deptName, locAdd from tbl_dept WHERE
                    //( deptName like ? and id > ? )
                    criteria.andDeptnameLike("%部%");
                    criteria.andIdGreaterThan(2);
                    List<Dept> list = mapper.selectByExample(example);
                    for (Dept dept : list) {
                        System.out.println(dept);
                    }
                }
                ```
10. MyBatis-工作原理
11. MyBatis-插件开发
    1. MyBatis在四大对象的创建过程中，都会有插件进行介入。插件可以利用动态代理机制一层层的包装目标对象，而实现在目标对象执行目标方法之前进行拦截的效果。
    2. MyBatis允许在已映射语句执行过程中的某一点进行拦截调用。
    3. 默认情况下，MyBatis允许使用插件来拦截的方法调用包括:
        1. Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed)
        2. ParameterHandler (getParameterObject, setParameters)
        3. ResultSetHandler (handleResultSets, handleOutputParameters)
        4. StatementHandler (prepare, parameterize, batch, update, query)
    4. 插件开发
        1. 插件开发步骤
            1. 编写插件实现Interceptor接口，并使用@Intercepts注解完成插件签名
                ```
                @Intercepts({@Signature(type=StatementHandler.class, method="prepare", args={Connection.class})})
                public class MyFirstPlugin implements Interceptor {}
                ```
            2. 在全局配置文件中注册插件
                ```
                <plugins>
                    <plugin interceptor="com.onevgo.mybatis.plugin.MyFirstPlugin">
                        <property name="username" value="tomcat" />
                    </plugin>
                </plugins>
                ```
    5. 插件原理
        1. 按照插件注解声明，按照插件配置顺序调用插件plugin方法，生成被拦截对象的动态代理
        2. 多个插件依次生成目标对象的代理对象，层层包裹，先声明的先包裹;形成代理链
        3. 目标方法执行时依次从外到内执行插件的intercept方法。
        4. 多个插件情况下，我们往往需要在某个插件中分离出目标对象。可以借助MyBatis提供的SystemMetaObject类来进行获取最后一层的h以及target属性的值
    6. Interceptor接口
        1. Intercept:拦截目标方法执行
        2. plugin:生成动态代理对象，可以使用MyBatis提供的Plugin类的wrap方法
        3. setProperties:注入插件配置时设置的属性
        4. 常用代码:
            1. 从代理链中分离真实被代理对象
                ```
                // 1、分离代理对象。由于会形成多次代理，所以需要通过一个 while 循环分离出最终被代理对象，从而方便提取信息
                MetaObject metaObject = SystemMetaObject.forObject(target);
                while (metaObject.hasGetter("h")) {
                    Object h = metaObject.getValue("h");
                    metaObject = SystemMetaObject.forObject(h);
                }
                // 2、获取到代理对象中包含的被代理的真实对象
                Object obj = metaObject.getValue("target");
                // 3、获取被代理对象的MetaObject方便进行信息提取
                MetaObject forObject = SystemMetaObject.forObject(obj);
                ```
12. 扩展:MyBatis实用场景
    1. PageHelper插件进行分页
    2. 批量操作
    3. 存储过程
    4. typeHandler处理枚举
    5. PageHelper插件进行分页
        1. PageHelper是MyBatis中非常方便的第三方分页 插件。
        2. 官方文档: https://github.com/pagehelper/Mybatis-PageHelper/blob/master/README_zh.md
        3. 我们可以对照官方文档的说明，快速的使用插件
        4. 使用步骤
            1. 导入相关包pagehelper-x.x.x.jar和jsqlparser-0.9.5.jar。
            2. 在MyBatis全局配置文件中配置分页插件。
                ```
                <plugins>
                    <!-- com.github.pagehelper 为 PageHelper类所在的包名 -->
                    <plugin>
                        <!-- 使用下面的方式配置参数，后面会有所有的参数介绍 -->
                        <property name="param1" value="value1" />
                    </plugin>
                </plugins>
                ```
            3. 使用PageHelper提供的方法进行分页
            4. 可以使用更强大的PageInfo封装返回结果
    6. 批量操作
        1. 默认的 openSession() 方法没有参数,它会创建有如下特性的
            1. 会开启一个事务(也就是不自动提交)
            2. 连接对象会从由活动环境配置的数据源实例得到。
            3. 事务隔离级别将会使用驱动或数据源的默认设置。
            4. 预处理语句不会被复用,也不会批量处理更新。
        2. openSession 方法的 ExecutorType 类型的参数，枚举类型:
            1. ExecutorType.SIMPLE: 这个执行器类型不做特殊的事情(这是默认装配的)。它为每个语句的执行创建一个新的预处理语句。
            2. ExecutorType.REUSE: 这个执行器类型会复用预处理语句。
            3. ExecutorType.BATCH: 这个执行器会批量执行所有更新语句
        3. 批量操作我们是使用MyBatis提供的BatchExecutor进行的，他的底层就是通过jdbc攒sql的方式进行的。我们可以让他攒够一定数量后发给数据库一次。
            ```
            public void test01() {
                SqlSession openSession = build.openSession(ExecutorType.BATCH);
                UserDao mapper = openSession.getMapper(UserDao.class);
                long start = System.currentTimeMillis();
                for (int i = 0; i < 1000000; i++) {
                    String name = UUID.randomUUID().toString().substring(0, 5);
                    mapper.addUser(new User(null, name, 13));
                }
                openSession.commit(); openSession.close();
                long end = System.currentTimeMillis();
                System.out.println("耗时时间:"+(end-start));
            }
            ```
        4. 与Spring整合中，我们推荐，额外的配置一个可以专 门用来执行批量操作的sqlSession
            ```
            <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
                <constructor-arg name="sqlSessionFactory" ref="sqlSessionFactoryBean"></constructor-arg>
                <constructor-arg name="executorType" value="BATCH"></constructor-arg>
            </bean>
            ```
        5. 需要用到批量操作的时候，我们可以注入配置的这个批量 SqlSession。通过他获取到mapper映射器进行操作。
        6. 注意:
            1. 批量操作是在session.commit()以后才发送sql语句给数据库进行执行的
            2. 如果我们想让其提前执行，以方便后续可能的查询操作 获取数据，我们可以使用sqlSession.flushStatements()方 法，让其直接冲刷到数据库进行执行。
    7. 存储过程
        1. 实际开发中，我们通常也会写一些存储过程， MyBatis也支持对存储过程的调用
        2. 一个最简单的存储过程
            ```
            delimiter $$
            create procedure test()
            begin
                select 'hello';
            end $$
            delimiter ;
            ```
        3. 存储过程的调用
            1. select标签中statementType=“CALLABLE”
            2. 标签体中调用语法:
                1. `{call procedure_name(#{param1_info},#{param2_info})}`
        4. 存储过程-游标处理
            1. MyBatis对存储过程的游标提供了一个JdbcType=CURSOR的支持， 可以智能的把游标读取到的数据，映射到我们声明的结果集中
            2. 调用实例:
                ```
                EmployeeMapper.xml
                <select id="getPage" parameterType="PageEmp" statementType="CALLABLE" databaseId="oracle">
                    {call PAGE_EMP(
                        #{start,mode=IN,jdbcType=INTEGER},
                        #{end,mode=IN,jdbcType=INTEGER},
                        #{count,mode=OUT,jdbcType=INTEGER},
                        #{emps,mode=OUT,jdbcType=CURSOR,javaType=ResultSet,resultMap=TestEmp}
                    )}
                </select>
                <resultMap type="Emp" id="TestEmp">
                    <id column="EMPNO" property="id"></id>
                </resultMap>
                
                db.properties
                orcl.driver=oracle.jdbc.OracleDriver
                orcl.url=jdbc:oracle:thin:@localhost:1521:orcl
                orcl.username=scott
                orcl.password=123456
                
                PageEmp.java
                public class PageEmp {
                    private int start;
                    private int end;
                    private int count;
                    private List<Emp> emps;
                }
                
                mybatis-config.xml
                <environment id="oracle_dev">
                    <transactionManager type="JDBC" />
                    <dataSource type="POOLED">
                        <property name="driver" value="${orcl.driver}" />
                        <property name="url" value="${orcl.url}" />
                        <property name="username" value="${orcl.username}" />
                        <property name="password" value="${orcl.password}" />
                    </dataSource>
                </environment>
                <databaseIdProvider type="DB_VENDOR">
                    <property name="MySQL" value="mysql"/>
                    <property name="Oracle" value="oracle"/>
                </databaseIdProvider>
                
                procedure.sql
                create or replace procedure
                hello_test(
                    p_start in int,
                    p_end in int,
                    p_counr out int,
                    ref_cur out sys_refcursor
                ) AS
                BEGIN
                    select count(*) into p_count from empl;
                    open ref_cur for
                        select * from (select e.*,rownum as r1 from emp e where rownum < p_end)
                        where r1 > p_start;
                END hello_test;
                ```
    8. 自定义TypeHandler处理枚举
        1. 我们可以通过自定义TypeHandler的形式来在设置参数或 者取出结果集的时候自定义参数封装策略。
        2. 步骤:
            1. 实现TypeHandler接口或者继承BaseTypeHandler
            2. 使用@MappedTypes定义处理的java类型，使用@MappedJdbcTypes定义jdbcType类型
            3. 在自定义结果集标签或者参数处理的时候声明使用自定义 TypeHandler进行处理或者在全局配置TypeHandler要处理的javaType
        3. 测试实例
            1. 一个代表部门状态的枚举类
                ```
                public enum DeptStatus {
                    WORKING(100, "部门正在工作中"),
                    MEETING(200, "部门正在开会中"),
                    VOCATION(300, "部门正在休假中");
                }
                ```
                1. 测试全局配置EnumOrdinalTypeHandler
                    ```
                    <typeHandlers>
                        <typeHandler handler="org.apache.ibatis.type.EnumOrdinalTypeHandler" javaType="com.onevgo.mybatis.bean.DeptStatus"/>
                    </typeHandlers>
                    ```
                2. 测试全局配置EnumTypeHandler
                    ```
                    <typeHandlers>
                        <typeHandler handler="org.apache.ibatis.type.EnumTypeHandler" javaType="com.onevgo.mybatis.bean.DeptStatus"/>
                    </typeHandlers>
                    ```
                3. 测试参数位置设置自定义TypeHandler
                    ```
                    <insert id="addDept">
                        insert into department(dept_name,status) values(#{deptName}, #{status,typeHandler=com.onevgo.mybatis.typehanlder.MyEnumTypeHandler})
                    </insert>
                    ```
        4. 自定义TypeHandler
