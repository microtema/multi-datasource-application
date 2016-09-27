package com.e2open.datahub.core.domain;

import com.e2open.datahub.core.domain.staging.StagingPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;

@Service
public class JdbcTemplateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcTemplateService.class);

    @Autowired
    JdbcTemplate stagingJdbcTemplate;

    @Autowired
    JdbcTemplate metadataJdbcTemplate;

    @PostConstruct
    public void init() {

        stagingJdbcTemplate.query("SELECT * FROM staging_person", new CustomerRowMapper()).forEach(out::println);

        metadataJdbcTemplate.queryForList("SELECT * FROM metadata_person").forEach(out::println);
    }

    private static class CustomerRowMapper implements RowMapper<StagingPerson> {
        @Override
        public StagingPerson mapRow(ResultSet rs, int rowNum) throws SQLException {
            StagingPerson customer = new StagingPerson();
            customer.setId(rs.getLong("id"));
            customer.setFirstName(rs.getString("first_Name"));
            customer.setLastName(rs.getString("first_Name"));

            return customer;
        }
    }
}
