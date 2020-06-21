# 好脑子不如烂笔头系列之java - Spring Data
===========================
1. SpringData 概述
    1. Spring Data : Spring 的一个子项目。用于简化数据库访问，支持NoSQL 和 关系数据存储。其主要目标是使数据库的访问变得方便快捷。
    2. SpringData 项目所支持 NoSQL 存储：
        1. MongoDB （文档数据库）
        2. Neo4j（图形数据库）
        3. Redis（键/值存储）
        4. Hbase（列族数据库）
    3. SpringData 项目所支持的关系数据存储技术：
        1. JDBC
        2. JPA
    4. JPA Spring Data : 致力于减少数据访问层 (DAO) 的开发量. 开发者唯一要做的，就只是声明持久层的接口，其他都交给 Spring Data JPA 来帮你完成！
    5. 框架怎么可能代替开发者实现业务逻辑呢？比如：当有一个 UserDao.findUserById()  这样一个方法声明，大致应该能判断出这是根据给定条件的 ID 查询出满足条件的 User  对象。Spring Data JPA 做的便是规范方法的名字，根据符合规范的名字来确定方法需要实现什么样的逻辑。
2. SpringData JPA HelloWorld
    1. 使用 Spring Data JPA 进行持久层开发需要的四个步骤：
        1. 配置 Spring 整合 JPA
        2. 在 Spring 配置文件中配置 Spring Data，让 Spring 为声明的接口创建代理对象。配置了 <jpa:repositories> 后，Spring 初始化容器时将会扫描 base-package  指定的包目录及其子目录，为继承 Repository 或其子接口的接口创建代理对象，并将代理对象注册为 Spring Bean，业务层便可以通过 Spring 自动封装的特性来直接使用该对象。
        3. 声明持久层的接口，该接口继承  Repository，Repository 是一个标记型接口，它不包含任何方法，如必要，Spring Data 可实现 Repository 其他子接口，其中定义了一些常用的增删改查，以及分页相关的方法。
        4. 在接口中声明需要的方法。Spring Data 将根据给定的策略（具体策略稍后讲解）来为其生成实现代码。
    2. 搭建环境
        1. 同时下载 Spring Data Commons 和 Spring Data JPA 两个发布包：
            1. Commons 是 Spring Data 的基础包
            2. 并把相关的依赖 JAR 文件加入到 CLASSPATH 中
        2. 在 Spring 的配置文件中配置 Spring Data
    3. 示例代码
3. Repository 接口
    1. Repository 接口概述
        1. Repository 接口是 Spring Data 的一个核心接口，它不提供任何方法，开发者需要在自己定义的接口中声明需要的方法 
            1. public interface Repository<T, ID extends Serializable> { } 
        2. Spring Data可以让我们只定义接口，只要遵循 Spring Data的规范，就无需写实现类。  
        3. 与继承 Repository 等价的一种方式，就是在持久层接口上使用 @RepositoryDefinition 注解，并为其指定 domainClass 和 idClass 属性。如下两种方式是完全等价的
    2. Repository 的子接口
        1. 基础的 Repository 提供了最基本的数据访问功能，其几个子接口则扩展了一些功能。它们的继承关系如下： 
            1. Repository： 仅仅是一个标识，表明任何继承它的均为仓库接口类
            2. CrudRepository： 继承 Repository，实现了一组 CRUD 相关的方法 
            3. PagingAndSortingRepository： 继承 CrudRepository，实现了一组分页排序相关的方法 
            4. JpaRepository： 继承 PagingAndSortingRepository，实现一组 JPA 规范相关的方法 
            5. 自定义的 XxxxRepository 需要继承 JpaRepository，这样的 XxxxRepository 接口就具备了通用的数据访问控制层的能力。
            6. JpaSpecificationExecutor： 不属于Repository体系，实现一组 JPA Criteria 查询相关的方法 
