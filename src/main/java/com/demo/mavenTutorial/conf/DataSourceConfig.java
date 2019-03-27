package com.demo.mavenTutorial.conf;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    @Bean(name = "dataSource")
    @Primary
//    @Qualifier("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource newDataSource(Environment env) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(env.getProperty("spring.datasource.url"));
        ds.setUsername(env.getProperty("spring.datasource.username"));
        ds.setPassword(env.getProperty("spring.datasource.password"));
        ds.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
        return ds;
    }

    @Bean(name = "dev-dataSource")
//    @Qualifier("dev-dataSource")
    @ConfigurationProperties(prefix = "spring.dev-datasource")
    public DataSource devataSource(Environment env) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(env.getProperty("spring.dev-datasource.url"));
        ds.setUsername(env.getProperty("spring.dev-datasource.username"));
        ds.setPassword(env.getProperty("spring.dev-datasource.password"));
        ds.setDriverClassName(env.getProperty("spring.dev-datasource.driverClassName"));
        return ds;
    }

    @Bean(name = "dataSourceTemplate")
    public JdbcTemplate primaryTemplate(@Qualifier("dataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "dev-dataSourceTemplate")
    public JdbcTemplate devTemplate(@Qualifier("dev-dataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
