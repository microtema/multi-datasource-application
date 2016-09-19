package com.e2open.datahub.core;

import com.e2open.datahub.core.persistence.DatasourceConfig;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


@Component
public class CoreImpl {

    private static final Logger LOGGER = LogManager.getLogger(CoreImpl.class);

    @Value("${com.e2open.datahub.core.api}")
    private String property;

    @Autowired
    private DatasourceConfig metadataDatasourceConfig;

    @Autowired
    private DatasourceConfig stagingDatasourceConfig;
    @Autowired
    private DataSource metadataDataSource;
    @Autowired
    private DataSource stagingDataSource;

    @PostConstruct
    public void init() {
        LOGGER.info("init: " + property);

        try {
            Connection connection = metadataDataSource.getConnection();
            connection.createStatement().execute("VALUES 1");
            LOGGER.info("metadata connection successfully established");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = stagingDataSource.getConnection();
            connection.createStatement().execute("VALUES 1");
            LOGGER.info("staging connection successfully established");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
