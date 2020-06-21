# 好脑子不如烂笔头系列之java - JDBC
===========================
1. java.sql.Driver：
    1. Java.sql.Driver 接口是所有 JDBC 驱动程序需要实现的接口。这个接口是提供给数据库厂商使用的，不同数据库厂商提供不同的实现
    2. 在程序中不需要直接去访问实现了 Driver 接口的类，而是由驱动程序管理器类(java.sql.DriverManager)去调用这些Driver实现
2. java.sql.DriverManager：用来装载驱动程序，获取数据库连接。
    1. 加载 JDBC 驱动需调用 Class 类的静态方法 forName()，向其传递要加载的 JDBC 驱动的类名
    2. DriverManager 类是驱动程序管理器类，负责管理驱动程序
    3. 通常不用显式调用 DriverManager 类的 registerDriver() 方法来注册驱动程序类的实例，因为 Driver 接口的驱动程序类都包含了静态代码块，在这个静态代码块中，会调用 DriverManager.registerDriver() 方法来注册自身的一个实例
3. java.sql.Connection：完成对某一指定数据库的联接
    1. 可以调用 DriverManager 类的 getConnection() 方法建立到数据库的连接
    2. JDBC URL 用于标识一个被注册的驱动程序，驱动程序管理器通过这个 URL 选择正确的驱动程序，从而建立到数据库的连接。
    3. JDBC URL的标准由三部分组成，各部分间用冒号分隔。 
        1. `jdbc:<子协议>:<子名称>`
        2. 协议：JDBC URL中的协议总是jdbc 
        3. 子协议：子协议用于标识一个数据库驱动程序
        4. 子名称：一种标识数据库的方法。子名称可以依不同的子协议而变化，用子名称的目的是为了定位数据库提供足够的信息 
    4. 几种常用数据库的JDBC URL
        1. Oracle： `jdbc:oracle:thin:@localhost:1521:sid`
        2. SQLServer： `jdbc:microsoft:sqlserver//localhost:1433; DatabaseName=sid`
        3. MYSQL： `jdbc:mysql://localhost:3306/sid`
4. java.sql.Statement：在一个给定的连接中作为SQL执行声明的容器
    1. 通过调用 Connection 对象的 createStatement 方法创建该对象
    2. 该对象用于执行静态的 SQL 语句，并且返回执行结果
    3. Statement 接口中定义了下列方法用于执行 SQL 语句：
        1. ResultSet executeQuery(String sql)
        2. int executeUpdate(String sql)
    4. 两个重要的子类：
        1. java.sql.PreparedStatement 用于执行预编译的sql声明
            1. 可以通过调用 Connection 对象的 preparedStatement() 方法获取 PreparedStatement 对象
            2. PreparedStatement 接口是 Statement 的子接口，它表示一条预编译过的 SQL 语句
            3. PreparedStatement 对象所代表的 SQL 语句中的参数用问号(?)来表示，调用 PreparedStatement 对象的 setXXX() 方法来设置这些参数. 
                - setXXX() 方法有两个参数，第一个参数是要设置的 SQL 语句中的参数的索引(从 1 开始)，第二个是设置的 SQL 语句中的参数的值
            4. PreparedStatement vs Statement:
                1. 代码的可读性和可维护性. 
                2. PreparedStatement 能最大可能提高性能：
                3. PreparedStatement 可以防止 SQL 注入 
        2. Java.sql.CallableStatement 用于执行数据库中存储过程的调用
5. java.sql.ResultSet：对于给定声明取得结果的途径
    1. 通过调用 Statement 对象的 executeQuery() 方法创建该对象
    2. ResultSet 对象以逻辑表格的形式封装了执行数据库操作的结果集，ResultSet 接口由数据库厂商实现
    3. ResultSet 对象维护了一个指向当前数据行的游标，初始的时候，游标在第一行之前，可以通过 ResultSet 对象的 next() 方法移动到下一行
    4. ResultSet 接口的常用方法
6. 数据类型转换表
    ```
    java类型                  SQL类型
    boolean                 BIT
    byte                    TINYINT
    short                   SMALLINT
    int                     INTEGER
    long                    BIGINT
    String                  CHAR,VARCHAR,LONGVARCHAR
    byte array              BINARY,VARBINARY
    java.sql.Date           DATE
    java.sql.Time           TIME
    java.sql.Timestamp      TIMESTAMP
    ```
7. java.sql.DatabaseMetaData：提供了许多方法用于获得数据源的各种信息，通过这些方法可以非常详细的了解数据库的信息
    1. getURL()：返回一个String类对象，代表数据库的URL。
    2. getUserName()：返回连接当前数据库管理系统的用户名。
    3. isReadOnly()：返回一个boolean值，指示数据库是否只允许读操作。
    4. getDatabaseProductName()：返回数据库的产品名称。
    5. getDatabaseProductVersion()：返回数据库的版本号。
    6. getDriverName()：返回驱动驱动程序的名称。
    7. getDriverVersion()：返回驱动程序的版本号。
