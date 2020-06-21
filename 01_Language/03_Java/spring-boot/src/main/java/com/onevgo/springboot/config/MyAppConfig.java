package com.onevgo.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.onevgo.springboot.bean.Person;
import com.onevgo.springboot.component.StringToDateConverter;
import com.onevgo.springboot.entity.TbUser;
import com.onevgo.springboot.entity.User;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * <code>@Configuration</code> 指明当前类是一个配置类，就是来替代之前的Spring配置文件
 * 在配置文件中用 <bean></bean> 标签添加组件
 */
@Configuration
// @Import(MyAppConfig.class) // 导入其他配置类
// @ImportResource(locations = {"classpath:/beans.xml"}) // 导入XML配置
@PropertySource(value = {"classpath:/person.properties"}) // 加载属性文件
// 启用对@ConfigurationProperties的支持，
// 当以这种方式注册@ConfigurationProperties bean时，该bean将具有一个常规名称:<prefix>-<fqn>，
// 其中<prefix>是在@ConfigurationProperties注释中指定的环境键前缀，
// <fqn>是该bean的完全限定名。
// 如果注释不提供任何前缀，则只使用bean的完全限定名。
@EnableConfigurationProperties(MyAppProperties.class)
@MapperScan(value = "com.onevgo.springboot.mapper")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.onevgo.springboot.repository.def",
        entityManagerFactoryRef = "entityManagerFactory",
        transactionManagerRef = "transactionManager")
public class MyAppConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.jpa")
    public JpaProperties jpaProperties() {
        return new JpaProperties();
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return new ConfigurationCustomizer() {
            @Override
            public void customize(org.apache.ibatis.session.Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }

    //    @Bean
    public StringToDateConverter stringToDateConverter() {
        return new StringToDateConverter();
    }

    @Configuration
    protected static class DbDefaultConfig {
        @Bean
        @Primary
        @ConfigurationProperties(prefix = "spring.datasource.druid")
        public DataSource dataSource(DataSourceProperties dataSourceProperties) {
            return dataSourceProperties
                    .initializeDataSourceBuilder()
                    .type(DruidDataSource.class)
                    .build();
        }

        @Bean
        @Primary
        public LocalContainerEntityManagerFactoryBean entityManagerFactory(
                EntityManagerFactoryBuilder builder,
                @Qualifier("dataSource") DataSource dataSource,
                @Qualifier("jpaProperties") JpaProperties jpaProperties) {
            return builder.dataSource(dataSource)
                    .packages(User.class)
                    .properties(jpaProperties.getHibernateProperties(dataSource))
                    .persistenceUnit("default")
                    .build();
        }

        @Bean
        @Primary
        public PlatformTransactionManager transactionManager(
                @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
            return new JpaTransactionManager(entityManagerFactory);
        }

        @Bean
        @Primary
        public JdbcTemplate jdbcTemplate(
                @Qualifier("dataSource") DataSource dataSource) {
            return new JdbcTemplate(dataSource);
        }
    }

    @Configuration
    @EnableJpaRepositories(basePackages = "com.onevgo.springboot.repository.yii2",
            entityManagerFactoryRef = "yii2EntityManagerFactory",
            transactionManagerRef = "yii2TransactionManager")
    protected static class DbYii2Config {
        @Bean
        @ConfigurationProperties(prefix = "app.datasource.yii2")
        public DataSource yii2DataSource(DataSourceProperties dataSourceProperties) {
            return dataSourceProperties.initializeDataSourceBuilder()
                    .type(org.apache.tomcat.jdbc.pool.DataSource.class)
                    .build();
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean yii2EntityManagerFactory(
                EntityManagerFactoryBuilder builder,
                @Qualifier("yii2DataSource") DataSource dataSource,
                @Qualifier("jpaProperties") JpaProperties jpaProperties) {
            return builder.dataSource(dataSource)
                    .packages(TbUser.class)
                    .properties(jpaProperties.getHibernateProperties(dataSource))
                    .persistenceUnit("yii2")
                    .build();
        }

        @Bean
        public PlatformTransactionManager yii2TransactionManager(
                @Qualifier("yii2EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
            return new JpaTransactionManager(entityManagerFactory);
        }

        @Bean
        public JdbcTemplate yii2JdbcTemplate(
                @Qualifier("yii2DataSource") DataSource dataSource) {
            return new JdbcTemplate(dataSource);
        }
    }

    @Bean
    public Person person() {
        return new Person();
    }
}
