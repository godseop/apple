package org.godseop.apple.config;


import com.zaxxer.hikari.HikariDataSource;
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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages="org.godseop.apple.mapper.postgresql", sqlSessionTemplateRef="sqlSessionTemplatePostgresql")
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryPostgresql",
        transactionManagerRef = "transactionManagerPostgresql",
        basePackages="org.godseop.apple.repository.postgresql"
)
@EnableTransactionManagement
public class PostgresqlConfig {

    @Bean(name = "postgresqlDataSourceProperties")
    @ConfigurationProperties("spring.datasource.postgresql")
    public DataSourceProperties postgresqlDataSourceProperties() {
        return new DataSourceProperties();
    }


    @Bean(name = "postgresqlDataSource")
    public DataSource postgresqlDataSource(@Qualifier("postgresqlDataSourceProperties") DataSourceProperties dataSourceProperties) {
        return DataSourceBuilder
                .create(dataSourceProperties.getClassLoader())
                .type(HikariDataSource.class)
                .driverClassName(dataSourceProperties.getDriverClassName())
                .url(dataSourceProperties.getUrl())
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword())
                .build();
    }


    @Bean(name = "entityManagerFactoryPostgresql")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPostgresql(
            EntityManagerFactoryBuilder builder, @Qualifier("postgresqlDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("org.godseop.apple.entity.postgresql")
                .persistenceUnit("postgresql")
                .build();
    }


    @Bean(name = "transactionManagerPostgresql")
    public PlatformTransactionManager transactionManagerPostgresql(
            @Qualifier("entityManagerFactoryPostgresql") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }




    @Bean(name = "sqlSessionFactoryPostgresql")
    public SqlSessionFactory sqlSessionFactoryPostgresql(@Qualifier("postgresqlDataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage("org.godseop.apple.entity.postgresql");
        sqlSessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis-config.xml"));
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/postgresql/*.xml"));
        return sqlSessionFactory.getObject();
    }


    @Bean(name = "sqlSessionTemplatePostgresql")
    public SqlSessionTemplate sqlSessionTemplatePostgresql(@Qualifier("sqlSessionFactoryPostgresql") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
