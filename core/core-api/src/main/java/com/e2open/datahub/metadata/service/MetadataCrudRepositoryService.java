package com.e2open.datahub.metadata.service;

import com.e2open.datahub.metadata.dao.MetadataPersonRepository;
import com.e2open.datahub.metadata.entity.MetadataPerson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Slf4j
@Service
public class MetadataCrudRepositoryService {


    @Inject
    private MetadataPersonRepository repository;

    private MetadataPerson metadataPerson = MetadataPerson.builder().firstName("Metadata").lastName("Tema").build();

    @PostConstruct
    private void init() {

        // save metadataPerson
        MetadataPerson saved = repository.save(metadataPerson);

        // fetch all metadataPersons
        log.info("Metadata# Person found with findAll():");
        repository.findAll().forEach(person -> {
            log.info(person.toString());
        });

        log.info("Metadata# Person found with findByLastName('Tema'):");
        repository.findByLastName("Tema").forEach(person -> {
            log.info(person.toString());
        });
    }

}