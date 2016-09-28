package com.e2open.datahub.metadata.service;

import com.e2open.datahub.metadata.entity.MetadataPerson;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class MetadataEntityManager {

    @PersistenceContext(unitName = "metadata")
    private EntityManager entityManager;

    private MetadataPerson metadataPerson = MetadataPerson.builder().firstName("MetadataEntityManager").lastName("Tema").build();

    /**
     * Using JpaContext in custom implementations
     *
     * @Inject public MetadataEntityManager(JpaContext context) {
     * this.entityManager = context.getEntityManagerByManagedType(MetadataPerson.class);
     * }
     */

    @PostConstruct
    @Transactional("metadataTransactionManager")
    public void init() {

        //entityManager.persist(metadataPerson);

        MetadataPerson entity = entityManager.find(MetadataPerson.class, 1l);

        System.out.println(entity);
    }
}