8. java.sql.ResultSetMetaData：可用于获取关于 ResultSet 对象中列的类型和属性信息的对象
    1. getColumnName(int column)：获取指定列的名称
    2. getColumnLabel(int column)：获取指定列的别名，没有别名则同getColumnName(int column)
    3. getColumnCount()：返回当前 ResultSet 对象中的列数。 
    4. getColumnTypeName(int column)：检索指定列的数据库特定的类型名称。 
    5. getColumnDisplaySize(int column)：指示指定列的最大标准宽度，以字符为单位。 
    6. isNullable(int column)：指示指定列中的值是否可以为 null。 
    7. isAutoIncrement(int column)：指示是否自动为指定列进行编号，这样这些列仍然是只读的。 
9. 取得数据库自动生成的主键：
    1. Statement.RETURN_GENERATED_KEYS： `Connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);`
    2. PreparedStatement.getGeneratedKeys()
10. 数据库事务
    1. 事务的ACID(acid)属性
        1. 原子性（Atomicity）   原子性是指事务是一个不可分割的工作单位，事务中的操作要么都发生，要么都不发生。 
        2. 一致性（Consistency） 事务必须使数据库从一个一致性状态变换到另外一个一致性状态。
        3. 隔离性（Isolation）   事务的隔离性是指一个事务的执行不能被其他事务干扰，即一个事务内部的操作及使用的数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。
        4. 持久性（Durability）  持久性是指一个事务一旦被提交，它对数据库中数据的改变就是永久性的，接下来的其他操作和数据库故障不应该对其有任何影响
    2. JDBC 事务处理：
        1. 调用 Connection 对象的 setAutoCommit(false); 以取消自动提交事务
        2. 在所有的 SQL 语句都成功执行后，调用 commit(); 方法提交事务
        3. 在出现异常时，调用 rollback(); 方法回滚事务
        4. 若此时 Connection 没有被关闭, 则需要恢复其自动提交状态
    3. 数据库的隔离级别
        1. 对于同时运行的多个事务, 当这些事务访问数据库中相同的数据时, 如果没有采取必要的隔离机制, 就会导致各种并发问题:
            1. 脏读: 对于两个事物 T1, T2, T1 读取了已经被 T2 更新但还没有被提交的字段. 之后, 若 T2 回滚, T1读取的内容就是临时且无效的.
            2. 不可重复读: 对于两个事物 T1, T2, T1 读取了一个字段, 然后 T2 更新了该字段. 之后, T1再次读取同一个字段, 值就不同了.
            3. 幻读: 对于两个事物 T1, T2, T1 从一个表中读取了一个字段, 然后 T2 在该表中插入了一些新的行. 之后, 如果 T1 再次读取同一个表, 就会多出几行.
        2. 数据库提供的 4 种事务隔离级别:
            1. READ UNCOMMITTED（读未提交数据）     允许事务读取未被其他事务提交的变更。脏读、不可重复读和幻读的问题都会出现
            2. READ COMMITTED（读已提交数据）       只允许事务读取已经被其它事务提交的变更，可以避免脏读，但不可重复读和幻读问题仍然可能出现
            3. REPEATABLE READ（可重复读）          确保事务可以多次从一个字段中读取相同的值，在这个事务持续期间，禁止其它事务对这个字段进行更新，可以避免脏读和不可重复读，但幻读的问题依然存在
            4. SERIALIZABLE（串行化）               确保事务可以从一个表中读取相同的行，在这个事务持续期间，禁止其它事务对该表执行插入。更新和删除操作，所有并发问题都可以避免，但性能十分低下
        3. Oracle 支持的 2 种事务隔离级别：READ COMMITTED, SERIALIZABLE. Oracle 默认的事务隔离级别为: READ COMMITTED 
        4. Mysql 支持 4 种事务隔离级别. Mysql 默认的事务隔离级别为: REPEATABLE READ
        5. 在 MySql 中设置隔离级别
            1. 每启动一个 mysql 程序, 就会获得一个单独的数据库连接. 每个数据库连接都有一个全局变量 @@tx_isolation, 表示当前的事务隔离级别. MySQL 默认的隔离级别为 Repeatable Read
            2. 查看当前的隔离级别: `SELECT @@tx_isolation;`
            3. 设置当前 mySQL 连接的隔离级别: `set transaction isolation level read committed;`
            4. 设置数据库系统的全局的隔离级别: `set global transaction isolation level read committed;`
