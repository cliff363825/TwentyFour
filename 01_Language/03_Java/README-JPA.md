# 好脑子不如烂笔头系列之java - JPA
===========================
1. JPA 概述
    1. JPA 是什么
        1. Java Persistence API：用于对象持久化的 API
        2. Java EE 5.0 平台标准的 ORM 规范，使得应用程序以统一的方式访问持久层
    2. JPA和Hibernate的关系
        1. JPA 是 hibernate 的一个抽象（就像JDBC和JDBC驱动的关系）：
            1. JPA 是规范：JPA 本质上就是一种  ORM 规范，不是ORM 框架 —— 因为 JPA 并未提供 ORM 实现，它只是制订了一些规范，提供了一些编程的 API 接口，但具体实现则由 ORM 厂商提供实现
            2. Hibernate 是实现：Hibernate 除了作为 ORM 框架之外，它也是一种 JPA 实现
        2. 从功能上来说， JPA 是 Hibernate 功能的一个子集
    3. JPA 的供应商
        1. JPA 的目标之一是制定一个可以由很多供应商实现的 API，目前Hibernate 3.2+、TopLink 10.1+ 以及 OpenJPA 都提供了 JPA 的实现
        2. Hibernate
            1. JPA 的始作俑者就是 Hibernate 的作者
            2. Hibernate 从 3.2 开始兼容 JPA
        3. OpenJPA
            1. OpenJPA  是 Apache 组织提供的开源项目
        4. TopLink
            1. TopLink 以前需要收费，如今开源了
    4. JPA的优势
        1. 标准化:  提供相同的 API，这保证了基于JPA 开发的企业应用能够经过少量的修改就能够在不同的 JPA 框架下运行。
        2. 简单易用，集成方便:  JPA 的主要目标之一就是提供更加简单的编程模型，在 JPA 框架下创建实体和创建 Java  类一样简单，只需要使用 javax.persistence.Entity 进行注释；JPA 的框架和接口也都非常简单，
        3. 可媲美JDBC的查询能力:  JPA的查询语言是面向对象的，JPA定义了独特的JPQL，而且能够支持批量更新和修改、JOIN、GROUP BY、HAVING 等通常只有 SQL 才能够提供的高级查询特性，甚至还能够支持子查询。
        4. 支持面向对象的高级特性: JPA 中能够支持面向对象的高级特性，如类之间的继承、多态和类之间的复杂关系，最大限度的使用面向对象的模型
    5. JPA 包括 3方面的技术
        1. ORM  映射元数据：JPA 支持 XML 和  JDK 5.0 注解两种元数据的形式，元数据描述对象和表之间的映射关系，框架据此将实体对象持久化到数据库表中。  
        2. JPA 的 API：用来操作实体对象，执行CRUD操作，框架在后台完成所有的事情，开发者从繁琐的 JDBC和 SQL代码中解脱出来。  
        3. 查询语言（JPQL）：这是持久化操作中很重要的一个方面，通过面向对象而非面向数据库的查询语言查询数据，避免程序和具体的  SQL 紧密耦合。
