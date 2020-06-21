package com.onevgo.sssp;

import com.onevgo.sssp.entity.Department;
import com.onevgo.sssp.entity.Employee;
import com.onevgo.sssp.repository.DepartmentRepository;
import com.onevgo.sssp.repository.EmployeeRepository;
import org.hibernate.jpa.QueryHints;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class SSSPTest {
    private ApplicationContext applicationContext;
    private DepartmentRepository departmentRepository;
    private EmployeeRepository employeeRepository;
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() throws Exception {
        applicationContext = new ClassPathXmlApplicationContext("spring-sssp.xml");
        employeeRepository = applicationContext.getBean(EmployeeRepository.class);
        departmentRepository = applicationContext.getBean(DepartmentRepository.class);
        entityManagerFactory = applicationContext.getBean(EntityManagerFactory.class);
    }

    @After
    public void tearDown() throws Exception {
        entityManagerFactory.close();
    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testInsertEmployees() {
        Random random = new Random();
        for (int i = 'a'; i < 'z'; i++) {
            int j = random.nextInt(4) + 1;
            Department department = departmentRepository.findOne(j);

            Employee employee = new Employee();
            employee.setLastName((char) i + "" + (char) i);
            employee.setEmail((char) i + "" + (char) i + "@onevgo.com");
            employee.setBirth(new Date());
            employee.setCreateTime(new Date());
            employee.setDepartment(department);

            employeeRepository.save(employee);
        }
    }

    @Test
    public void testRepositorySecondLevelCache() {
        List<Department> departments = departmentRepository.getAll();
        departments = departmentRepository.getAll();
    }

    @Test
    public void testJpaSecondLevelCache() {
        String jpql = "select d from Department d";

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE, true);
        List<Department> departments = query.getResultList();
        entityManager.close();

        entityManager = entityManagerFactory.createEntityManager();
        query = entityManager.createQuery(jpql).setHint(QueryHints.HINT_CACHEABLE, true);
        departments = query.getResultList();
        entityManager.close();
    }

    @Test
    public void testFirstEmployeeByLastName() {
        String lastName = "aa";
        Employee employee = employeeRepository.getFirstByLastName(lastName);
        System.out.println(employee);
    }
}
