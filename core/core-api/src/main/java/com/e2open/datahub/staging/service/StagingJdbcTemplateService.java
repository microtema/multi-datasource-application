package com.e2open.datahub.staging.service;

import com.e2open.datahub.staging.entity.StagingPerson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
@Service
public class StagingJdbcTemplateService {


    @Autowired
    @Qualifier("stagingJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {

        jdbcTemplate.query("SELECT * FROM StagingPerson", new CustomerRowMapper()).forEach(stagingPerson -> log.info(stagingPerson.toString()));

        jdbcTemplate.queryForList("SELECT id FROM StagingPerson", Long.class).forEach(stagingPerson -> log.info(stagingPerson.toString()));
    }

    private static class CustomerRowMapper implements RowMapper<StagingPerson> {
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
