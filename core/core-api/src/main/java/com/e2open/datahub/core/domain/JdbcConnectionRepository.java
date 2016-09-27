package com.e2open.datahub.core.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@Component
public class JdbcConnectionRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcConnectionRepository.class);

    @Autowired
    private DataSource metadataDataSource;

    @Autowired
    private DataSource stagingDataSource;


    @PostConstruct
    public void init() {

        try {
            Connection connection = metadataDataSource.getConnection();
            connection.createStatement().execute("select * metadata_person");
            LOGGER.info("metadata connection successfully established");
        } catch (SQLException e) {
            LOGGER.warn("metadata connection failed");
        }

        try {
            Connection connection = stagingDataSource.getConnection();
            connection.createStatement().execute("select * from staging_person");
            LOGGER.info("staging connection successfully established");
        } catch (SQLException e) {
            LOGGER.warn("staging connection failed");
        }
    }
}
