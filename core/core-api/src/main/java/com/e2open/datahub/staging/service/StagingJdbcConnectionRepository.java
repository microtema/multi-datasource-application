package com.e2open.datahub.staging.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@Component
public class StagingJdbcConnectionRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(StagingJdbcConnectionRepository.class);

    @Inject
    @Qualifier("stagingDataSource")
    private DataSource dataSource;


    @PostConstruct
    public void init() {

        try {
            Connection connection = dataSource.getConnection();
            boolean execute = connection.createStatement().execute("select * FROM stagingperson");
            LOGGER.info("staging connection successfully established");
        } catch (SQLException e) {
            LOGGER.warn("staging connection failed");
        }
    }
}
