package com.e2open.datahub.staging.service;

import com.e2open.datahub.staging.dao.StagingPersonRepository;
import com.e2open.datahub.staging.entity.StagingPerson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Slf4j
@Service
public class StagingCrudRepositoryService {


    @Inject
    private StagingPersonRepository repository;

    private StagingPerson stagingPerson = StagingPerson.builder().firstName("Staging").lastName("Tema").build();

    @PostConstruct
    private void init() {

        // save stagingPerson
        StagingPerson saved = repository.save(stagingPerson);

        // fetch all stagingPersons
        log.info("Staging# Person found with findAll():");
        repository.findAll().forEach(person -> {
            log.info(person.toString());
        });

        log.info("Staging# Person found with findByLastName('Tema'):");
        repository.findByLastName("Tema").forEach(person -> {
            log.info(person.toString());
        });
    }
}