3. SpringData 方法定义规范
    1. 简单条件查询
        1. 简单条件查询: 查询某一个实体类或者集合 
        2. 按照 Spring Data 的规范，查询方法以 find | read | get 开头，  涉及条件查询时，条件的属性用条件关键字连接，要注意的是：条件属性以首字母大写。 
            ```
            例如：定义一个 Entity 实体类
             class User {
                private String firstName;
                private String lastName; 
            }
             使用And条件连接时，应这样写：
            findByLastNameAndFirstName(String lastName,String firstName);
            条件的属性名称与个数要与参数的位置与个数一一对应
            ```
    2. 支持的关键字
        1. 直接在接口中定义查询方法，如果是符合规范的，可以不用写实现，目前支持的关键字写法如下：
            ```
            Keyword             Sample                      JPQL snippet
            And                 findByLastnameAndFirstname  ... where x.lastname = ?1 and x.firstname = ?2
            Or                  findByLastnameOrFirstname   ... where x.lastname = ?1 or x.firstname = ?2
            Between             findByStartDateBetween      ... where x.startDate between ?1 and ?2
            LessThan            findByAgeLessThan           ... where x.age < ?1
            GreaterThan         findByAgeGreaterThan        ... where x.age > ?2
            After               findByStartDateAfter        ... where x.startDate > ?1
            Before              findByStartDateBefore       ... where x.startDate < ?1
            IsNull              findByAgeIsNull             ... where x.age is null
            IsNotNull,NotNull   findByAge(Is)NotNull        ... where x.age not null
            Like                findByFirstnameLike         ... where x.firstname like ?1
            NotLike             findByFirstnameNotLike      ... where x.firstname not like ?1
            StartingWith        findByFirstnameStartingWith ... where x.firstname like ?1 (parameter bound with appended %)
            EndingWith          findByFirstnameEndingWith   ... where x.firstname like ?1 (parameter bound with prepended %)
            Containing          findByFirstnameContaining   ... where x.firstname like ?1 (parameter bound wrapped in %)
            OrderBy             findByAgeOrderByLastnameDesc ... where x.age = ?1 order by x.lastname desc
            Not                 findByLastnameNot           ... where x.lastname <> ?1
            In                  findByAgeIn(Collection<Age> ages) ... where x.age in ?1
            NotIn               findByAgeNotIn(Collection<Age> ages) ... where x.age not in ?1
            TRUE                findByActiveTrue()          ... where x.active = true
            FALSE               findByActiveFalse()         ... where x.active = false
            ...
            ```
    3. 查询方法解析流程
        1. 假如创建如下的查询：findByUserDepUuid()，框架在解析该方法时，首先剔除 findBy，然后对剩下的属性进行解析，假设查询实体为Doc
            1. 先判断 userDepUuid （根据 POJO 规范，首字母变为小写）是否为查询实体的一个属性，如果是，则表示根据该属性进行查询；如果没有该属性，继续第二步；
            2. 从右往左截取第一个大写字母开头的字符串(此处为Uuid)，然后检查剩下的字符串是否为查询实体的一个属性，如果是，则表示根据该属性进行查询；如果没有该属性，则重复第二步，继续从右往左截取；最后假设 user 为查询实体的一个属性；
            3. 接着处理剩下部分（DepUuid），先判断 user 所对应的类型是否有depUuid属性，如果有，则表示该方法最终是根据 “ Doc.user.depUuid” 的取值进行查询；否则继续按照步骤 2 的规则从右往左截取，最终表示根据 “Doc.user.dep.uuid” 的值进行查询。
            4. 可能会存在一种特殊情况，比如 Doc包含一个 user 的属性，也有一个 userDep 属性，此时会存在混淆。可以明确在属性之间加上 "_" 以显式表达意图，比如 "findByUser_DepUuid()" 或者 "findByUserDep_uuid()"
        2. 特殊的参数： 还可以直接在方法的参数上加入分页或排序的参数，比如：
            1. Page<UserModel> findByName(String name, Pageable pageable);
            2. List<UserModel> findByName(String name, Sort sort);
4. 使用 @Query 注解
    1. 使用@Query自定义查询
        1. 这种查询可以声明在 Repository 方法中，摆脱像命名查询那样的约束，将查询直接在相应的接口方法中声明，结构更为清晰，这是 Spring data 的特有实现。
    2. 索引参数与命名参数
        1. 索引参数如下所示，索引值从1开始，查询中 "?X" 个数需要与方法定义的参数个数相一致，并且顺序也要一致 
            ```
            @Query("select c from Customer c where c.customerId = ?1")
            Customer testGetByCustomerId2(Integer id);
            ```
        2. 命名参数（推荐使用这种方式）：可以定义好参数名，赋值时采用@Param("参数名")，而不用管顺序。
            ```
            @Query("select c from Customer u where c.firstname = :firstname or c.lastname = :lastname")
            Customer findByLastnameOrFirstname(@Param("lastname") String lastname, @Param("firstname") String firstname)
            ```
        3. 如果是 @Query 中有 LIKE 关键字，后面的参数需要前面或者后面加 %，这样在传递参数值的时候就可以不加 %：
            ```
            @Query("select o from UserModel o where o.name like ?1%")
            public List<UserModel> findByUuidOrAge(String name);
            
            @Query("select o from UserModel o where o.name like %?1")
            public List<UserModel> findByUuidOrAge(String name);
            
            @Query("select o from UserModel o where o.name like %?1%")
            public List<UserModel> findByUuidOrAge(String name);
            ```
    3. 用@Query来指定本地查询
        1. 还可以使用@Query来指定本地查询，只要设置nativeQuery为true，比如：
            ```
            @Query(value="select * from tbl_user where name like %?1" ,nativeQuery=true)
            public List<UserModel> findByUuidOrAge(String name);
            ```
