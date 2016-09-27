package com.e2open.datahub.core.domain;

import com.e2open.datahub.core.domain.metadata.MetadataPerson;
import com.e2open.datahub.core.domain.metadata.MetadataPersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Service
public class CrudRepositoryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrudRepositoryService.class);

    @Inject
    private MetadataPersonRepository metadataPersonRepository;

    @PostConstruct
    private void init() {

        // save a couple of customers
        metadataPersonRepository.save(MetadataPerson.builder().firstName("Mario").lastName("Tema").build());

        // fetch all customers
        LOGGER.info("Person found with findAll():");
        metadataPersonRepository.findAll().forEach(person -> {
            LOGGER.info(person.toString());
        });

        LOGGER.info("Person found with findByLastName('Tema'):");
        metadataPersonRepository.findByLastName("Tema").forEach(person -> {
            LOGGER.info(person.toString());
        });
    }

}
