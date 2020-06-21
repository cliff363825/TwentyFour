package com.onevgo.jpa;

import com.onevgo.jpa.helloworld.*;
import org.hibernate.jpa.QueryHints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JPATest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;

    @Before
    public void setUp() throws Exception {
        // 1. 创建 EntityManagerFactory
        String persistenceUnitName = "jpa-1";
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.show_sql", true);
        entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName, properties);

        // 2. 创建 EntityManager。类似于 Hibernate 的 SessionFactory
        entityManager = entityManagerFactory.createEntityManager();

        // 3. 开启事务
        transaction = entityManager.getTransaction();
        transaction.begin();

        // 4. 进行持久化操作
    }

    @After
    public void tearDown() throws Exception {
        // 5. 提交事务
        transaction.commit();

        // 6. 关闭 EntityManager
        entityManager.close();

        // 7. 关闭 EntityManagerFactory
        entityManagerFactory.close();
    }

    /**
     * 类似于 hibernate 中 Session 的 get 方法
     */
    @Test
    public void testFind() {
        // select ... from table where pk = ?;
        Customer customer = entityManager.find(Customer.class, 1);
        Customer customer2 = entityManager.find(Customer.class, 1);
        System.out.println("------------------");
        System.out.println(customer);
        System.out.println(customer == customer2); // true
    }

    /**
     * 类似于 hibernate 中 Session 的 load 方法 懒加载
     */
    @Test
    public void testGetReference() {
        // select ... from table where pk = ?;
        Customer customer = entityManager.getReference(Customer.class, 1);
        System.out.println(customer.getClass()); // class com.onevgo.jpa.helloworld.Customer_$$_jvst510_2

        // org.hibernate.LazyInitializationException: could not initialize proxy - no Session
        // 懒加载异常
//        transaction.commit();
//        entityManager.close();

        System.out.println("------------------");
        System.out.println(customer);
    }

    /**
     * 类似于 hibernate 的 save 方法。使对象有临时状态变为持久化状态。
     * 和 Hibernate 的 save 方法的不同之处：若对象有id，则不能执行 insert 操作，而会抛出异常
     */
    @Test
    public void testPersist() {
        Customer customer = new Customer();
        customer.setAge(15);
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());
        customer.setEmail("bb@163.com");
        customer.setLastName("BB");
