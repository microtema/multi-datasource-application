package com.e2open.datahub.metadata.service;

import com.e2open.datahub.metadata.dao.MetadataPersonRepository;
import com.e2open.datahub.metadata.entity.MetadataPerson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Service
public class MetadataCrudRepositoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetadataCrudRepositoryService.class);

    @Inject
    private MetadataPersonRepository repository;

    private MetadataPerson metadataPerson = MetadataPerson.builder().firstName("Metadata").lastName("Tema").build();

    @PostConstruct
    private void init() {

        // save metadataPerson
        MetadataPerson saved = repository.save(metadataPerson);

        // fetch all metadataPersons
        LOGGER.info("Metadata# Person found with findAll():");
        repository.findAll().forEach(person -> {
            LOGGER.info(person.toString());
        });

        LOGGER.info("Metadata# Person found with findByLastName('Tema'):");
        repository.findByLastName("Tema").forEach(person -> {
            LOGGER.info(person.toString());
        });
    }

}