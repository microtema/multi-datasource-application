package com.e2open.datahub.staging;

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

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "stagingEntityManagerFactory",
        transactionManagerRef = "stagingTransactionManager"
)
public class StagingConfig {


    @Bean
    @ConfigurationProperties(prefix = "e2datahub.staging.datasource")
    public DataSource stagingDataSource() {

        return DataSourceBuilder.create().build();
    }


    @Bean
    public JdbcTemplate stagingJdbcTemplate() {
        return new JdbcTemplate(stagingDataSource());
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean stagingEntityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.e2open.datahub.staging");
        factory.setDataSource(stagingDataSource());
        factory.setPersistenceUnitName("staging");
        factory.afterPropertiesSet();

        return factory;
    }

    @Bean
    public PlatformTransactionManager stagingTransactionManager() {

        return new JpaTransactionManager(stagingEntityManagerFactory().getObject());
    }

    @Bean
    EntityManager stagingEntityManager() {
        return stagingEntityManagerFactory().getObject().createEntityManager();
    }
}