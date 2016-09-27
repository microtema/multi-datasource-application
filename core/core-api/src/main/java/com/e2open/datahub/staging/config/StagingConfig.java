package com.e2open.datahub.staging.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
@EnableJpaRepositories(
        entityManagerFactoryRef = "stagingEntityManagerFactory",
        transactionManagerRef = "stagingTransactionManager",
        basePackages = "com.e2open.datahub.staging.dao"
)
@EntityScan(basePackages = "com.e2open.datahub.staging.entity")
public class StagingConfig {


    @Bean(name = "stagingDataSource")
    @ConfigurationProperties(prefix = "e2datahub.staging.datasource")
    public DataSource stagingDataSource() {

        return DataSourceBuilder.create().build();
    }

    @Autowired
    @Bean(name = "stagingJdbcTemplate")
    public JdbcTemplate stagingJdbcTemplate(@Qualifier("stagingDataSource") DataSource stagingDataSource) {
        return new JdbcTemplate(stagingDataSource);
    }

    @Autowired
    @Bean(name = "stagingEntityManagerFactory")
    public EntityManagerFactory stagingEntityManagerFactory(@Qualifier("stagingDataSource") DataSource stagingDataSource) {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.e2open.datahub.staging.entity");
        factory.setDataSource(stagingDataSource);
        factory.setPersistenceUnitName("staging");
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Autowired
    @Bean(name = "stagingTransactionManager")
    public PlatformTransactionManager stagingTransactionManager(@Qualifier("stagingEntityManagerFactory") EntityManagerFactory stagingEntityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(stagingEntityManagerFactory);
        return txManager;
    }
}