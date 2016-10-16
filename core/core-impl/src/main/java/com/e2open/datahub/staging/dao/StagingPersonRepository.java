package com.e2open.datahub.staging.dao;

import com.e2open.datahub.staging.entity.StagingPerson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional("stagingTransactionManager")
public interface StagingPersonRepository extends CrudRepository<StagingPerson, Long> {

    List<StagingPerson> findByLastName(String lastName);
}
