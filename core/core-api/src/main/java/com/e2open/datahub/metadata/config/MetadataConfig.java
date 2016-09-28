package com.e2open.datahub.metadata.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "metadataEntityManagerFactory",
        transactionManagerRef = "metadataTransactionManager",
        basePackages = "com.e2open.datahub.metadata.dao"
)
@EntityScan(basePackages = "com.e2open.datahub.metadata.entity")
public class MetadataConfig {

    @Primary
    @Bean(name = "metadataDataSource")
    @ConfigurationProperties(prefix = "e2datahub.metadata.datasource")
    public DataSource metadataDataSource() {

        return DataSourceBuilder.create().build();
    }

    @Primary
    @Autowired
    @Bean(name = "metadataJdbcTemplate")
    public JdbcTemplate metadataJdbcTemplate(@Qualifier("metadataDataSource") DataSource metadataDataSource) {
        return new JdbcTemplate(metadataDataSource);
    }

    @Primary
    @Autowired
    @Bean(name = "metadataEntityManagerFactory")
    public EntityManagerFactory metadataEntityManagerFactory(@Qualifier("metadataDataSource") DataSource metadataDataSource) {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.e2open.datahub.metadata.entity");
        factory.setDataSource(metadataDataSource);
        factory.setPersistenceUnitName("metadata");
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Primary
    @Autowired
    @Bean(name = "metadataTransactionManager")
    public PlatformTransactionManager metadataTransactionManager(@Qualifier("metadataEntityManagerFactory") EntityManagerFactory metadataEntityManagerFactory) {

        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(metadataEntityManagerFactory);
        return txManager;
    }
}
