package com.e2open.datahub.metadata.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@Component
public class MetadataJdbcConnectionRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetadataJdbcConnectionRepository.class);

    @Autowired
    private DataSource metadataDataSource;

    @PostConstruct
    public void init() {

        try {
            Connection connection = metadataDataSource.getConnection();
            boolean execute = connection.createStatement().execute("select * FROM metadataperson");
            LOGGER.info("metadata connection successfully established");
        } catch (SQLException e) {
            LOGGER.warn("metadata connection failed");
        }
    }
}
