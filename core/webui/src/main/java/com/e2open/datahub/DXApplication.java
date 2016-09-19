package com.e2open.datahub;

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

}
