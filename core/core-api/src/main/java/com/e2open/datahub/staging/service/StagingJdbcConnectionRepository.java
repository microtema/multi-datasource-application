package com.e2open.datahub.staging.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@Component
public class StagingJdbcConnectionRepository {

    @Inject
    @Qualifier("stagingDataSource")
    private DataSource dataSource;


    @PostConstruct
    public void init() {

        try {
            Connection connection = dataSource.getConnection();
            boolean execute = connection.createStatement().execute("select * FROM stagingperson");
            log.info("staging connection successfully established");
        } catch (SQLException e) {
            log.warn("staging connection failed");
        }
    }
}
