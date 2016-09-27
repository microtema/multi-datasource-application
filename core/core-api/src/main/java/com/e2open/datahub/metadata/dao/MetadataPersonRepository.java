package com.e2open.datahub.metadata.dao;

import com.e2open.datahub.metadata.entity.MetadataPerson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional("metadataTransactionManager")
public interface MetadataPersonRepository extends CrudRepository<MetadataPerson, Long> {

    List<MetadataPerson> findByLastName(String lastName);
}
