package com.onevgo.mybatis;

//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
import com.onevgo.mybatis.bean.Department;
import com.onevgo.mybatis.bean.EmpStatus;
import com.onevgo.mybatis.bean.Employee;
import com.onevgo.mybatis.dao.DepartmentMapper;
import com.onevgo.mybatis.dao.EmployeeMapper;
import com.onevgo.mybatis.dao.EmployeeMapperAnnotation;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 1. 接口式编程
 * 原生      UserDAO    ===> UserDAOImpl
 * mybatis  UserMapper ===> UserMapper.xml
 * 2. SqlSession 代表和数据库的一次会话，用完必须关闭
 * 3. SqlSession 和 Connection 一样都是非线程安全。每次使用都应该去获取新的对象
 * 4. mapper 接口没有实现类，但是 mybatis 会为这个接口生成一个代理对象
 * （将接口和xml进行绑定）
 * EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
 * 5. 两个重要的配置文件：
 * mybatis 的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息
 * sql 映射文件：保存了每一个sql语句的映射信息：
 * 将 sql 抽取出来
 */
public class MyBatisTest {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        String resource = "mybatis-config-test.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();
    }

    /**
     * 1. 根据 xml 配置文件（全局配置文件）创建一个 SqlSessionFactory 对象
     * 有数据源一些运行环境信息
     * 2. sql映射文件：配置了每一个 sql，以及 sql 的封装规则等
     * 3. 将sql映射文件注册在全局文件中
     * 4. 写代码
     * <ul>
     * <li>1. 根据全局文件得到 SqlSessionFactory</li>
     * <li>2. 使用 SqlSessionFactory 工厂，获取到 SqlSession 对象使用他来执行增删改查</li>
     * <li>一个 SqlSession 就是代表和数据库的一次会话，用完关闭</li>
     * <li>3. 使用 sql 的唯一标识来告诉 MyBatis 执行哪个sql。sql都是保存在sql映射文件中的</li>
     * </ul>
     *
     * @throws IOException
     */
    @Test
    public void testHello() throws IOException {
        // 2. 获取 SqlSession 实例，能直接执行已经映射的 sql 语句
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Employee employee = sqlSession.selectOne("com.onevgo.mybatis.dao.EmployeeMapper.getById", 1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 1. 获取 SqlSessionFactory 对象：
     * <ul>
     * <li>1. 解析文件的每一个信息保存在 Configuration 中，返回包含 Configuration 的 DefaultSqlSessionFactory</li>
     * <li>2. 注意: MappedStatement 代表一个增删改查的详细信息</li>
     * </ul>
     * 2. 获取 SqlSession 对象
     * <ul>
     * <li>1. 返回一个 DefaultSqlSession 对象，包含 Executor 和 Configuration；</li>
     * <li>2. 这一步会创建 Executor 对象；</li>
     * </ul>
     * 3. 获取接口的代理对象（MapperProxy）
     * <ul>
     * <li>1. getMapper，使用 MapperProxyFactory 创建一个 MapperProxy 的代理对象</li>
     * <li>2. 代理对象里面包含了 DefaultSqlSession(Executor)</li>
     * </ul>
     * 4. 执行增删改查方法
     * 5. 总结
     * <ul>
     * <li>1. 根据配置文件（全局，sql映射）初始化出 Configuration 对象</li>
     * <li>2. 创建一个 DefaultSqlSession 对象，他里面包含 Configuration 以及 Executor（根据全局配置文件中的 defaultExecutorType 创建出对应的 Executor）</li>
     * <li>3. DefaultSqlSession.getMapper() 拿到 Mapper 接口对应的 MapperProxy</li>
     * <li>4. MapperProxy里面有（DefaultSqlSession）</li>
     * <li>5. 执行增删改查方法：</li>
     * <ul>
     * <li>1. 调用 DefaultSqlSession 的增删改查（Executor）</li>
     * <li>2. 会创建一个 StatementHandler 对象。（同时也会创建出 ParameterHandler 和 ResultSetHandler）</li>
     * <li>3. 调用 StatementHandler 预编译参数以及设置参数值；使用 ParameterHandler 来给 sql 设置参数</li>
     * <li>4. 调用 StatementHandler 的增删改查方法</li>
     * <li>5. ResultSetHandler 封装结果</li>
     * </ul>
     * </ul>
     * 6. 注意：
     * <ul>
     * <li>1. 四大对象每个创建的时候都有一个 interceptorChain.pluginAll(parameterHandler / resultSetHandler / statementHandler / executor)</li>
     * </ul>
     */
    @Test
    public void testMapper() {
        // 获取 SqlSession 对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            // 获取接口的实现类对象
            // 会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            System.out.println(mapper.getClass().getName()); // com.sun.proxy.$Proxy13
            Employee employee = mapper.getEmpById(1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testMapperAnnotation() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapperAnnotation mapper = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 1. mybatis 允许增删改直接定义以下类型返回值
     * Integer(int) Long(long) Boolean(boolean) void
     * 2. 我们需要手动提交数据
     * sqlSessionFactory.openSession(); ==> 手动提交
     * sqlSessionFactory.openSession(true); ==> 自动提交
     */
    @Test
    public void testInsert() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Employee employee = new Employee();
            employee.setLastName("jerry");
            employee.setEmail("jerry@onevgo.com");
            employee.setGender("1");
            Long rows = mapper.addEmp(employee);
            System.out.println("affected rows: " + rows);
            System.out.println("id: " + employee.getId());

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testUpdate() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

            Employee employee = new Employee();
            employee.setId(1);
            employee.setLastName("AAA");
            employee.setEmail("aaa@onevgo.com");
            employee.setGender("0");
            Long rows = mapper.updateEmp(employee);
            System.out.println("affected rows: " + rows);

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDelete() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Long rows = mapper.deleteEmpById(2);
            System.out.println("affected rows: " + rows);

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testParams() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpByIdAndLastName(1, "AAA");
            System.out.println(employee);

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testParamList() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Integer> ids = Arrays.asList(1);
            Employee employee = mapper.getEmpByIds(ids, "%A%");
            System.out.println(employee);

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testParamMap() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<String, Object> map = new HashMap<>();
            map.put("id", 1);
            map.put("lastName", "AAA");
            map.put("tableName", "tbl_employee");
            Employee employee = mapper.getEmpByMap(map);
            System.out.println(employee);

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testReturnList() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> emps = mapper.getEmpsByLastNameLike("%e%");
            System.out.println(emps);

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testResultMap() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<String, Object> emp = mapper.getEmpByIdReturnMap(1);
            System.out.println(emp);

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testResultMapKey() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Map<Integer, Employee> emps = mapper.getEmpsByLastNameLikeReturnMap("%e%");
            System.out.println(emps);

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testAssociate() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpAndDept(1);
            System.out.println(employee);
            System.out.println(employee.getDept());

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testAssociateStep() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpByIdStep(1);
            System.out.println(employee.getLastName());
            System.out.println("------------------");
            System.out.println(employee.getDept());

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testCollection() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department dept = mapper.getDeptWithEmpsById(1);
            System.out.println(dept.getDepartmentName());
            System.out.println("------------------");
            System.out.println(dept.getEmps());

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testCollectionStep() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
            Department dept = mapper.getDeptWithEmpsStepById(1);
            System.out.println(dept.getDepartmentName());
            System.out.println("------------------");
            System.out.println(dept.getEmps());

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDiscriminator() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee emp = mapper.getEmpByIdDis(1);
            System.out.println(emp);
            System.out.println(emp.getDept());
            System.out.println("------------------");
            emp = mapper.getEmpByIdDis(3);
            System.out.println(emp);
            System.out.println(emp.getDept());

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDynamicSqlIf() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Employee employee = new Employee();
//            employee.setId(3);
            employee.setLastName("%e%");

            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> emps = mapper.getEmpsByConditionIf(employee);
            System.out.println(emps);
            System.out.println("-----------");

            emps = mapper.getEmpsByConditionTrim(employee);
            System.out.println(emps);

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDynamicSqlChoose() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Employee employee = new Employee();
//            employee.setId(3);
            employee.setLastName("%e%");

            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> emps = mapper.getEmpsByConditionChoose(employee);
            System.out.println(emps);

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDynamicSqlSet() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Employee employee = new Employee();
            employee.setId(1);
            employee.setLastName("BBB");
            employee.setGender("0");

            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            mapper.updateEmpBySet(employee);
            System.out.println("---------");
            mapper.updateEmpByTrim(employee);

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDynamicSqlForeach() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> emps = mapper.getEmpsByConditionForeach(Arrays.asList(1, 2, 3, 4));
            System.out.println(emps);
            System.out.println("---------");
            emps = mapper.getEmpsByConditionForeach(new ArrayList<>());
            System.out.println(emps);

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testBatchInsert() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            List<Employee> employeeList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Department department = new Department();
                department.setId(1 + (i % 2));
                Employee employee = new Employee();
                employee.setLastName("Name " + i);
                employee.setEmail("Name_" + i + "@onevgo.com");
                employee.setGender((i % 2) + "");
                employee.setDept(department);

                employeeList.add(employee);
            }

            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            mapper.addEmps(employeeList);

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInnerParam() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            Employee employee = new Employee();
            employee.setLastName("%" + escape("\\") + "%");

            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            List<Employee> emp = mapper.getEmpsByInnerParameter(employee);
            System.out.println(emp);

            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 两级缓存
     * 1. 一级缓存（本地缓存）：sqlSession级别的缓存，一级缓存是一直开启的
     * 与数据库同一次会话期间查询到的数据会放在本地缓存中。
     * 以后如果需要获取相同的数据，直接从缓存中拿，没必要再去查询数据库
     * 一级缓存失效情况（没有使用到一级缓存的情况，效果就是，还需要在向数据库发出查询）
     * <ul>
     * <li>1. sqlSession不同</li>
     * <li>2. sqlSession相同，查询条件不同。（当前一级缓存中还没有这个数据）</li>
     * <li>3. sqlSession相同，两次查询之间执行了增删改操作（这次增删改可能对当前数据有影响）</li>
     * <li>4. sqlSession相同，手动清除了一级缓存（缓存清空）</li>
     * </ul>
     * <p>
     * 2. 二级缓存（全局缓存）：基于namespace级别的缓存，一个namespace对应一个二级缓存
     * 工作机制：
     * <ul>
     * <li>1. 一个会话，查询一条数据，这个数据就会被放在当前会话的一级缓存中</li>
     * <li>2. 如果会话关闭：一级缓存中的数据会被保存到二级缓存中；新的会话查询信息，就可以参照二级缓存中的内容</li>
     * <li>3. sqlSession === EmployeeMapper ==> Employee</li>
     * <li>                  DepartmentMapper ==> Department</li>
     * <li>不同 namespace 查出的数据会被放在自己对应的缓存中（map）</li>
     * <li>效果：数据会从二级缓存中获取</li>
     * <li>查出的数据都会被默认先放在一级缓存中</li>
     * <li>只有会话提交或者关闭以后，一级缓存中的数据才会转移到二级缓存中</li>
     * <li>4. 使用</li>
     * <ul>
     * <li>1. 开启全局二级缓存配置：<setting name="cacheEnabled" value="true" /></li>
     * <li>2. 去mapper.xml中配置使用二级缓存 <cache></cache></li>
     * <li>3. 我们的 POJO 需要实现序列化接口</li>
     * </ul>
     * </ul>
     * <p>
     * 和缓存有关的设置/属性：
     * 1. cacheEnabled=true | false:关闭缓存（二级缓存关闭）
     * 2. 每个 select 标签都有 useCache="true" | false:不使用缓存（一级缓存依然使用，二级缓存不使用）
     * 3. 每个 insert/update/delete 标签的 flushCache="true" （一级二级都会清除）
     * <ul>
     * <li>1. 增删改执行完成后就会清除缓存</li>
     * <li>2. 测试 flushCache="true"，一级缓存就清空了，二级也会清除</li>
     * <li>3. 查询标签 flushCache="false"</li>
     * <ul>
     * <li>1. 如果 flushCache="true"，每次查询之后都会清空缓存，缓存是没有被使用的</li>
     * </ul>
     * </ul>
     * 4. sqlSession.clearCache() 只是清除当前session的一级缓存
     * 5. localCacheScope 本地缓存作用域（一级缓存SESSION），当前会话的所有数据保存在会话缓存中
     * <ul>
     * <li>1. STATEMENT 可以禁用一级缓存</li>
     * </ul>
     * <p>
     * 第三方缓存整合
     * 1. 导入第三方缓存包即可
     * 2. 导入与第三方缓存整合的适配包，官方有
     * 3. mapper.xml 中使用自定义缓存
     * <ul>
     * <li>1. <cache type="org.mybatis.caches.ehcache.EhcacheCache"></cache></li>
     * </ul>
     */
    @Test
    public void testFirstLevelCache() {
        // 1. 获取到的 SqlSession 不会自动提交数据
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee emp = mapper.getEmpById(1);
            System.out.println(emp);
//            sqlSession.clearCache();
            Employee emp1 = mapper.getEmpById(1);
            System.out.println(emp1);
            System.out.println(emp == emp1);
            // 2. 手动提交数据
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testSecondLevelCache() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee emp = mapper.getEmpById(1);
        System.out.println(emp);
        sqlSession.commit();
        sqlSession.close();

        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        EmployeeMapper mapper2 = sqlSession2.getMapper(EmployeeMapper.class);
        Employee emp1 = mapper2.getEmpById(1);
        System.out.println(emp1);
        sqlSession2.commit();
        sqlSession2.close();

        System.out.println(emp == emp1);
    }

    /**
     * 在四大对象创建的时候
     * 1. 每个创建出来的对象不是直接返回的，而是 interceptorChain.pluginAll(parameterHandler)
     * 2. 获取到所有的 Interceptor（拦截器） （插件需要实现的接口），调用 interceptor.plugin(target); 返回 target 包装后的对象
     * 3. 插件机制，我们可以使用插件为目标对象创建一个代理对象：AOP面向切面
     * <ul>
     * <li>1. 我们的插件可以为四大对象创建出代理对象；</li>
     * <li>2. 代理对象就可以拦截到四大对象的每一个执行</li>
     * </ul>
     * <p>
     * 插件编写：
     * 1. 编写 Interceptor 的实现类
     * 2. 使用 @Intercepts 注解完成插件签名
     * 3. 将写好的插件注册到全局配置文件中
     */
    @Test
    public void testPlugin() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee employee = mapper.getEmpById(1);
            System.out.println(employee);
        } finally {
            sqlSession.close();
        }
    }

//    @Test
//    public void testPageHelper() {
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        try {
//            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
//
//            Page<Object> page = PageHelper.startPage(4, 2);
//            List<Employee> emps = mapper.getEmps();
//            // 传入要连续显示多少页
//            PageInfo<Employee> pageInfo = new PageInfo<>(emps, 5);
//            for (Employee e : emps) {
//                System.out.println(e);
//            }
//            /*
//            System.out.println("当前页码：" + page.getPageNum());
//            System.out.println("总记录数：" + page.getTotal());
//            System.out.println("每页的记录数：" + page.getPageSize());
//            System.out.println("总页码：" + page.getPages());
//            */
//            System.out.println("当前页码：" + pageInfo.getPageNum());
//            System.out.println("总记录数：" + pageInfo.getTotal());
//            System.out.println("每页的记录数：" + pageInfo.getPageSize());
//            System.out.println("总页码：" + pageInfo.getPages());
//            System.out.println("是否第一页：" + pageInfo.isIsFirstPage());
//            System.out.println("连续显示的页码：");
//            for (int num : pageInfo.getNavigatepageNums()) {
//                System.out.println(num);
//            }
//            sqlSession.commit();
//        } finally {
//            sqlSession.close();
//        }
//    }

    @Test
    public void testBatch() {
//        SqlSession sqlSession = sqlSessionFactory.openSession(); // 23195ms
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH); // 6928ms
        long start = System.currentTimeMillis();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            for (int i = 0; i < 10000; i++) {
                Employee emp = new Employee();
                emp.setLastName("Batch_" + i);
                emp.setEmail("Batch_" + i + "@onevgo.com");
                emp.setGender("1");
                mapper.addEmp(emp);
            }
            sqlSession.commit();
            long end = System.currentTimeMillis();
            System.out.println("执行时长：" + (end - start));
        } finally {
            sqlSession.close();
        }
    }

    /**
     * 默认 mybatis 在处理枚举对象的时候保存的是枚举的名字 org.apache.ibatis.type.EnumTypeHandler
     * 改变使用 org.apache.ibatis.type.EnumOrdinalTypeHandler
     */
    @Test
    public void testEnum() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            Employee emp = new Employee();
            emp.setLastName("test_enum");
            emp.setEmail("test_enum@onevgo.com");
            emp.setGender("1");
            emp.setEmpStatus(EmpStatus.LOGOUT);
            mapper.addEmp(emp);

            System.out.println("保存成功：" + emp.getId());

            Employee emp1 = mapper.getEmpById(emp.getId());
            System.out.println(emp1.getEmpStatus());
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    public String escape(String s) {
        if (s != null) {
            s = s.replace("\\", "\\\\")
                    .replace("_", "\\_")
                    .replace("%", "\\%");
        }
        return s;
    }
}
