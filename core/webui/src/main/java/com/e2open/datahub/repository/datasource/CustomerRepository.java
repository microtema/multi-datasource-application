package com.e2open.datahub.repository.datasource;

import com.e2open.datahub.entity.datasource.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);
}