5. @Modifying 注解和事务
    1. @Query 与 @Modifying 执行更新操作
        1. @Query 与 @Modifying 这两个 annotation一起声明，可定义个性化更新操作，例如只涉及某些字段更新时最为常用，示例如下：
            ```
            @Modifying
            @Query("update Customer c set c.customerName = ?1")
            int updateCustomerName(String cn);
            ``` 
        2. 注意：
            1. 方法的返回值应该是 int，表示更新语句所影响的行数
            2. 在调用的地方必须加事务，没有事务不能正常执行
    2. 事务
        1. Spring Data 提供了默认的事务处理方式，即所有的查询均声明为只读事务。
        2. 对于自定义的方法，如需改变 Spring Data 提供的事务默认方式，可以在方法上注解 @Transactional 声明 
        3. 进行多个 Repository 操作时，也应该使它们在同一个事务中处理，按照分层架构的思想，这部分属于业务逻辑层，因此，需要在 Service 层实现对多个 Repository 的调用，并在相应的方法上声明事务。 
6. CrudRepository 接口
    1. CrudRepository
        1. CrudRepository 接口提供了最基本的对实体类的添删改查操作 
            1. T save(T entity);//保存单个实体 
            2. Iterable<T> save(Iterable<? extends T> entities);//保存集合        
            3. T findOne(ID id);//根据id查找实体         
            4. boolean exists(ID id);//根据id判断实体是否存在         
            5. Iterable<T> findAll();//查询所有实体,不用或慎用!         
            6. long count();//查询实体数量         
            7. void delete(ID id);//根据Id删除实体         
            8. void delete(T entity);//删除一个实体 
            9. void delete(Iterable<? extends T> entities);//删除一个实体的集合         
            10. void deleteAll();//删除所有实体,不用或慎用! 
7. PagingAndSortingRepository接口
    1. PagingAndSortingRepository
        1. 该接口提供了分页与排序功能 
            1. Iterable<T> findAll(Sort sort); //排序 
            2. Page<T> findAll(Pageable pageable); //分页查询（含排序功能） 
8. JpaRepository接口
    1. JpaRepository
        1. 该接口提供了JPA的相关功能 
            1. List<T> findAll(); //查找所有实体 
            2. List<T> findAll(Sort sort); //排序、查找所有实体 
            3. List<T> save(Iterable<? extends T> entities);//保存集合 
            4. void flush();//执行缓存与数据库同步 
            5. T saveAndFlush(T entity);//强制执行持久化 
            6. void deleteInBatch(Iterable<T> entities);//删除一个实体集合 
9. JpaSpecificationExecutor接口
    1. JpaSpecificationExecutor
        1. 不属于Repository体系，实现一组 JPA Criteria 查询相关的方法 
        2. Specification：封装  JPA Criteria 查询条件。通常使用匿名内部类的方式来创建该接口的对象
10. 自定义 Repository 方法
    1. 为某一个 Repository 上添加自定义方法
        1. 步骤：
            1. 定义一个接口: 声明要添加的, 并自实现的方法
            2. 提供该接口的实现类: 类名需在要声明的 Repository 后添加 Impl, 并实现方法
            3. 声明 Repository 接口, 并继承 1) 声明的接口
            4. 使用. 
            5. 注意: 默认情况下, Spring Data 会在 base-package 中查找 "接口名Impl" 作为实现类. 也可以通过　repository-impl-postfix　声明后缀. 
    2. 为所有的 Repository 都添加自实现的方法
        1. 步骤：
            1. 声明一个接口, 在该接口中声明需要自定义的方法, 且该接口需要继承 Spring Data 的 Repository.
            2. 提供 1) 所声明的接口的实现类. 且继承 SimpleJpaRepository, 并提供方法的实现
            3. 定义 JpaRepositoryFactoryBean 的实现类, 使其生成 1) 定义的接口实现类的对象
            4. 修改 <jpa:repositories /> 节点的 factory-class 属性指向 3) 的全类名
            5. 注意: 全局的扩展实现类不要用 Imp 作为后缀名, 或为全局扩展接口添加 @NoRepositoryBean 注解告知  Spring Data: Spring Data 不把其作为 Repository
