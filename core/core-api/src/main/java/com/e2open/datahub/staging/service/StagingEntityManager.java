package com.e2open.datahub.staging.service;

import com.e2open.datahub.staging.entity.StagingPerson;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional("stagingTransactionManager")
public class StagingEntityManager {

    @PersistenceContext(unitName = "staging")
    private EntityManager entityManager;

    private StagingPerson stagingPerson = StagingPerson.builder().firstName("StagingEntityManager").lastName("Tema").build();

    @PostConstruct
    public void init() {

        entityManager.persist(stagingPerson);

        StagingPerson entity = entityManager.find(StagingPerson.class, stagingPerson.getId());

        System.out.println(entity);
    }
}
