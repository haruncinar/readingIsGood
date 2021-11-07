package com.haruncinar.readingisgood.repository;

import com.haruncinar.readingisgood.entity.Book;
import com.haruncinar.readingisgood.entity.Stock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends MongoRepository<Stock, String>
{
    Stock findByBookId(String bookId);

    Stock findStockByBookId(String bookId);
}
