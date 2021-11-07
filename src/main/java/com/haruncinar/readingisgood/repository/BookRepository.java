package com.haruncinar.readingisgood.repository;

import com.haruncinar.readingisgood.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<Book, String>
{
}