2. HelloWorld
    1. 使用JPA持久化对象的步骤
        1. 创建 persistence.xml, 在这个文件中配置持久化单元
            1. 需要指定跟哪个数据库进行交互;
            2. 需要指定 JPA 使用哪个持久化的框架以及配置该框架的基本属性
        2. 创建实体类, 使用 annotation 来描述实体类跟数据库表之间的映射关系.
        3. 使用 JPA API 完成数据增加、删除、修改和查询操作
            1. 创建 EntityManagerFactory (对应 Hibernate 中的 SessionFactory);
            2. 创建 EntityManager (对应 Hibernate 中的Session);
    2. 在 Eclipse 下创建 JPA 工程
    3. 开发JPA依赖的jar文件
        1. hibernate-release-4.2.4.Final/lib/required/*.jar
        2. hibernate-release-4.2.4.Final/lib/jpa/*.jar
        3. 数据库驱动的 jar 包
    4. persistence.xml
        1. JPA 规范要求在类路径的 META-INF 目录下放置persistence.xml，文件的名称是固定的
    5. 执行持久化操作
3. JPA 基本注解
    1. JPA 基本注解
        1. @Entity
        2. @Transient
        3. @Temporal
        4. @Table
        5. @Id
        6. @GeneratedValue
        7. @Column
        8. @Basic
    2. @Entity
        1. @Entity 标注用于实体类声明语句之前，指出该Java 类为实体类，将映射到指定的数据库表。如声明一个实体类 Customer，它将映射到数据库中的 customer 表上。
    3. @Table
        1. 当实体类与其映射的数据库表名不同名时需要使用 @Table 标注说明，该标注与 @Entity 标注并列使用，置于实体类声明语句之前，可写于单独语句行，也可与声明语句同行。
        2. @Table 标注的常用选项是 name，用于指明数据库的表名
        3. @Table标注还有一个两个选项 catalog 和 schema 用于设置表所属的数据库目录或模式，通常为数据库名。uniqueConstraints 选项用于设置约束条件，通常不须设置。
            ```
            @Table(name="PERSON_TABLE")
            @Entity
            public class Person {}
            ```
    4. @Id
        1. @Id 标注用于声明一个实体类的属性映射为数据库的主键列。该属性通常置于属性声明语句之前，可与声明语句同行，也可写在单独行上。
        2. @Id标注也可置于属性的getter方法之前。
            ```
            @Column(name="PERSON_ID")
            @GeneratedValue(strategy=GenerationType.AUTO)
            @Id
            public Integer getPersonId() {...}
            ```
    5. @GeneratedValue
        1. @GeneratedValue  用于标注主键的生成策略，通过 strategy 属性指定。默认情况下，JPA 自动选择一个最适合底层数据库的主键生成策略：SqlServer 对应 identity，MySQL 对应 auto increment。
        2. 在 javax.persistence.GenerationType 中定义了以下几种可供选择的策略：
            1. IDENTITY：采用数据库 ID自增长的方式来自增主键字段，Oracle 不支持这种方式；
            2. AUTO： JPA自动选择合适的策略，是默认选项；
            3. SEQUENCE：通过序列产生主键，通过 @SequenceGenerator 注解指定序列名，MySql 不支持这种方式
            4. TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植。
    6. @Basic
        1. @Basic 表示一个简单的属性到数据库表的字段的映射,对于没有任何标注的 getXxxx() 方法,默认即为@Basic
        2. fetch: 表示该属性的读取策略,有 EAGER 和 LAZY 两种,分别表示主支抓取和延迟加载,默认为 EAGER.
        3. optional:表示该属性是否允许为null, 默认为true 
    7. @Column
        1. 当实体的属性与其映射的数据库表的列不同名时需要使用@Column 标注说明，该属性通常置于实体的属性声明语句之前，还可与 @Id 标注一起使用。
        2. @Column 标注的常用属性是 name，用于设置映射数据库表的列名。此外，该标注还包含其它多个属性，如：unique 、nullable、length 等。
        3. @Column 标注的 columnDefinition 属性: 表示该字段在数据库中的实际类型.通常 ORM 框架可以根据属性类型自动判断数据库中字段的类型,但是对于Date类型仍无法确定数据库中字段类型究竟是DATE,TIME还是TIMESTAMP.此外,String的默认映射类型为VARCHAR, 如果要将 String 类型映射到特定数据库的 BLOB 或TEXT 字段类型.
        4. @Column标注也可置于属性的getter方法之前
    8. @Transient
        1. 表示该属性并非一个到数据库表的字段的映射,ORM框架将忽略该属性.
        2. 如果一个属性并非数据库表的字段映射,就务必将其标示为@Transient,否则,ORM框架默认其注解为@Basic 
    9. @Temporal
        1. 在核心的 Java API 中并没有定义 Date 类型的精度(temporal precision).  而在数据库中,表示 Date 类型的数据有 DATE, TIME, 和 TIMESTAMP 三种精度(即单纯的日期,时间,或者两者 兼备). 在进行属性映射时可使用@Temporal注解来调整精度.
    10. 用 table 来生成主键详解
        1. 将当前主键的值单独保存到一个数据库的表中，主键的值每次都是从指定的表中查询来获得
        2. 这种方法生成主键的策略可以适用于任何数据库，不必担心不同数据库不兼容造成的问题。
4. JPA API
    1. JPA API 
        1. Persistence
        2. EntityManagerFactory
        3. EntityManager#find
        4. EntityManager#getReference
        5. EntityManager#persistence
        6. EntityManager#remove
        7. EntityManager#merge
        8. EntityManager 其他方法
        9. EntityTransaction
    2. JPA相关接口/类：Persistence
        1. Persistence  类是用于获取 EntityManagerFactory 实例。该类包含一个名为 createEntityManagerFactory 的 静态方法 。
        2. createEntityManagerFactory 方法有如下两个重载版本。
            1. 带有一个参数的方法以 JPA 配置文件 persistence.xml 中的持久化单元名为参数
            2. 带有两个参数的方法：前一个参数含义相同，后一个参数 Map类型，用于设置 JPA 的相关属性，这时将忽略其它地方设置的属性。Map 对象的属性名必须是 JPA 实现库提供商的名字空间约定的属性名。
    3. EntityManagerFactory
        1. EntityManagerFactory 接口主要用来创建 EntityManager 实例。该接口约定了如下4个方法：
            1. createEntityManager()：用于创建实体管理器对象实例。
            2. createEntityManager(Map map)：用于创建实体管理器对象实例的重载方法，Map 参数用于提供 EntityManager 的属性。
            3. isOpen()：检查 EntityManagerFactory 是否处于打开状态。实体管理器工厂创建后一直处于打开状态，除非调用close()方法将其关闭。
            4. close()：关闭 EntityManagerFactory 。 EntityManagerFactory 关闭后将释放所有资源，isOpen()方法测试将返回 false，其它方法将不能调用，否则将导致IllegalStateException异常。
    4. EntityManager
        1. 在 JPA 规范中, EntityManager 是完成持久化操作的核心对象。实体作为普通 Java 对象，只有在调用 EntityManager 将其持久化后才会变成持久化对象。EntityManager 对象在一组实体类与底层数据源之间进行 O/R 映射的管理。它可以用来管理和更新 Entity Bean, 根椐主键查找 Entity Bean, 还可以通过JPQL语句查询实体。
        2. 实体的状态:
            1. 新建状态:   新创建的对象，尚未拥有持久性主键。
            2. 持久化状态：已经拥有持久性主键并和持久化建立了上下文环境
            3. 游离状态：拥有持久化主键，但是没有与持久化建立上下文环境
            4. 删除状态:  拥有持久化主键，已经和持久化建立上下文环境，但是从数据库中删除。
        3. find (Class<T> entityClass,Object primaryKey)：返回指定的 OID 对应的实体类对象，如果这个实体存在于当前的持久化环境，则返回一个被缓存的对象；否则会创建一个新的 Entity, 并加载数据库中相关信息；若 OID 不存在于数据库中，则返回一个 null。第一个参数为被查询的实体类类型，第二个参数为待查找实体的主键值。
        4. getReference (Class<T> entityClass,Object primaryKey)：与find()方法类似，不同的是：如果缓存中不存在指定的 Entity, EntityManager 会创建一个 Entity 类的代理，但是不会立即加载数据库中的信息，只有第一次真正使用此 Entity 的属性才加载，所以如果此 OID 在数据库不存在，getReference() 不会返回 null 值, 而是抛出EntityNotFoundException
        5. persist (Object entity)：用于将新创建的 Entity 纳入到 EntityManager 的管理。该方法执行后，传入 persist() 方法的 Entity 对象转换成持久化状态。
            1. 如果传入 persist() 方法的 Entity 对象已经处于持久化状态，则 persist() 方法什么都不做。
            2. 如果对删除状态的 Entity 进行 persist() 操作，会转换为持久化状态。
            3. 如果对游离状态的实体执行 persist() 操作，可能会在 persist() 方法抛出 EntityExistException(也有可能是在flush或事务提交后抛出)。
        6. remove (Object entity)：删除实例。如果实例是被管理的，即与数据库实体记录关联，则同时会删除关联的数据库记录。
        7. merge (T entity)：merge() 用于处理 Entity 的同步。即数据库的插入和更新操作
        8. flush ()：同步持久上下文环境，即将持久上下文环境的所有未保存实体的状态信息保存到数据库中。
        9. setFlushMode (FlushModeType flushMode)：设置持久上下文环境的Flush模式。参数可以取2个枚举
            1. FlushModeType.AUTO 为自动更新数据库实体，
            2. FlushModeType.COMMIT 为直到提交事务时才更新数据库记录。
        10. getFlushMode ()：获取持久上下文环境的Flush模式。返回FlushModeType类的枚举值。
        11. refresh (Object entity)：用数据库实体记录的值更新实体对象的状态，即更新实例的属性值。
        12. clear ()：清除持久上下文环境，断开所有关联的实体。如果这时还有未提交的更新则会被撤消。
        13. contains (Object entity)：判断一个实例是否属于当前持久上下文环境管理的实体。
        14. isOpen ()：判断当前的实体管理器是否是打开状态。
        15. getTransaction ()：返回资源层的事务对象。EntityTransaction实例可以用于开始和提交多个事务。
        16. close ()：关闭实体管理器。之后若调用实体管理器实例的方法或其派生的查询对象的方法都将抛出 IllegalstateException 异常，除了getTransaction 和 isOpen方法(返回 false)。不过，当与实体管理器关联的事务处于活动状态时，调用 close 方法后持久上下文将仍处于被管理状态，直到事务完成。
        17. createQuery (String qlString)：创建一个查询对象。
        18. createNamedQuery (String name)：根据命名的查询语句块创建查询对象。参数为命名的查询语句。
        19. createNativeQuery (String sqlString)：使用标准 SQL语句创建查询对象。参数为标准SQL语句字符串。
        20. createNativeQuery (String sqls, String resultSetMapping)：使用标准SQL语句创建查询对象，并指定返回结果集 Map的 名称。
    5. EntityTransaction
        1. EntityTransaction 接口用来管理资源层实体管理器的事务操作。通过调用实体管理器的getTransaction方法 获得其实例。
        2. begin ()
            1. 用于启动一个事务，此后的多个数据库操作将作为整体被提交或撤消。若这时事务已启动则会抛出 IllegalStateException 异常。
        3. commit ()
            1. 用于提交当前事务。即将事务启动以后的所有数据库更新操作持久化至数据库中。
        4. rollback ()
            1. 撤消(回滚)当前事务。即撤消事务启动后的所有数据库更新操作，从而不对数据库产生影响。
        5. setRollbackOnly ()
            1. 使当前事务只能被撤消。
        6. getRollbackOnly ()
            1. 查看当前事务是否设置了只能撤消标志。
        7. isActive ()
            1. 查看当前事务是否是活动的。如果返回true则不能调用begin方法，否则将抛出 IllegalStateException 异常；如果返回 false 则不能调用 commit、rollback、setRollbackOnly 及 getRollbackOnly 方法，否则将抛出 IllegalStateException 异常。
5. 映射关联关系
    1. 映射关联关系
        1. 映射单向多对一的关联关系
        2. 映射单向一对多的关联关系
        3. 映射双向多对一的关联关系
        4. 映射双向一对一的关联关系
        5. 映射双向多对多的关联关系
    2. 双向一对多及多对一映射
        1. 双向一对多关系中，必须存在一个关系维护端，在 JPA 规范中，要求  many 的一方作为关系的维护端(owner side), one 的一方作为被维护端(inverse side)。
        2. 可以在 one 方指定 @OneToMany 注释并设置 mappedBy 属性，以指定它是这一关联中的被维护端，many 为维护端。
        3. 在 many 方指定 @ManyToOne 注释，并使用 @JoinColumn 指定外键名称
            ```
            Customer.java
            @OneToMany(targetEntity=Order.class, mappedBy="customer")
            @OrderBy("ORDER_NAME")
            public set<Order> getOrders() {...}
            
            Order.java
            @ManyToOne(targetEntity=Customer.class)
            @JoinColumn(name="CUSTOMER_ID")
            public Customer getCustomer() {...}
            ```
    3. 双向一对一映射
        1. 基于外键的 1-1 关联关系：在双向的一对一关联中，需要在关系被维护端(inverse side)中的 @OneToOne 注释中指定 mappedBy，以指定是这一关联中的被维护端。同时需要在关系维护端(owner side)建立外键列指向关系被维护端的主键列。
            ```
            Manager.java
            @OneToOne(mappedBy="mgr")
            public Department getDept() {...}
            
            Department.java
            @OneToOne(fetch=FetchType.LAZY)
            @JoinColumn(name="MGR_ID", unique=true)
            public Manager getMgr() {...}
            ```
    4. 双向 1-1 不延迟加载的问题
        1. 如果延迟加载要起作用, 就必须设置一个代理对象.
        2. Manager 其实可以不关联一个 Department
        3. 如果有 Department 关联就设置为代理对象而延迟加载, 如果不存在关联的 Department 就设置 null, 因为外键字段是定义在 Department 表中的,Hibernate 在不读取 Department 表的情况是无法判断是否有关联有 Department, 因此无法判断设置 null 还是代理对象, 而统一设置为代理对象,也无法满足不关联的情况, 所以无法使用延迟加载,只 有显式读取 Department.
    5. 双向多对多关联关系
        1. 在双向多对多关系中，我们必须指定一个关系维护端(owner side),可以通过 @ManyToMany 注释中指定 mappedBy 属性来标识其为关系维护端。
            ```
            Order.java
            @ManyToMany
            @JoinTable(name="CATEGORY_ITEM",
                       joinColumns={@JoinColumn(name="CATEGORY_ID", referencedColumnName="ID")},
                       inverseJoinColumns={@JoinColumn(name="ITEM_ID", referencedColumnName="ID")})
            public Set<Item> getItems() {...}
            
            Item.java
            @ManyToMany(mappedBy="items")
            public Set<Category> getCategories() {...}
            ```
6. 使用二级缓存
    1. 使用二级缓存
        1. `<shared-cache-mode>` 节点：若 JPA 实现支持二级缓存，该节点可以配置在当前的持久化单元中是否启用二级缓存，可配置如下值：
            1. ALL：所有的实体类都被缓存
            2. NONE：所有的实体类都不被缓存. 
            3. ENABLE_SELECTIVE：标识 @Cacheable(true) 注解的实体类将被缓存
            4. DISABLE_SELECTIVE：缓存除标识 @Cacheable(false) 以外的所有实体类
            5. UNSPECIFIED：默认值，JPA 产品默认值将被使用
7. JPQL
    1. JPQL
        1. HelloWorld
        2. 使用 Hibernate 的查询缓存
        3. ORDER BY 和 GROUP BY
        4. 关联查询
        5. 子查询 和 JPQL 函数
        6. UPDATE 和 DELETE
    2. JPQL语言
        1. JPQL语言，即 Java Persistence Query Language 的简称。JPQL 是一种和 SQL 非常类似的中间性和对象化查询语言，它最终会被编译成针对不同底层数据库的 SQL 查询，从而屏蔽不同数据库的差异。
        2. JPQL语言的语句可以是 select 语句、update 语句或delete语句，它们都通过 Query 接口封装执行
    3. javax.persistence.Query
        1. Query接口封装了执行数据库查询的相关方法。调用 EntityManager 的 createQuery、create NamedQuery 及 createNativeQuery 方法可以获得查询对象，进而可调用 Query 接口的相关方法来执行查询操作。
        2. Query接口的主要方法
            1. int executeUpdate()
                1. 用于执行update或delete语句。
            2. List getResultList()
                1. 用于执行select语句并返回结果集实体列表。
            3. Object getSingleResult()
                1. 用于执行只返回单个结果实体的select语句。
            4. Query setFirstResult(int startPosition)
                1. 用于设置从哪个实体记录开始返回查询结果。
            5. Query setMaxResults(int maxResult) 
                1. 用于设置返回结果实体的最大数。与setFirstResult结合使用可实现分页查询。
            6. Query setFlushMode(FlushModeType flushMode) 
                1. 设置查询对象的Flush模式。参数可以取2个枚举值：FlushModeType.AUTO 为自动更新数据库记录，FlushMode Type.COMMIT 为直到提交事务时才更新数据库记录。
            7. setHint(String hintName, Object value) 
                1. 设置与查询对象相关的特定供应商参数或提示信息。参数名及其取值需要参考特定 JPA 实现库提供商的文档。如果第二个参数无效将抛出IllegalArgumentException异常。
            8. setParameter(int position, Object value) 
                1. 为查询语句的指定位置参数赋值。Position 指定参数序号，value 为赋给参数的值。
            9. setParameter(int position, Date d, TemporalType type) 
                1. 为查询语句的指定位置参数赋 Date 值。Position 指定参数序号，value 为赋给参数的值，temporalType 取 TemporalType 的枚举常量，包括 DATE、TIME 及 TIMESTAMP 三个，，用于将 Java 的 Date 型值临时转换为数据库支持的日期时间类型（java.sql.Date、java.sql.Time及java.sql.Timestamp）。
            10. setParameter(int position, Calendar c, TemporalType type) 
                1. 为查询语句的指定位置参数赋 Calenda r值。position 指定参数序号，value 为赋给参数的值，temporalType 的含义及取舍同前。
            11. setParameter(String name, Object value) 
                1. 为查询语句的指定名称参数赋值。
            12. setParameter(String name, Date d, TemporalType type) 
                1. 为查询语句的指定名称参数赋 Date 值。用法同前。
            13. setParameter(String name, Calendar c, TemporalType type) 
                1. 为查询语句的指定名称参数设置Calendar值。name为参数名，其它同前。该方法调用时如果参数位置或参数名不正确，或者所赋的参数值类型不匹配，将抛出 IllegalArgumentException 异常。
        3. select语句
            1. select语句用于执行查询。其语法可表示为：
                ```
                select_clause 
                form_clause 
                [where_clause] 
                [groupby_clause] 
                [having_clause]
                [orderby_clause]
                ```
        4. select-from 子句
            1. from 子句是查询语句的必选子句。
                1. Select 用来指定查询返回的结果实体或实体的某些属性
                2. From 子句声明查询源实体类，并指定标识符变量（相当于SQL表的别名）。
            2. 如果不希望返回重复实体，可使用关键字 distinct 修饰。select、from 都是 JPQL 的关键字，通常全大写或全小写，建议不要大小写混用。
        5. 查询所有实体
            1. 查询所有实体的 JPQL 查询字串很简单，例如：
                1. select o from Order o 或  select o from Order as o
            2. 关键字 as 可以省去。
            3. 标识符变量的命名规范与 Java 标识符相同，且区分大小写。
            4. 调用 EntityManager 的 createQuery() 方法可创建查询对象，接着调用 Query 接口的 getResultList() 方法就可获得查询结果集。例如：
                ```
                Query query = entityManager.createQuery( "select o from Order o"); 
                List orders = query.getResultList();
                Iterator iterator = orders.iterator();
                while( iterator.hasNext() ) {
                    // 处理Order
                }
                ```
        6. where子句
            1. where子句用于指定查询条件，where跟条件表达式。例：
                1. select o from Orders o where o.id = 1
                2. select o from Orders o where o.id > 3 and o.confirm = 'true'	
                3. select o from Orders o where o.address.streetNumber >= 123
            2. JPQL也支持包含参数的查询，例如：
                1. select o from Orders o where o.id = :myId
               	2. select o from Orders o where o.id = :myId and o.customer = :customerName
                3. 注意：参数名前必须冠以冒号(:)，执行查询前须使用Query.setParameter(name, value)方法给参数赋值。
            3. 也可以不使用参数名而使用参数的序号，例如：
                1. select o from Order o where o.id = ?1 and o.customer = ?2
                2. 其中 ?1 代表第一个参数，?2 代表第一个参数。在执行查询之前需要使用重载方法Query.setParameter(pos, value) 提供参数值。
                    ```
                    Query query = entityManager.createQuery( "select o from 　	Orders o where o.id = ?1 and o.customer = ?2" );
                    query.setParameter( 1, 2 );
                    query.setParameter( 2, "John" );
                    List orders = query.getResultList();
                    ...
                    ```
            4. where条件表达式中可用的运算符基本上与SQL一致，包括：
                1. 算术运算符：+　-　*　/　+(正)　-(负)
                2. 关系运算符：==　<>　>　>=　<　<=　between…and　like　in　is null 等
                3. 逻辑运算符： and　or 　not
            5. where子句示例
                1. 下面是一些常见查询表达式示例：
                    ```
                    // 以下语句查询 Id 介于 100 至 200 之间的订单。
                    select o from Orders o where o.id between 100 and 200
                    
                    // 以下语句查询国籍为的 'US'、'CN'或'JP' 的客户。
                    select c from Customers c where c.county in ('US','CN','JP')
                    
                    // 以下语句查询手机号以139开头的客户。%表示任意多个字符序列，包括0个。
                    select c from Customers c where c.phone like '139%'
                    
                    // 以下语句查询名字包含4个字符，且234位为ose的客户。_表示任意单个字符。
                    select c from Customers c where c.lname like '_ose'
                    
                    // 以下语句查询电话号码未知的客户。Null用于测试单值是否为空。
                    select c from Customers c where c.phone is null
                    
                    // 以下语句查询尚未输入订单项的订单。empty用于测试集合是否为空。
                    select o from Orders o where o.orderItems is empty
                    ```
        7. 查询部分属性
            1. 如果只须查询实体的部分属性而不需要返回整个实体。例如：
                1. select o.id, o.customerName, o.address.streetNumber from Order o order by o.id
            2. 执行该查询返回的不再是Orders实体集合，而是一个对象数组的集合(Object[])，集合的每个成员为一个对象数组，可通过数组元素访问各个属性。
        8. 使用 Hibernate 的查询缓存
            ```
            String jpql = "select c from Customer c";
            Query query = entityManager.createQuery(jpql);
            query.setHint(QueryHints.CACHEABLE, true);
            ```
        9. order by子句
            1. order by子句用于对查询结果集进行排序。和SQL的用法类似，可以用 "asc" 和 "desc" 指定升降序。如果不显式注明，默认为升序。
                1. select o from Orders o order by o.id
                2. select o from Orders o order by o.address.streetNumber desc
                3. select o from Orders o order by o.customer asc, o.id desc
        10. group by子句与聚合查询
            1. group by 子句用于对查询结果分组统计，通常需要使用聚合函数。常用的聚合函数主要有 AVG、SUM、COUNT、MAX、MIN 等，它们的含义与SQL相同。例如：
                1. select max(o.id) from Orders o
            2. 没有 group by 子句的查询是基于整个实体类的，使用聚合函数将返回单个结果值，可以使用Query.getSingleResult()得到查询结果。例如：
                ```
                Query query = entityManager.createQuery("select max(o.id) from Orders o");
                Object result = query.getSingleResult();
                Long max = (Long)result;
                ...
                ```
        11. having子句
            1. Having 子句用于对 group by 分组设置约束条件，用法与where 子句基本相同，不同是 where 子句作用于基表或视图，以便从中选择满足条件的记录；having 子句则作用于分组，用于选择满足条件的组，其条件表达式中通常会使用聚合函数。
                ```
                例如，以下语句用于查询订购总数大于100的商家所售商品及数量：
                select o.seller, o.goodId, sum(o.amount) from V_Orders o group by o.seller, o.goodId having sum(o.amount) > 100
                ```
            2. having子句与where子句一样都可以使用参数。
        12. 关联查询
            1. 在JPQL中，很多时候都是通过在实体类中配置实体关联的类属性来实现隐含的关联(join)查询。例如：
                1. select o from Orders o where o.address.streetNumber=2000 
            2. 上述JPQL语句编译成以下SQL时就会自动包含关联，默认为左关联。
            3. 在某些情况下可能仍然需要对关联做精确的控制。为此，JPQL 也支持和 SQL 中类似的关联语法。如：
                1. left out join / left join 
                2. inner join 
                3. left join / inner join fetch 
                4. 其中，left join和left out join等义，都是允许符合条件的右边表达式中的实体为空。
                ```
                例如，以下外关联查询可以找出所有客户实体记录，即使它未曾订货：
                select c from Customers c left join c.orders o
                
                以下内关联查询只找出所有曾订过商品的客户实体记录：
                select c from Customers c inner join c.orders o
                
                如果001号客户下过5次订单的话，以下fetch关联查询将得到 5个客户实体的引用，并且执行了 5 个订单的查询：
                select c from Customers c left join fetch c.orders o where c.id=001
                ```
        13. 子查询
            1. JPQL也支持子查询，在 where 或 having 子句中可以包含另一个查询。当子查询返回多于 1 个结果集时，它常出现在 any、all、exists表达式中用于集合匹配查询。它们的用法与SQL语句基本相同。
        14. JPQL函数
            1. JPQL提供了以下一些内建函数，包括字符串处理函数、算术函数和日期函数。
            2. 字符串处理函数主要有：
                1. concat(String s1, String s2)：字符串合并/连接函数。
                2. substring(String s, int start, int length)：取字串函数。
                3. trim([leading|trailing|both,] [char c,] String s)：从字符串中去掉首/尾指定的字符或空格。
                4. lower(String s)：将字符串转换成小写形式。
                5. upper(String s)：将字符串转换成大写形式。
                6. length(String s)：求字符串的长度。
                7. locate(String s1, String s2[, int start])：从第一个字符串中查找第二个字符串(子串)出现的位置。若未找到则返回0。
            3. 算术函数主要有 abs、mod、sqrt、size 等。Size 用于求集合的元素个数。
            4. 日期函数主要为三个，即 current_date、current_time、current_timestamp，它们不需要参数，返回服务器上的当前日期、时间和时戳。
        15. update语句
            1. update语句用于执行数据更新操作。主要用于针对单个实体类的批量更新
                ```
                以下语句将帐户余额不足万元的客户状态设置为未偿付：
                update Customers c set c.status = '未偿付' where c.balance < 10000
                ```
        16. delete语句
            1. delete语句用于执行数据更新操作。
                ```
                以下语句删除不活跃的、没有订单的客户：
                delete from Customers c where c.status = 'inactive' and c.orders is empty
                ```
8. 整合 Spring
    1. Spring 整合 JPA
        1. 三种整合方式：
            1. LocalEntityManagerFactoryBean：适用于那些仅使用 JPA 进行数据访问的项目，该 FactoryBean 将根据JPA PersistenceProvider 自动检测配置文件进行工作，一般从“META-INF/persistence.xml”读取配置信息，这种方式最简单，但不能设置 Spring 中定义的DataSource，且不支持 Spring 管理的全局事务
            2. 从JNDI中获取：用于从 Java EE 服务器获取指定的EntityManagerFactory，这种方式在进行 Spring 事务管理时一般要使用 JTA 事务管理
            3. LocalContainerEntityManagerFactoryBean：适用于所有环境的 FactoryBean，能全面控制 EntityManagerFactory 配置,如指定 Spring 定义的 DataSource 等等。
            ```
            <!-- 配置 JPA 提供者的适配器 -->
            <bean id="jpaVendorAdapter" class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
                <property name="databasePlatform">
                    <bean class="com.atguigu.ssps.modules.persistence.Hibernates" factory-method="getDialect">
                        <constructor-arg ref="dataSource"></constructor-arg>
            	    </bean>
                </property>
            </bean>
            ```
    2. Spring 整合 JPA（2）
        ```
        <!-- 配置 JPA 的 EntityManager -->
        <bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
        	<property name="dataSource" ref="dataSource"></property>
        	<property name="jpaVendorAdapter" ref="jpaVendorAdapter"></property>
        	<property name="packagesToScan" value="com.atuigu.crm"></property>
        	<property name="jpaProperties">
                <props>
                    <!-- 二级缓存相关 -->
                    <prop key="hibernate.cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</prop>
                    <prop key="net.sf.ehcache.configurationResourceName">ehcache-hibernate.xml</prop>
                    <!-- 生成的数据表的列的映射策略 -->
                    <prop key="hibernate.ejb.naming_strategy">org.hibernate.cfg.ImprovedNamingStrategy</prop>
                    <!-- hibernate 基本属性 -->
                    <prop key="hibernate.show_sql">true</prop>
                    <prop key="hibernate.format_sql">true</prop>
                    <prop key="hibernate.hbm2ddl.auto">update</prop>
                </props>
            </property>
        </bean>
        ```