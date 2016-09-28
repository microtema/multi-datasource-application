package com.e2open.datahub.metadata.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@Component
@Slf4j
public class MetadataJdbcConnectionRepository {

    @Autowired
    private DataSource metadataDataSource;

    @PostConstruct
    public void init() {

        try {
            Connection connection = metadataDataSource.getConnection();
            boolean execute = connection.createStatement().execute("select * FROM metadataperson");
            log.info("metadata connection successfully established");
        } catch (SQLException e) {
            log.warn("metadata connection failed");
        }
    }
}
