package com.haruncinar.readingisgood.repository;

import com.haruncinar.readingisgood.entity.OrderLine;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends MongoRepository<OrderLine, String>
{
}
