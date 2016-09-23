package com.e2open.datahub;

import com.e2open.datahub.entity.datasource.Customer;
import com.e2open.datahub.repository.datasource.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@SpringBootApplication
@ServletComponentScan
public class DXApplication extends SpringBootServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(DXApplication.class);


    public static void main(String[] args) throws Exception {
        SpringApplication.run(DXApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(DXApplication.class);
    }

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "e2datahub.metadata.datasource")
    public DataSource metadataDataSource() {

        return DataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties(prefix = "e2datahub.staging.datasource")
    public DataSource stagingDataSource() {

        return DataSourceBuilder.create().build();
    }

    @Bean
    public CommandLineRunner demo(CustomerRepository repository) {
        System.out.println(repository);

        return (args) -> {
            // save a couple of customers
            repository.save(new Customer("Mario", "Tema"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            repository.findAll().forEach(customer -> {
                log.info(customer.toString());
            });

            log.info("Customer found with findByLastName('Tema'):");
            log.info("--------------------------------------------");
            repository.findByLastName("Tema").forEach(customer -> {
                log.info(customer.toString());
            });
        };
    }

}