11. 批量处理JDBC语句提高处理速度
    1. JDBC的批量处理语句包括下面两个方法：
        1. addBatch(String):    添加需要批量处理的SQL语句或是参数
        2. executeBatch():      执行批量处理语句；
    2. 通常我们会遇到两种批量执行SQL语句的情况
        1. 多条SQL语句的批量处理
        2. 一个SQL语句的批量传参
12. 数据库连接池（connection pool）
    1. JDBC 的数据库连接池使用 javax.sql.DataSource 来表示，DataSource 只是一个接口，该接口通常由服务器(Weblogic, WebSphere, Tomcat)提供实现，也有一些开源组织提供实现：
        1. DBCP 数据库连接池
            1. DBCP 是 Apache 软件基金组织下的开源连接池实现，该连接池依赖该组织下的另一个开源系统：Common-pool. 如需使用该连接池实现，应在系统中增加如下两个 jar 文件：
                1. Commons-dbcp.jar：连接池的实现
                2. Commons-pool.jar：连接池实现的依赖库
            2. Tomcat 的连接池正是采用该连接池来实现的。该数据库连接池既可以与应用服务器整合使用，也可由应用程序独立使用
        2. C3P0 数据库连接池
    2. DataSource 通常被称为数据源，它包含连接池和连接池管理两个部分，习惯上也经常把 DataSource 称为连接池
    3. 数据源和数据库连接不同，数据源无需创建多个，它是产生数据库连接的工厂，因此整个应用只需要一个数据源即可。
    4. 当数据库访问结束后，程序还是像以前一样关闭数据库连接：conn.close(); 但上面的代码并没有关闭数据库的物理连接，它仅仅把数据库连接释放，归还给了数据库连接池。
13. Apache—DBUtils简介
    1. commons-dbutils 是 Apache 组织提供的一个开源 JDBC工具类库，它是对JDBC的简单封装，学习成本极低，并且使用dbutils能极大简化jdbc编码的工作量，同时也不会影响程序的性能。
    2. API介绍：
        1. org.apache.commons.dbutils.QueryRunner
            1. 该类简单化了SQL查询，它与ResultSetHandler组合在一起使用可以完成大部分的数据库操作，能够大大减少编码量。
            2. QueryRunner类提供了两个构造方法：
                1. 默认的构造方法
                2. 需要一个 javax.sql.DataSource 来作参数的构造方法。
            3. QueryRunner类的主要方法
                1. public Object query(Connection conn, String sql, Object[] params, ResultSetHandler rsh) throws SQLException：执行一个查询操作，在这个查询中，对象数组中的每个元素值被用来作为查询语句的置换参数。该方法会自行处理 PreparedStatement 和 ResultSet 的创建和关闭。
                2. public Object query(String sql, Object[] params, ResultSetHandler rsh) throws SQLException:　几乎与第一种方法一样；唯一的不同在于它不将数据库连接提供给方法，并且它是从提供给构造方法的数据源(DataSource) 或使用的setDataSource 方法中重新获得 Connection。
                3. public Object query(Connection conn, String sql, ResultSetHandler rsh) throws SQLException : 执行一个不需要置换参数的查询操作。
                4. public int update(Connection conn, String sql, Object[] params) throws SQLException:用来执行一个更新（插入、更新或删除）操作。
                5. public int update(Connection conn, String sql) throws SQLException：用来执行一个不需要置换参数的更新操作。
        2. org.apache.commons.dbutils.ResultSetHandler
            1. 该接口用于处理 java.sql.ResultSet，将数据按要求转换为另一种形式。
            2. ResultSetHandler 接口提供了一个单独的方法：Object handle (java.sql.ResultSet rs)。
            3. ResultSetHandler 接口的实现类
                1. ArrayHandler：把结果集中的第一行数据转成对象数组。
                2. ArrayListHandler：把结果集中的每一行数据都转成一个数组，再存放到List中。
                3. BeanHandler：将结果集中的第一行数据封装到一个对应的JavaBean实例中。
                4. BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
        3. 工具类
            1. org.apache.commons.dbutils.DbUtils: 提供如关闭连接、装载JDBC驱动程序等常规工作的工具类，里面的所有方法都是静态的。
                1. public static void close(...) throws java.sql.SQLException：DbUtils类提供了三个重载的关闭方法。这些方法检查所提供的参数是不是NULL，如果不是的话，它们就关闭Connection、Statement和ResultSet。
                2. public static void closeQuietly(...)：这一类方法不仅能在Connection、Statement和ResultSet为NULL情况下避免关闭，还能隐藏一些在程序中抛出的SQLException。
                3. public static void commitAndCloseQuietly(Connection conn)：用来提交连接，然后关闭连接，并且在关闭连接时不抛出SQL异常。 
                4. public static boolean loadDriver(java.lang.String driverClassName)：这一方装载并注册JDBC驱动程序，如果成功就返回true。使用该方法，你不需要捕捉这个异常ClassNotFoundException。