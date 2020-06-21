package com.onevgo.mybatis;

import com.onevgo.mybatis.mbg.bean.Employee;
import com.onevgo.mybatis.mbg.bean.EmployeeExample;
import com.onevgo.mybatis.mbg.dao.EmployeeMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MbgTest {
    @Test
    public void testMbg() throws Exception {
        ArrayList<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        InputStream inputStream = null;
        try {
            ConfigurationParser configurationParser = new ConfigurationParser(warnings);
            inputStream = getClass().getResourceAsStream("/mbg.xml");
            Configuration configuration = configurationParser.parseConfiguration(inputStream);
            DefaultShellCallback shellCallback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration, shellCallback, warnings);
            myBatisGenerator.generate(null);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        String resource = "mybatis-config-test.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();
    }

    @Test
    public void testMybatis3() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
            // 1. 查询所有
            List<Employee> emps = mapper.selectByExample(null);
            System.out.println(emps);

            // 2. 查询员工名字中有e字母的，和员工性别是1的
            // 封装员工查询条件的example
            EmployeeExample example = new EmployeeExample();
            // 创建一个 criteria，这个Criteria 就是拼装查询条件
            EmployeeExample.Criteria criteria = example.createCriteria();
            criteria.andLastNameLike("%e%");
            criteria.andGenderEqualTo("1");

            EmployeeExample.Criteria criteria1 = example.createCriteria();
            criteria1.andEmailLike("%e%");
            example.or(criteria1);

            emps = mapper.selectByExample(example);
            System.out.println(emps);
        } finally {
            sqlSession.close();
        }
    }
}
