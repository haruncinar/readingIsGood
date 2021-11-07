package com.haruncinar.readingisgood.repository;

import com.haruncinar.readingisgood.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String>
{
}
