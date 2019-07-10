package org.godseop.apple.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Slf4j
@Configuration
@MapperScan(basePackages="org.godseop.apple.mapper.mysql", sqlSessionTemplateRef="sqlSessionTemplateMysql")
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryMysql",
        transactionManagerRef = "transactionManagerMysql",
        basePackages="org.godseop.apple.repository.mysql"
)
@EnableTransactionManagement
public class MysqlConfig {


    @Primary
    @Bean(name = "mysqlDataSourceProperties")
    @ConfigurationProperties("spring.datasource.mysql")
    public DataSourceProperties mysqlDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Primary
    @Bean(name = "mysqlDataSource")
    public DataSource mysqlDataSource(@Qualifier("mysqlDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return DataSourceBuilder
                .create(dataSourceProperties.getClassLoader())
                .type(HikariDataSource.class)
                .driverClassName(dataSourceProperties.getDriverClassName())
                .url(dataSourceProperties.getUrl())
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword())
                .build();
    }

    @Primary
    @Bean(name = "entityManagerFactoryMysql")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryMysql(
            EntityManagerFactoryBuilder builder, @Qualifier("mysqlDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("org.godseop.apple.entity.mysql")
                .persistenceUnit("mysql")
                .build();
    }

    @Primary
    @Bean(name = "transactionManagerMysql")
    public PlatformTransactionManager transactionManagerMysql(
            @Qualifier("entityManagerFactoryMysql") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }


    @Primary
    @Bean(name = "sqlSessionFactoryMysql")
    public SqlSessionFactory sqlSessionFactoryMysql(@Qualifier("mysqlDataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage("org.godseop.apple.entity.mysql");
        sqlSessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/mysql/*.xml"));
        return sqlSessionFactory.getObject();
    }

    @Primary
    @Bean(name = "sqlSessionTemplateMysql")
    public SqlSessionTemplate sqlSessionTemplateMysql(@Qualifier("sqlSessionFactoryMysql") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}