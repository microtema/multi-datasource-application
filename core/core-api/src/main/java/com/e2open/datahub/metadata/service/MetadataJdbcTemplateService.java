package com.e2open.datahub.metadata.service;

import com.e2open.datahub.metadata.entity.MetadataPerson;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;

import static java.lang.System.out;

@Service
@Slf4j
public class MetadataJdbcTemplateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetadataJdbcTemplateService.class);

    @Inject
    @Qualifier("metadataJdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {

        jdbcTemplate.query("SELECT * FROM metadataperson", new CustomerRowMapper()).forEach(out::println);
    }

    private static class CustomerRowMapper implements RowMapper<MetadataPerson> {
        @Override
        public MetadataPerson mapRow(ResultSet rs, int rowNum) throws SQLException {

            MetadataPerson customer = new MetadataPerson();

            customer.setId(rs.getLong("id"));
            customer.setFirstName(rs.getString("firstName"));
            customer.setLastName(rs.getString("firstName"));

            return customer;
        }
    }
}
