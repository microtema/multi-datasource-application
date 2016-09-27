package com.e2open.datahub.core.domain.metadata;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MetadataPersonRepository extends CrudRepository<MetadataPerson, Long> {

    List<MetadataPerson> findByLastName(String lastName);
}
