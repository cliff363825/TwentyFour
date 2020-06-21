package com.onevgo.jooq;

import com.onevgo.jooq.service.EmployeeService;
import com.onevgo.jooq.tables.TblDept;
import com.onevgo.jooq.tables.TblEmployee;
import org.jooq.DSLContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import static com.onevgo.jooq.Tables.TBL_DEPT;
import static com.onevgo.jooq.Tables.TBL_EMPLOYEE;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring-jooq.xml"})
public class JooqSpringTest {
    @Autowired
    private DSLContext dsl;
    @Autowired
    private DataSourceTransactionManager txMgr;
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void testJoin() {
        TblEmployee emp = TBL_EMPLOYEE.as("emp");
        TblDept dept = TBL_DEPT.as("dept");

        dsl.select(emp.ID, emp.LAST_NAME, dept.DEPT_NAME)
                .from(emp)
                .join(dept).on(dept.ID.eq(emp.D_ID))
                .limit(10)
                .forEach(r -> System.out.println(r.get(emp.ID) + "|" + r.get(emp.LAST_NAME) + "|" + r.get(dept.DEPT_NAME)));
    }

    @Test
    public void testTransaction() {
        TransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus transaction = txMgr.getTransaction(definition);
        try {
            for (int i = 0; i < 2; i++) {
                int execute = dsl.insertInto(TBL_EMPLOYEE,
                        TBL_EMPLOYEE.LAST_NAME, TBL_EMPLOYEE.EMAIL, TBL_EMPLOYEE.GENDER)
                        .values("test" + i, "test" + i + "@test.com", "1")
                        .execute();
            }
            int j = 10 / 0;
            txMgr.commit(transaction);
        } catch (Exception e) {
            txMgr.rollback(transaction);
            e.printStackTrace();
        }
    }

    @Test
    public void testTransactional() {
        employeeService.addEmployee("test", "test@test.com", "1");
    }
}
