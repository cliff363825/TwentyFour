package com.onevgo.jooq;

import static com.onevgo.jooq.Tables.*;
import static org.jooq.impl.DSL.*;

import com.onevgo.jooq.tables.TblDept;
import com.onevgo.jooq.tables.TblEmployee;
import com.onevgo.jooq.tables.records.TblEmployeeRecord;
import org.jooq.*;
import org.jooq.impl.CustomField;
import org.junit.Before;
import org.junit.Test;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.*;

public class JooqTest {

    @Before
    public void setUp() throws Exception {
        System.setProperty("org.jooq.no-logo", "true");
    }

    @Test
    public void testConnection() throws Exception {
        System.out.println(getConnection());
    }

    protected Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/default";
        String user = "root";
        String password = "root";
        return DriverManager.getConnection(url, user, password);
    }

    @Test
    public void testGetSql() throws Exception {
        String sql = using(getConnection(), SQLDialect.MYSQL)
                .select(field("*"))
                .from(table("employee"))
                .where(field("id").eq(1))
                .getSQL();
        System.out.println(sql);
    }

    @Test
    public void test01() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        Result<Record> result = create.select()
                .from(TBL_EMPLOYEE)
                .limit(1)
                .fetch();
        System.out.println(result);

        for (Record r : result) {
            Integer id = r.getValue(TBL_EMPLOYEE.ID);
            String lastName = r.getValue(TBL_EMPLOYEE.LAST_NAME);
            String email = r.getValue(TBL_EMPLOYEE.EMAIL);
            System.out.println("id => " + id + ", lastName => " + lastName + ", email => " + email);
        }
    }

    @Test
    public void test02() throws Exception {
        String sql = "select * from tbl_employee limit 0,100";
        using(getConnection(), SQLDialect.MYSQL)
                .fetch(sql)
                .map(Record::intoMap)
                .forEach(System.out::println);
    }

    @Test
    public void testParameterTypes() throws Exception {
        Param<String> x = val("x");
        Param<Integer> id = param("id", 1);

        Result<Record1<String>> result = using(getConnection(), SQLDialect.MYSQL)
                .select(TBL_EMPLOYEE.LAST_NAME.concat(x))
                .from(TBL_EMPLOYEE)
                .where(TBL_EMPLOYEE.ID.eq(id))
                .fetch();
        System.out.println(result);
    }

    @Test
    public void testAttachRecords() throws Exception {
        TblEmployeeRecord tblEmployeeRecord = using(getConnection(), SQLDialect.MYSQL)
                .selectFrom(TBL_EMPLOYEE)
                .where(TBL_EMPLOYEE.ID.eq(1))
                .fetchOne();

        tblEmployeeRecord.setLastName("JOOQ");
        tblEmployeeRecord.store();
    }

    @Test
    public void testIn() throws Exception {
        List<Integer> ids = Arrays.asList(1, 2, 3);
        Result<Record> records = using(getConnection(), SQLDialect.MYSQL)
                .select()
                .from(TBL_EMPLOYEE)
                .where(TBL_EMPLOYEE.ID.in(ids))
                .fetch();
        System.out.println(records);
    }

    @Test
    public void testQueryPart01() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        SelectQuery<Record> query = create.selectQuery();
        query.addFrom(TBL_EMPLOYEE);

        boolean join = true;
        if (join) {
            query.addJoin(TBL_DEPT, TBL_DEPT.ID.eq(TBL_EMPLOYEE.D_ID));
        }

        query.addLimit(100);

        Result<Record> records = query.fetch();
        System.out.println(records);
    }

    @Test
    public void testQueryPart02() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        SelectJoinStep<Record> select = create.select().from(TBL_EMPLOYEE);

        boolean join = true;
        if (join) {
            select.join(TBL_DEPT).on(TBL_DEPT.ID.eq(TBL_EMPLOYEE.D_ID));
        }

        select.limit(100);
        Result<Record> records = select.fetch();
        System.out.println(records);
    }

    @Test
    public void testSelect() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        Field<Integer> c = count().as("c");
        Result<Record3<Integer, String, Integer>> result = create.select(TBL_DEPT.ID, TBL_DEPT.DEPT_NAME, c)
                .from(TBL_DEPT)
                .join(TBL_EMPLOYEE).on(TBL_EMPLOYEE.D_ID.eq(TBL_DEPT.ID))
                .groupBy(TBL_DEPT.ID)
                .having(c.gt(1))
                .orderBy(c.asc())
                .fetch();
        System.out.println(result);
    }

    @Test
    public void testSelectFrom() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        TblEmployeeRecord result = create.selectFrom(TBL_EMPLOYEE)
                .where(TBL_EMPLOYEE.ID.eq(1))
                .fetchAny();
        System.out.println(result);
    }

    @Test
    public void testSelect01() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);

        SelectSelectStep<Record2<String, String>> select1 = create.select(TBL_EMPLOYEE.LAST_NAME, TBL_EMPLOYEE.EMAIL);
        SelectSelectStep<Record2<String, String>> select2 = create.select(TBL_EMPLOYEE.LAST_NAME, trim(TBL_EMPLOYEE.EMAIL));

        SelectSelectStep<Record1<Integer>> select3 = create.selectCount();
        SelectSelectStep<Record1<Integer>> select4 = create.selectZero();
        SelectSelectStep<Record1<Integer>> select5 = create.selectOne();

        SelectSelectStep<Record1<String>> select6 = create.selectDistinct(TBL_EMPLOYEE.LAST_NAME);
    }

    @Test
    public void testSelect02() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);

        create.select().from(TBL_EMPLOYEE).fetch();

        create.select().from(TBL_EMPLOYEE, TBL_DEPT).fetch();
        create.select().from(TBL_EMPLOYEE).crossJoin(TBL_DEPT).fetch();

        create.select().from(table(name("tbl_employee"))).fetch();
    }

    @Test
    public void testFrom01() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);

        create.selectOne().from(TBL_EMPLOYEE).fetch();
        create.selectOne().from(TBL_EMPLOYEE, TBL_DEPT).fetch();
        create.selectOne().from(TBL_EMPLOYEE.as("emp"), TBL_DEPT.as("d")).fetch();
    }

    @Test
    public void testJoin01() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        create.select()
                .from(TBL_EMPLOYEE.join(TBL_DEPT).on(TBL_DEPT.ID.eq(TBL_EMPLOYEE.D_ID)))
                .fetch();
    }

    @Test
    public void testJoin02() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        create.select()
                .from(TBL_EMPLOYEE)
                .join(TBL_DEPT).on(TBL_DEPT.ID.eq(TBL_EMPLOYEE.D_ID))
                .fetch();
    }

    @Test
    public void testSemiJoin() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        create.select()
                .from(TBL_EMPLOYEE)
                .leftSemiJoin(TBL_DEPT).on(TBL_DEPT.ID.eq(TBL_EMPLOYEE.D_ID))
                .fetch();
    }

    @Test
    public void testAntiJoin() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        create.select()
                .from(TBL_EMPLOYEE)
                .leftAntiJoin(TBL_DEPT).on(TBL_DEPT.ID.eq(TBL_EMPLOYEE.D_ID))
                .fetch();
    }

    @Test
    public void testWhere() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        create.select()
                .from(TBL_DEPT)
                .where(TBL_DEPT.ID.eq(1))
                .and(TBL_DEPT.DEPT_NAME.like("%开发%"))
                .fetch();
    }

    @Test
    public void testGroupBy() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        create.select(TBL_EMPLOYEE.D_ID, count().as("c"))
                .from(TBL_EMPLOYEE)
                .groupBy(TBL_EMPLOYEE.D_ID)
                .having(field(name("c")).gt(1))
                .fetch();
    }

    @Test
    public void testOrderBy01() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        create.select(TBL_EMPLOYEE.ID, TBL_EMPLOYEE.LAST_NAME)
                .from(TBL_EMPLOYEE)
                .orderBy(TBL_EMPLOYEE.ID.asc(), TBL_EMPLOYEE.LAST_NAME.desc())
                .fetch();
    }

    @Test
    public void testOrderBy02() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        create.select(TBL_EMPLOYEE.ID, TBL_EMPLOYEE.LAST_NAME)
                .from(TBL_EMPLOYEE)
                .orderBy(one().asc(), inline(2).desc())
                .fetch();
    }

    @Test
    public void testOrderBy03() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        create.select(TBL_EMPLOYEE.LAST_NAME, TBL_EMPLOYEE.D_ID)
                .from(TBL_EMPLOYEE)
                .orderBy(one().asc(), inline(2).desc().nullsFirst())
                .fetch();
    }

    @Test
    public void testOrderBy04() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        create.select(TBL_EMPLOYEE.ID, TBL_EMPLOYEE.LAST_NAME)
                .from(TBL_EMPLOYEE)
                .orderBy(
                        choose(TBL_EMPLOYEE.LAST_NAME)
                                .when("JOOQ", 0)
                                .when("Batch_0", 1)
                                .otherwise(2)
                                .asc()
                )
                .fetch();
    }

    @Test
    public void testOrderBy05() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        create.select(TBL_EMPLOYEE.ID, TBL_EMPLOYEE.LAST_NAME)
                .from(TBL_EMPLOYEE)
                .orderBy(TBL_EMPLOYEE.LAST_NAME.sortAsc("JOOQ", "Batch_0").nullsFirst())
                .fetch();
    }

    @Test
    public void testOrderBy06() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        Map<String, Integer> map = new HashMap<>();
        map.put("JOOQ", 0);
        map.put("Batch_0", 1);
        create.select(TBL_EMPLOYEE.ID, TBL_EMPLOYEE.LAST_NAME)
                .from(TBL_EMPLOYEE)
                .orderBy(TBL_EMPLOYEE.LAST_NAME.sort(map))
                .fetch();
    }

    @Test
    public void testLimitOffset() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        create.select(TBL_EMPLOYEE.ID, TBL_EMPLOYEE.LAST_NAME)
                .from(TBL_EMPLOYEE)
                .limit(1)
                .offset(2)
                .fetch();
    }

    @Test
    public void testSeek() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        create.select(TBL_EMPLOYEE.ID, TBL_EMPLOYEE.LAST_NAME)
                .from(TBL_EMPLOYEE)
                .orderBy(TBL_EMPLOYEE.ID.asc())
                .seek(10)
                .limit(5)
                .fetch();
    }

    @Test
    public void testForUpdate() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        create.select(TBL_EMPLOYEE.ID, TBL_EMPLOYEE.LAST_NAME)
                .from(TBL_EMPLOYEE)
                .where(TBL_EMPLOYEE.ID.eq(1))
                .forUpdate()
                .fetch();
    }

    @Test
    public void testUnion() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        create.select(TBL_EMPLOYEE.ID, TBL_EMPLOYEE.LAST_NAME)
                .from(TBL_EMPLOYEE)
                .where(TBL_EMPLOYEE.ID.eq(1))
                .union(
                        create.select(TBL_EMPLOYEE.ID, TBL_EMPLOYEE.LAST_NAME)
                                .from(TBL_EMPLOYEE)
                                .where(TBL_EMPLOYEE.ID.eq(3))
                )
                .fetch();
    }

    @Test
    public void testUnionAll() throws Exception {
        DSLContext create = using(getConnection(), SQLDialect.MYSQL);
        create.select(TBL_EMPLOYEE.ID, TBL_EMPLOYEE.LAST_NAME)
                .from(TBL_EMPLOYEE)
                .orderBy(TBL_EMPLOYEE.ID.asc())
                .limit(1)
                .unionAll(
                        create.select(TBL_EMPLOYEE.ID, TBL_EMPLOYEE.LAST_NAME)
                                .from(TBL_EMPLOYEE)
                                .orderBy(TBL_EMPLOYEE.ID.desc())
                                .limit(1)
                )
                .fetch();
    }

    @Test
    public void testInsert01() throws Exception {
        using(getConnection(), SQLDialect.MYSQL)
                .insertInto(TBL_EMPLOYEE,
                        TBL_EMPLOYEE.LAST_NAME, TBL_EMPLOYEE.EMAIL, TBL_EMPLOYEE.GENDER)
                .values("JOOQ-01", "JOOQ-01@qq.com", "1")
                .execute();
    }

    @Test
    public void testInsert02() throws Exception {
        using(getConnection(), SQLDialect.MYSQL)
                .insertInto(TBL_EMPLOYEE,
                        TBL_EMPLOYEE.LAST_NAME, TBL_EMPLOYEE.EMAIL, TBL_EMPLOYEE.GENDER)
                .values("JOOQ-02", "JOOQ-02@qq.com", "1")
                .values("JOOQ-03", "JOOQ-03@qq.com", "2")
                .execute();
    }

    @Test
    public void testInsert03() throws Exception {
        using(getConnection(), SQLDialect.MYSQL)
                .insertInto(TBL_EMPLOYEE)
                .defaultValues()
                .execute();
    }

    @Test
    public void testInsert04() throws Exception {
        using(getConnection(), SQLDialect.MYSQL)
                .insertInto(TBL_EMPLOYEE,
                        TBL_EMPLOYEE.LAST_NAME, TBL_EMPLOYEE.EMAIL, TBL_EMPLOYEE.GENDER)
                .values(
                        defaultValue(TBL_EMPLOYEE.LAST_NAME),
                        defaultValue(TBL_EMPLOYEE.EMAIL),
                        defaultValue(TBL_EMPLOYEE.GENDER)
                )
                .execute();
    }

    @Test
    public void testInsert05() throws Exception {
        using(getConnection(), SQLDialect.MYSQL)
                .insertInto(TBL_EMPLOYEE)
                .set(TBL_EMPLOYEE.LAST_NAME, "JOOQ-05")
                .set(TBL_EMPLOYEE.EMAIL, "JOOQ-05@qq.com")
                .set(TBL_EMPLOYEE.GENDER, "2")
                .execute();
    }

    @Test
    public void testInsertSelect() throws Exception {
        using(getConnection(), SQLDialect.MYSQL)
                .insertInto(TBL_EMPLOYEE,
                        TBL_EMPLOYEE.LAST_NAME, TBL_EMPLOYEE.GENDER, TBL_EMPLOYEE.EMAIL)
                .select(
                        select(
                                field("lastName", String.class).as("last_name"),
                                field("gender", String.class).as("gender"),
                                field("email", String.class).as("email")
                        ).from(table("employee"))
                )
                .execute();
    }

    @Test
    public void testInsertOnDuplicate() throws Exception {
        using(getConnection(), SQLDialect.MYSQL)
                .insertInto(TBL_EMPLOYEE,
                        TBL_EMPLOYEE.LAST_NAME, TBL_EMPLOYEE.GENDER, TBL_EMPLOYEE.EMAIL)
                .values("jooq-duplicate", "1", "jooq-duplicate@qq.com")
                .onDuplicateKeyUpdate()
//                .onDuplicateKeyIgnore()
                .set(TBL_EMPLOYEE.EMAIL, "jooq-duplicate@qq.com")
                .execute();
    }

    @Test
    public void testInsertReturning01() throws Exception {
        TblEmployeeRecord record = using(getConnection())
                .insertInto(TBL_EMPLOYEE,
                        TBL_EMPLOYEE.LAST_NAME, TBL_EMPLOYEE.GENDER, TBL_EMPLOYEE.EMAIL)
                .values("jooq", "2", "jooq@qq.com")
                .returning(TBL_EMPLOYEE.ID)
                .fetchOne();
        System.out.println(record.getValue(TBL_EMPLOYEE.ID));
    }

    @Test
    public void testInsertReturning02() throws Exception {
        Result<TblEmployeeRecord> result = using(getConnection())
                .insertInto(TBL_EMPLOYEE,
                        TBL_EMPLOYEE.LAST_NAME, TBL_EMPLOYEE.GENDER, TBL_EMPLOYEE.EMAIL)
                .values("jooq01", "1", "jooq01@qq.com")
                .values("jooq02", "2", "jooq02@qq.com")
                .returning(TBL_EMPLOYEE.ID)
                .fetch();
        // Mysql批量插入只返回最先插入的那条记录的自增id，这里之所以能返回多个自增id，是由mysql-jdbc中程序处理的
        result.forEach(r -> System.out.println(r.getValue(TBL_EMPLOYEE.ID)));
    }

    @Test
    public void testUpdate01() throws Exception {
        using(getConnection())
                .update(TBL_EMPLOYEE)
                .set(TBL_EMPLOYEE.LAST_NAME, "jooq-update")
                .where(TBL_EMPLOYEE.ID.eq(1))
                .execute();
    }

    @Test
    public void testUpdate02() throws Exception {
        using(getConnection())
                .update(TBL_EMPLOYEE)
                .set(TBL_EMPLOYEE.LAST_NAME, select(val("jooq-update-select")))
                .where(TBL_EMPLOYEE.ID.eq(1))
                .execute();
    }

    @Test
    public void testUpdate03() throws Exception {
        using(getConnection())
                .update(TBL_EMPLOYEE)
                .set(
                        row(TBL_EMPLOYEE.LAST_NAME, TBL_EMPLOYEE.EMAIL),
                        row("jooq-update-row", "jooq-update-row@example.com")
                        )
                .where(TBL_EMPLOYEE.ID.eq(1))
                .execute();
    }

    @Test
    public void testDelete01() throws Exception {
        using(getConnection())
                .delete(TBL_EMPLOYEE)
                .where(TBL_EMPLOYEE.ID.eq(40061))
                .execute();
    }

    @Test
    public void testDdl() throws Exception {
        using(getConnection())
                .ddl(TBL_EMPLOYEE)
                .stream()
                .forEach(System.out::println);
    }

    @Test
    public void testTable() throws Exception {
        using(getConnection())
                .select().from(TBL_EMPLOYEE)
                .join(TBL_DEPT).on(TBL_DEPT.ID.eq(TBL_EMPLOYEE.D_ID))
                .fetchAny();
    }

    @Test
    public void testTable02() throws Exception {
        TblEmployee e = TBL_EMPLOYEE.as("e");
        TblDept d = TBL_DEPT.as("d");
        using(getConnection())
                .select(e.field("id"), e.field("last_name")).from(e)
                .join(d).on(d.ID.eq(e.D_ID))
                .where(e.ID.eq(1))
                .fetchAny();
    }

    @Test
    public void testNestedSelect01() throws Exception {
        using(getConnection())
                .select().from(TBL_EMPLOYEE)
                .where(TBL_EMPLOYEE.D_ID.in(select(TBL_DEPT.ID).from(TBL_DEPT)))
                .fetchAny();
    }

    @Test
    public void testNestedSelect02() throws Exception {
        DSLContext dsl = using(getConnection());
        Table<?> t1 = dsl.select(count().as("c"), TBL_EMPLOYEE.D_ID)
                .from(TBL_EMPLOYEE)
                .groupBy(TBL_EMPLOYEE.D_ID)
                .asTable("t1");

        Result<Record> c = dsl.select().from(t1).orderBy(t1.field("c").desc()).fetch();
        System.out.println(c);
    }

    @Test
    public void testAlias() throws Exception {
        DSLContext dsl = using(getConnection());
        Record1<String> record = dsl
                .select(concat(TBL_EMPLOYEE.LAST_NAME, inline("|"), TBL_EMPLOYEE.EMAIL).as("col1"))
                .from(TBL_EMPLOYEE)
                .where(TBL_EMPLOYEE.ID.eq(1))
                .fetchAny();

        System.out.println(record.getValue("col1"));
    }

    @Test
    public void testMath() throws Exception {
        DSLContext dsl = using(getConnection());
        Result<Record1<Integer>> result = dsl.select(val(1).add(2).mul(val(5).sub(3)).div(2).mod(10)).fetch();
        Result<Record1<Object>> result2 = dsl.select(field("(1+2)*(5-3)/2%10")).fetch();
        System.out.println(result);
        System.out.println(result2);
    }

    @Test
    public void testDateTime() throws Exception {
        DSLContext dsl = using(getConnection());
        Result<Record1<Timestamp>> result = dsl.select(currentTimestamp().add(3)).fetch();
        System.out.println(result);
    }

    @Test
    public void testString() throws Exception {
        DSLContext dsl = using(getConnection());
        Result<Record1<String>> result = dsl.select(concat("A", "B", "C")).fetch();
        System.out.println(result);
    }

    @Test
    public void testCase() throws Exception {
        DSLContext dsl = using(getConnection());
        Result<Record1<String>> result = dsl.select(
                when(field("gender").eq("1"), "男")
                        .when(field("gender").eq("2"), "女")
                        .otherwise("未知").as("my_gender")
        ).from(TBL_EMPLOYEE).limit(10).fetch();
        System.out.println(result);

        Result<Record1<String>> result1 = dsl.select(
                choose(field("gender"))
                        .when("1", "男")
                        .when("2", "女")
                        .otherwise("未知"))
                .from(TBL_EMPLOYEE).limit(10).fetch();
        System.out.println(result1);
    }

    @Test
    public void testQueryByExample() throws Exception {
        TblEmployeeRecord record = new TblEmployeeRecord();
        record.setId(1);

        DSLContext dsl = using(getConnection());
        Result<Record> result = dsl.select()
                .from(TBL_EMPLOYEE)
                .where(condition(record))
                .fetch();
    }

    @Test
    public void testDynamicSql() throws Exception {
        Condition condition = trueCondition();

        condition = condition.and(TBL_EMPLOYEE.ID.eq(1));
        condition = condition.and(TBL_EMPLOYEE.LAST_NAME.like("%jooq%"));

        DSLContext dsl = using(getConnection());
        Result<Record> result = dsl.select()
                .from(TBL_EMPLOYEE)
                .where(condition)
                .fetch();
    }

    @Test
    public void testIndexedParameters() throws Exception {
        // 索引参数绑定
        DSLContext dsl = using(getConnection());

        // select * from tbl_employee where id=? and last_name like ?  [1 => 1, 2 => %jooq%]
        dsl.select()
                .from(TBL_EMPLOYEE)
                .where(TBL_EMPLOYEE.ID.eq(1))
                .and(TBL_EMPLOYEE.LAST_NAME.like("%jooq%"))
                .fetch();

        dsl.select()
                .from(TBL_EMPLOYEE)
                .where(TBL_EMPLOYEE.ID.eq(val(1)))
                .and(TBL_EMPLOYEE.LAST_NAME.like(val("%jooq%")))
                .fetch();
    }

    @Test
    public void testNamedParameters() throws Exception {
        // 命名参数绑定
        DSLContext dsl = using(getConnection());
        SelectConditionStep<Record> select = dsl.select().from(TBL_EMPLOYEE)
                .where(TBL_EMPLOYEE.LAST_NAME.like(param("lastName", "%jooq%")));
        Param<?> lastName = select.getParam("lastName");
        System.out.println(lastName);

        SelectConditionStep<Record> select1 = dsl.select().from(TBL_EMPLOYEE).where(TBL_EMPLOYEE.LAST_NAME.like("%jooq%"));
        select1.bind(1, "jooq-1");

        // select * from tbl_employee where last_name like :lastName  [lastName => jooq-1]
        SelectConditionStep<Record> select2 = dsl.select().from(TBL_EMPLOYEE).where(TBL_EMPLOYEE.LAST_NAME.like(param("lastName", "%jooq%")));
        select2.bind("lastName", "jooq-1");
    }

    @Test
    public void testInlinedParameters() throws Exception {
        // 不绑定参数，
        DSLContext dsl = using(getConnection());
        // select * from tbl_employee where last_name like '%jooq%'
        SelectConditionStep<Record> select = dsl.select().from(TBL_EMPLOYEE).where(TBL_EMPLOYEE.LAST_NAME.like(inline("%jooq%")));
        System.out.println(select.getSQL());
    }

    @Test
    public void testCustomField() throws Exception {
        DSLContext dsl = using(getConnection());

        // 1.
        dsl.select(field("id * 2").as("c")).from(TBL_EMPLOYEE).fetchAny();

        // 2.
        CustomField<Integer> customField = new CustomField<Integer>(TBL_EMPLOYEE.ID.getName(), TBL_EMPLOYEE.ID.getDataType()) {
            @Override
            public void accept(Context ctx) {
//                ctx.sql("id * 2");
                ctx.visit(TBL_EMPLOYEE.ID).sql(" * ").visit(inline(2));
            }
        };
        dsl.select(customField).from(TBL_EMPLOYEE).fetchAny();
    }

    @Test
    public void testPlainSqlQueryParts() throws Exception {
        DSLContext dsl = using(getConnection());
        Record record = dsl.select().from(TBL_EMPLOYEE)
                .where("id=?", 1)
                .fetchAny();
    }

    @Test
    public void testQuery() throws Exception {
        DSLContext dsl = using(getConnection());
        Query query = dsl.query("update tbl_employee set gender=? where id=?", "1", 1);
        int rowCount = query.execute();
        System.out.println(rowCount);
    }

    @Test
    public void testResultQuery() throws Exception {
        DSLContext dsl = using(getConnection());
        dsl.resultQuery("select * from tbl_employee where id=?", 1).fetchAny();
    }

    @Test
    public void testCrud() throws Exception {
        DSLContext dsl = using(getConnection());
        TblEmployeeRecord record = dsl.newRecord(TBL_EMPLOYEE);
        record.setLastName("jooq-crud");
        record.store();

        record.refresh();

        record.setGender("2");
        record.store();
    }
}
