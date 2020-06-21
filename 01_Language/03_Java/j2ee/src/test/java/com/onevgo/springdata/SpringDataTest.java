package com.onevgo.springdata;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

public class SpringDataTest {
    private ApplicationContext applicationContext;
    private PersonRepository personRepository;
    private PersonService personService;
    private AddressService addressService;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring-data.xml");
        personRepository = applicationContext.getBean(PersonRepository.class);
        personService = applicationContext.getBean(PersonService.class);
        addressService = applicationContext.getBean(AddressService.class);
    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testJpa() {

    }

    @Test
    public void testHelloWorldSpringData() {
        System.out.println(personRepository);

        Person person = personRepository.findByLastName("AA");
        System.out.println(person);
    }

    @Test
    public void testKeywords() {
        List<Person> personList = personRepository.getByLastNameStartingWithAndIdLessThan("xx", 10);
        System.out.println(personList);

        List<Person> personList2 = personRepository.getByLastNameEndingWithAndIdLessThan("xx", 10);
        System.out.println(personList2);

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 1);
        System.out.println(c);
        List<Person> personList3 = personRepository.getByEmailInOrBirthLessThan(Arrays.asList("aa@163.com", "bb@163.com"), c.getTime());
        System.out.println(personList3);
    }

    @Test
    public void testKeywords2() {
        List<Person> personList = personRepository.getByAddress_IdGreaterThan(1);
        System.out.println(personList);
    }

    @Test
    public void testQueryAnnotation() {
        Person person = personRepository.getMaxIdPerson();
        System.out.println(person);
    }

    @Test
    public void testQueryAnnotationParams1() {
        List<Person> personList = personRepository.testQueryAnnotationParams1("AA", "aa@163.com");
        System.out.println(personList);
    }

    @Test
    public void testQueryAnnotationParams2() {
        List<Person> personList = personRepository.testQueryAnnotationParams2("aa@163.com", "AA");
        System.out.println(personList);
    }

    @Test
    public void testQueryAnnotationLikeParam() {
        List<Person> personList = personRepository.testQueryAnnotationLikeParam("A", "163.com");
        System.out.println(personList);

        List<Person> personList2 = personRepository.testQueryAnnotationLikeParam2("163.com", "A");
        System.out.println(personList2);
    }

    @Test
    public void testNativeQuery() {
        Long totalCount = personRepository.getTotalCount();
        System.out.println(totalCount);
    }

    @Test
    public void testModifying() {
//        personRepository.updatePersonEmail(1, "bb@163.com");
        personService.updatePersonEmail("bb@163.com", 1);
    }

    @Test
    public void testCrudRepository() {
        List<Person> personList = new ArrayList<>();

        Address address = addressService.findAddressById(1);

        for (int i = 'a'; i < 'z'; i++) {
            Person person = new Person();
            person.setAddress(address);
            person.setBirth(new Date());
            person.setEmail((char)i + "" + (char)i + "@onevgo.com");
            person.setLastName((char)i + "" + (char)i);

            personList.add(person);
        }

        personService.savePersons(personList);
    }

    @Test
    public void testPagingAndSortingRepository() {
        // pageNo 从 0 开始
        int pageNo = 3;
        int pageSize = 5;

        // Pageable 接口通常使用的其 PageRequest 实现类，其中封装了需要分页的信息
        // 排序相关的.Sort 封装了排序的信息
        // Order 是具体针对某一个属性进行升序还是降序
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC, "email");
        Sort sort = new Sort(order1, order2);

        Pageable pageable = new PageRequest(pageNo, pageSize, sort);
        Page<Person> page = personRepository.findAll(pageable);

        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("当前第几页：" + (page.getNumber() + 1));
        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("当前页面的 List：" + page.getContent());
        System.out.println("当前页面的记录数：" + page.getNumberOfElements());
    }

    @Test
    public void testJpaRepository() {
        Person person = new Person();
        person.setBirth(new Date());
        person.setEmail("XY@163.com");
        person.setLastName("xyz");
        person.setId(28);

        Person person1 = personRepository.saveAndFlush(person);
        System.out.println(person1 == person);
    }

    // 目标：实现带查询条件的分页。id > 5 的条件
    // 调用 JpaSpecificationExecutor 的 Page<T> findAll(Specification<T> spec, Pageable pageable);
    // Specification: 封装了 JPA Criteria 查询的查询条件
    // Pageable: 封装了请求分页的信息：例如 pageNo，pageSize，Sort
    @Test
    public void testJpaSpecificationExecutor() {
        int pageNo = 0;
        int pageSize = 5;
        PageRequest pageable = new PageRequest(pageNo, pageSize);

        // 通常使用 Specification 的匿名内部类
        Specification<Person> specification = new Specification<Person>() {
            /**
             * @param root 代表查询的实体类
             * @param query 可以从中得到 Root 对象，即告知 JPA Criteria 查询要查询哪一个实体类，还可以来添加查询条件，还可以结合 EntityManager 对象得到最终查询的 TypedQuery 对象
             * @param cb CriteriaBuilder 对象。用于创建 Criteria 相关对象的工厂，当然可以从中获取到 Predicate 对象
             * @return Predicate 类型，代表一个查询条件
             */
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = null;
                predicate = cb.and(cb.gt(root.get("id"), 5), cb.lt(root.get("id"), 10));
                predicate = cb.or(predicate, cb.like(root.get("lastName"), "%yy%"));

                return predicate;
            }
        };

        Page<Person> page = personRepository.findAll(specification, pageable);

        System.out.println("总记录数：" + page.getTotalElements());
        System.out.println("当前第几页：" + (page.getNumber() + 1));
        System.out.println("总页数：" + page.getTotalPages());
        System.out.println("当前页面的 List：" + page.getContent());
        System.out.println("当前页面的记录数：" + page.getNumberOfElements());
    }

    @Test
    public void testCustomRepositoryMethod() {
        personRepository.test();
    }
}