//        customer.setId(100);

        // insert into table columns values (?,?,?);
        entityManager.persist(customer);
        System.out.println(customer.getId());

        // update table set age = ? where pk = ?;
        customer.setAge(25);
    }

    /**
     * 类似于 hibernate 中 Session 的 delete 方法，把对象对应的记录从数据库中移除
     * 但注意：该方法只能移除 持久化 对象。而 hibernate 的 delete 方法实际上还可以移除 游离对象
     */
    @Test
    public void testRemove() {
//        Customer customer = new Customer();
//        customer.setId(2);

        // select * from table where pk = 2;
        Customer customer = entityManager.find(Customer.class, 2);

        // delete from table where pk = 2;
        entityManager.remove(customer);
    }

    /**
     * 总的来说，类似于 hibernate Session 的 saveOrUpdate 方法
     * 1. 若传入的是一个临时对象，把临时对象的属性赋值到新的对象中，然后对新的对象执行持久化操作。所以
     * 新的对象中有 id，但以前的临时对象中没有id
     */
    @Test
    public void testMerge() {
        Customer customer = new Customer();
        customer.setAge(18);
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());
        customer.setEmail("cc@163.com");
        customer.setLastName("CC");

        // insert into table columns values(?,?,?);
        Customer customer2 = entityManager.merge(customer);

        System.out.println("customer#id: " + customer.getId());
        System.out.println("customer2#id: " + customer2.getId());
    }

    /**
     * 若传入的是一个游离对象，即传入的对象有 OID。
     * 1. 若在 EntityManager 缓存中没有该对象
     * 2. 若在数据库中也没有对应的记录
     * 3. JPA 会创建一个新的对象，然后把当前游离对象的属性复制到新创建的对象中
     * 4. 对新创建的对象执行 insert 操作
     */
    @Test
    public void testMerge2() {
        Customer customer = new Customer();
        customer.setAge(18);
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());
        customer.setEmail("dd@163.com");
        customer.setLastName("DD");
        customer.setId(100);

        // insert into table columns values(?,?,?)
        Customer customer2 = entityManager.merge(customer);

        System.out.println("customer#id: " + customer.getId());
        System.out.println("customer2#id: " + customer2.getId());
    }

    /**
     * 若传入的是一个游离对象，即传入的对象有 OID。
     * 1. 若在 EntityManager 缓存中没有该对象
     * 2. 若在数据库中也有对应的记录
     * 3. JPA 会查询对应的记录，然后返回该记录对一个的对象，再然后会把游离对象的属性复制到查询到的对象中。
     * 4. 对查询到的对象执行 update 操作
     */
    @Test
    public void testMerge3() {
        Customer customer = new Customer();
        customer.setAge(18);
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());
        customer.setEmail("ee@163.com");
        customer.setLastName("EE");
        customer.setId(6);

        // update table set column1=?,column2=? where pk = ?;
        Customer customer2 = entityManager.merge(customer);

        System.out.println(customer == customer2); // false
        System.out.println("customer#id: " + customer.getId());
        System.out.println("customer2#id: " + customer2.getId());
    }

    /**
     * 若传入的是一个游离对象，即传入的对象有 OID。
     * 1. 若在 EntityManager 缓存中有对应的对象
     * 3. JPA 会把游离对象的属性复制到查询到 EntityManager 缓存中的对象中。
     * 4. EntityManager 缓存中的对象执行 UPDATE
     */
    @Test
    public void testMerge4() {
        Customer customer = new Customer();
        customer.setAge(18);
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());
        customer.setEmail("dd@163.com");
        customer.setLastName("DD");
        customer.setId(6);

        Customer customer2 = entityManager.find(Customer.class, customer.getId());
        Customer customer3 = entityManager.merge(customer);

        System.out.println(customer3 == customer2); // true
        System.out.println("customer#id: " + customer.getId());
        System.out.println("customer2#id: " + customer2.getId());
        System.out.println("customer3#id: " + customer3.getId());
    }

    /**
     * 同 hibernate 中 Session 的 flush 方法
     */
    @Test
    public void testFlush() {
        Customer customer = entityManager.find(Customer.class, 1);
        System.out.println(customer);

        customer.setLastName("AA");

        entityManager.flush();
    }

    /**
     * 同 hibernate 中 Session 的 refresh 方法
     */
    @Test
    public void testRefresh() {
        Customer customer = entityManager.find(Customer.class, 1);
        customer = entityManager.find(Customer.class, 1);

        entityManager.refresh(customer);
    }

    /**
     * 保存多对一时，建议先保存 1 的一端，后保存 n 的一端，这样不会多出额外的 UPDATE 语句
     */
    @Test
    public void testManyToOnePersist() {
        Customer customer = new Customer();
        customer.setAge(18);
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());
        customer.setEmail("ff@163.com");
        customer.setLastName("FF");

        Order order1 = new Order();
        order1.setOrderName("O-FF-1");

        Order order2 = new Order();
        order2.setOrderName("O-FF-2");

        // 设置关联关系
        order1.setCustomer(customer);
        order2.setCustomer(customer);

        // 执行保存操作
        entityManager.persist(customer);
        entityManager.persist(order1);
        entityManager.persist(order2);
    }

    /**
     * 默认情况下，使用左外连接的方式来获取 n 的一段的对象和其关联的 1 的一端的对象
     * 可以使用 @ManyToOne 的 fetch 属性来修改默认的关联属性的加载策略
     */
    @Test
    public void testManyToOneFind() {
        Order order = entityManager.find(Order.class, 1);
        System.out.println(order.getOrderName());
        System.out.println(order.getCustomer().getLastName());
    }

    /**
     * 不能直接删除 1 的一端，因为有外键约束
     */
    @Test
    public void testManyToOneRemove() {
//        Order order = entityManager.find(Order.class, 1);
//        entityManager.remove(order);

        Customer customer = entityManager.find(Customer.class, 10);
        entityManager.remove(customer);
    }

    @Test
    public void testManyToOneUpdate() {
        Order order = entityManager.find(Order.class, 2);
        order.getCustomer().setLastName("FFF");
    }

    /**
     * 单向 1-n 关联关系执行保存时，一定会多出 update 语句
     * 因为 n 的一端在插入时不会同时插入外键列
     * <p>
     * 若是双向 1-n 的关联关系，执行保存时
     * 若先保存 n 的一端，在保存 1 的一端，默认情况下，会多出 n 条 update 语句
     * 若先保存 1 的一端，则会多出 2 条 update 语句
     * 在进行双向 1-n 关联关系时，建议使用 n 的一方来维护关联关系，而 1 的一方不维护关联关系，这样会有效的减少 SQL 语句
     * 注意：若在 1 的一端的 @OneToMany 中使用 mappedBy 属性，则 @OneToMany 端就不能在使用 @JoinColumn 属性了
     */
    @Test
    public void testOneToManyPersist() {
        Customer customer = new Customer();
        customer.setAge(18);
        customer.setBirth(new Date());
        customer.setCreatedTime(new Date());
        customer.setEmail("zz@163.com");
        customer.setLastName("ZZ");

        Order order1 = new Order();
        order1.setOrderName("O-ZZ-1");

        Order order2 = new Order();
        order2.setOrderName("O-ZZ-2");

        // 建立关联关系
        customer.getOrders().add(order1);
        customer.getOrders().add(order2);

        order1.setCustomer(customer);
        order2.setCustomer(customer);

        // 执行保存操作
        entityManager.persist(customer);
        entityManager.persist(order1);
        entityManager.persist(order2);
    }

    /**
     * 默认对关联的多的一方使用懒加载的加载策略
     * 可以使用 @OneToMany 的 fetch 属性来修改默认的加载策略
     */
    @Test
    public void testOneToManyFind() {
        Customer customer = entityManager.find(Customer.class, 12);
        System.out.println(customer.getLastName());
        System.out.println(customer.getOrders().size());
    }

    /**
     * 默认情况下，若删除 1 的一端，则先把关联的 n 的一端的外键置空，然后进行删除
     * 可以通过 @OneToMany 的 cascade 属性来修改默认的删除策略
     */
    @Test
    public void testOneToManyRemove() {
        Customer customer = entityManager.find(Customer.class, 11);
        entityManager.remove(customer);
    }

    @Test
    public void testOneToManyUpdate() {
        Customer customer = entityManager.find(Customer.class, 13);
        customer.getOrders().iterator().next().setOrderName("O-XXX-13");
    }

    /**
     * 双向 1-1的关联关系，建议现保存不维护关联关系的一方，即没有外键的一方，这样不会多出 update 语句
     */
    @Test
    public void testOneToOnePersistence() {
        Manager manager = new Manager();
        manager.setMgrName("M-AA");

        Department department = new Department();
        department.setDeptName("D-AA");

        // 设置关联关系
        manager.setDept(department);
        department.setMgr(manager);

        // 执行保存操作
        entityManager.persist(manager);
        entityManager.persist(department);
    }

    /**
     * 1. 默认情况下，若获取维护关联关系的一方，则会通过左外连接获取其关联的对象
     * 但可以通过 @OneToOne 的 fetch 属性来修改加载策略
     */
    @Test
    public void testOneToOneFind() {
        Department department = entityManager.find(Department.class, 1);
        System.out.println(department.getDeptName());
        System.out.println(department.getMgr().getClass());
    }

    /**
     * 1. 默认情况下，若获取不维护关联关系的一方，则也会通过左外连接获取其关联的对象
     * 可以通过 @OneToOne 的 fetch 属性来修改加载策略，但依然会在发送 SQL 语句来初始化其关联的对象
     * 这说明在不维护关联关系的一方，不建议修改 fetch 属性
     */
    @Test
    public void testOneToOneFind2() {
        Manager manager = entityManager.find(Manager.class, 1);
        System.out.println(manager.getMgrName());
        System.out.println(manager.getDept().getClass());
    }

    /**
     * 多对多的保存
     */
    @Test
    public void testManyToManyPersistence() {
        Item i1 = new Item();
        i1.setItemName("i-1");

        Item i2 = new Item();
        i2.setItemName("i-2");

        Category c1 = new Category();
        c1.setCategoryName("C-1");

        Category c2 = new Category();
        c2.setCategoryName("C-2");

        // 设置关联关系
        i1.getCategories().add(c1);
        i1.getCategories().add(c2);

        i2.getCategories().add(c1);
        i2.getCategories().add(c2);

        c1.getItems().add(i1);
        c1.getItems().add(i2);

        c2.getItems().add(i1);
        c2.getItems().add(i2);

        // 执行保存
        entityManager.persist(i1);
        entityManager.persist(i2);
        entityManager.persist(c1);
        entityManager.persist(c2);
    }

    /**
     * 对于关联的集合对象，默认使用懒加载的策略
     * 使用维护关联关系的一方获取，还是使用不维护关联关系的一方获取，SQL 语句相同。
     */
    @Test
    public void testManyToManyFind() {
//        Item item = entityManager.find(Item.class, 1);
//        System.out.println(item.getItemName());
//        System.out.println(item.getCategories().size());

        Category category = entityManager.find(Category.class, 1);
        System.out.println(category.getCategoryName());
        System.out.println(category.getItems().size());
    }

    @Test
    public void testSecondLevelCache() {
        Customer customer1 = entityManager.find(Customer.class, 1);

        transaction.commit();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();

        Customer customer2 = entityManager.find(Customer.class, 1);
    }

    @Test
    public void testHelloJPQL() {
        String jpql = "select c FROM Customer c WHERE c.age > ?1";
        Query query = entityManager.createQuery(jpql);

        // 占位符的索引是从1开始
        query.setParameter(1, 1);
        List<Customer> customers = query.getResultList();
        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    @Test
    public void testPartlyProperties1() {
        String jpql = "SELECT c.lastName, c.age FROM Customer c WHERE c.id > ?1";
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1, 1);
        /*
        List<Customer> customers = query.getResultList();
        // java.lang.ClassCastException: [Ljava.lang.Object; cannot be cast to com.onevgo.jpa.helloworld.Customer
        for (Customer c : customers) {
            System.out.println(c);
        }
        */
        List<Object[]> customers = query.getResultList();
        for (Object[] c : customers) {
            System.out.println(c[0]);
            System.out.println(c[1]);
        }
    }

    /**
     * 默认情况下，若只查询部分属性，则将返回 Object[] 类型的结果。或者 Object[] 类型的 List.
     * 也可以在实体类中创建对应的构造器，然后在 JPQL 语句中利用对应的构造器返回实体类的对象
     */
    @Test
    public void testPartlyProperties2() {
        String jpql = "SELECT new Customer(c.lastName, c.age) FROM Customer c WHERE c.id > ?1";
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1, 1);
        List<Customer> customers = query.getResultList();
        System.out.println(customers);
    }

    /**
     * createNamedQuery 适用于在实体类前使用 @NamedQuery 标记的查询语句
     */
    @Test
    public void testNamedQuery() {
        Query query = entityManager.createNamedQuery("testNamedQuery");
        query.setParameter(1, 1);
        Customer customer = (Customer) query.getSingleResult();
        System.out.println(customer);
    }

    /**
     * createNativeQuery 适用于本地SQL
     */
    @Test
    public void testNativeQuery() {
        String sql = "select age from jpa_customers where id = ?1";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter(1, 1);
        Integer age = (Integer) query.getSingleResult();
        System.out.println(age);
    }

    /**
     * 使用 hibernate 的查询缓存
     */
    @Test
    public void testQueryCache() {
        String jpql = "select c FROM Customer c WHERE c.age > ?1";
        Query query = entityManager.createQuery(jpql);
        query.setHint(QueryHints.HINT_CACHEABLE, true);

        // 占位符的索引是从1开始
        query.setParameter(1, 1);
        List<Customer> customers = query.getResultList();
        System.out.println(customers.size());

        query = entityManager.createQuery(jpql);
        query.setHint(QueryHints.HINT_CACHEABLE, true);

        // 占位符的索引是从1开始
        query.setParameter(1, 1);
        customers = query.getResultList();
        System.out.println(customers.size());
    }

    @Test
    public void testOrderBy() {
        String jpql = "select c FROM Customer c WHERE c.age > ?1 order by c.age desc";
        Query query = entityManager.createQuery(jpql);

        // 占位符的索引是从1开始
        query.setParameter(1, 1);
        List<Customer> customers = query.getResultList();
        System.out.println(customers.size());
    }

    /**
     * 查询 order 数量大于 2 的哪些 Customer
     */
    @Test
    public void testGroupBy() {
        String jpql = "select o.customer from Order o group by o.customer having count(o.id) > 1";
        Query query = entityManager.createQuery(jpql);
        List<Customer> customers = query.getResultList();
        System.out.println(customers);
    }

    /**
     * JPQL 的关联查询同 HQL 的关联查询
     */
    @Test
    public void testLeftOuterJoinFetch() {
//        String jpql = "select c from Customer c left outer join c.orders where c.id = ?1";
        String jpql = "from Customer c left outer join c.orders where c.id = ?1";

        Query query = entityManager.createQuery(jpql);
        query.setParameter(1, 14);
//        Customer customer = (Customer) query.getSingleResult(); // javax.persistence.NonUniqueResultException
        List<Object[]> customers = query.getResultList();

        for (Object[] c : customers) {
            System.out.println(c[0]);
            System.out.println(c[1]);
        }
    }

    @Test
    public void testSubQuery() {
        // 查询所有 Customer 的 lastName 为 YY 的 Order
        String jpql = "select o from Order o where o.customer=(select c from Customer c where c.lastName=?1)";
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1, "YY");
        List<Order> orders = query.getResultList();
        System.out.println(orders.iterator().next());
    }

    @Test
    public void testJpqlFunction() {
        String jpql = "select upper(c.email) from Customer c";

        Query query = entityManager.createQuery(jpql);
        List emails = query.getResultList();
        System.out.println(emails);
    }

    @Test
    public void testPagination() {
        String jpql = "select c from Customer c";
        Query query = entityManager.createQuery(jpql);
        query.setFirstResult(2); // offset
        query.setMaxResults(2); // limit
        List<Customer> customers = query.getResultList();
        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    /**
     * 可以使用 JPQL 完成 update 和 delete 操作
     */
    @Test
    public void testExecuteUpdate() {
        String jpql = "update Customer c set c.lastName = ?1 where c.id = ?2";
        Query query = entityManager.createQuery(jpql);
        query.setParameter(1, "YYY")
                .setParameter(2, 14);
        query.executeUpdate();
    }
}
