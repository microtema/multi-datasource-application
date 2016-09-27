package com.e2open.datahub.staging.service;

import com.e2open.datahub.staging.dao.StagingPersonRepository;
import com.e2open.datahub.staging.entity.StagingPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Service
public class StagingCrudRepositoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StagingCrudRepositoryService.class);

    @Inject
    private StagingPersonRepository repository;

    private StagingPerson stagingPerson = StagingPerson.builder().firstName("Staging").lastName("Tema").build();

    @PostConstruct
    private void init() {

        // save stagingPerson
        StagingPerson saved = repository.save(stagingPerson);

        // fetch all stagingPersons
        LOGGER.info("Staging# Person found with findAll():");
        repository.findAll().forEach(person -> {
            LOGGER.info(person.toString());
        });

        LOGGER.info("Staging# Person found with findByLastName('Tema'):");
        repository.findByLastName("Tema").forEach(person -> {
            LOGGER.info(person.toString());
        });
    }

}