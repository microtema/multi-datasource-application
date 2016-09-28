package com.e2open.datahub.metadata;


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
        entityManagerFactoryRef = "metadataEntityManagerFactory",
        transactionManagerRef = "metadataTransactionManager"
)
public class MetadataConfig {


    @Bean
    @ConfigurationProperties(prefix = "e2datahub.metadata.datasource")
    DataSource metadataDataSource() {

        return DataSourceBuilder.create().build();
    }


    @Bean
    JdbcTemplate metadataJdbcTemplate() {
        return new JdbcTemplate(metadataDataSource());
    }


    @Bean
    LocalContainerEntityManagerFactoryBean metadataEntityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.e2open.datahub.metadata");
        factory.setDataSource(metadataDataSource());
        factory.setPersistenceUnitName("metadata");
        factory.afterPropertiesSet();

        return factory;
    }


    @Bean
    PlatformTransactionManager metadataTransactionManager() {

        return new JpaTransactionManager(metadataEntityManagerFactory().getObject());
    }

    @Bean
    EntityManager metadataEntityManager() {
        return metadataEntityManagerFactory().getObject().createEntityManager();
    }
}
