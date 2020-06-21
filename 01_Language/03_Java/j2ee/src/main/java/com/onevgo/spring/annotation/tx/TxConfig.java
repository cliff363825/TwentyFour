package com.onevgo.spring.annotation.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 声明式事务：
 * <p>
 * 环境搭建：
 * 1. 导入相关依赖
 * 数据源、数据库驱动、Spring-jdbc模块
 * 2. 配置数据源、JdbcTemplate（Spring提供的简化数据库操作的工具）操作数据
 * 3. 给方法上标注 @Transactional 表示当前方法是一个事务方法；
 * 4. @EnableTransactionManagement 开启基于注解的事务管理功能；
 * <code>@EnableXXX</code>
 * 5. 配置事务管理器来控制事务;
 * <code>@Bean
 * public PlatformTransactionManager transactionManager()
 * </code>
 * 原理：
 * 1. @EnableTransactionManagement
 * 利用TransactionManagementConfigurationSelector给容器中会导入组件
 * 导入两个组件
 * AutoProxyRegistrar
 * ProxyTransactionManagementConfiguration
 * 2. AutoProxyRegistrar：
 * 给容器中注册一个 InfrastructureAdvisorAutoProxyCreator 组件；
 * InfrastructureAdvisorAutoProxyCreator：？
 * 利用后置处理器机制在对象创建以后，包装对象，返回一个代理对象（增强器），代理对象执行方法利用拦截器链进行调用；
 * 3. ProxyTransactionManagementConfiguration 做了什么？
 * <ul>
 * <li>1. 给容器中注册事务增强器；</li>
 * <ul>
 * <li>1. 事务增强器要用事务注解的信息，AnnotationTransactionAttributeSource解析事务注解</li>
 * <li>2. 事务拦截器：</li>
 * <ul>
 * <li>TransactionInterceptor；保存了事务属性信息，事务管理器；</li>
 * <li>他是一个 MethodInterceptor；</li>
 * <li>在目标方法执行的时候；</li>
 * <li>执行拦截器链；</li>
 * <li>事务拦截器：</li>
 * <ul>
 * <li>1. 先获取事务相关的属性</li>
 * <li>2. 再获取PlatformTransactionManager，如果事先没有添加指定任何transactionManger</li>
 * <li>最终会从容器中按照类型获取一个PlatformTransactionManager；</li>
 * <li>3. 执行目标方法</li>
 * <li>如果异常，获取到事务管理器，利用事务管理回滚操作；</li>
 * <li>如果正常，利用事务管理器，提交事务</li>
 * </ul>
 * </ul>
 * </ul>
 * </ul>
 */
@EnableTransactionManagement
@ComponentScan("com.onevgo.spring.annotation")
@Configuration
public class TxConfig {
    @Bean
    public DataSource dataSource() throws Exception {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/default?useSSL=false");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws Exception {
        // Spring 对 @Configuration 类会特殊处理；给容器中加组件的方法，多次调用都只是从容器中找组件
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws Exception {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }
}
