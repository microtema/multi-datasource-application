package com.e2open.datahub.staging.service;

import com.e2open.datahub.staging.entity.StagingPerson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Service
public class StagingJdbcTemplateService {


    @Inject
    private JdbcTemplate stagingJdbcTemplate;

    @PostConstruct
    public void init() {

        stagingJdbcTemplate.query("SELECT * FROM StagingPerson", new CustomerRowMapper()).forEach(stagingPerson -> log.info(stagingPerson.toString()));

        stagingJdbcTemplate.queryForList("SELECT id FROM StagingPerson", Long.class).forEach(stagingPerson -> log.info(stagingPerson.toString()));
    }

    public static class CustomerRowMapper implements RowMapper<StagingPerson> {
        @Override
        public StagingPerson mapRow(ResultSet rs, int rowNum) throws SQLException {

            StagingPerson customer = new StagingPerson();

            customer.setId(rs.getLong("id"));
            customer.setFirstName(rs.getString("firstName"));
            customer.setLastName(rs.getString("firstName"));

            return customer;
        }
    }
}
