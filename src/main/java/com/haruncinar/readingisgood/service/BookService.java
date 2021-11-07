package com.haruncinar.readingisgood.service;

import com.haruncinar.readingisgood.entity.Book;
import com.haruncinar.readingisgood.entity.Stock;
import com.haruncinar.readingisgood.model.UpdateBookStockRequest;
import com.haruncinar.readingisgood.model.UpdateBookStockResponse;
import com.haruncinar.readingisgood.model.exception.BookNotFoundException;
import com.haruncinar.readingisgood.model.exception.BookStockNotFoundException;
import com.haruncinar.readingisgood.repository.BookRepository;
import com.haruncinar.readingisgood.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService
{

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private StockRepository stockRepository;

    @Transactional
    public Book createBook(Book book)
    {
        return bookRepository.save(book);
    }

    public UpdateBookStockResponse updateBookStock(UpdateBookStockRequest updateBookStockRequest)
    {
        UpdateBookStockResponse updateBookStockResponse;
        Stock bookStock = stockRepository.findByBookId(updateBookStockRequest.getBookId());
        Book book = bookRepository.findById(updateBookStockRequest.getBookId()).orElse(null);
        if (book == null)
        {
            throw new BookNotFoundException();
        }
        if(bookStock == null)
        {
            bookStock = new Stock();
            bookStock.setBook(book);
        }
        bookStock.setAmount(updateBookStockRequest.getAmount());
        Stock savedBookStock = stockRepository.save(bookStock);
        updateBookStockResponse = UpdateBookStockResponse.builder()
                .isbn(savedBookStock.getBook().getIsbn())
                .bookName(savedBookStock.getBook().getName())
                .amount(savedBookStock.getAmount())
                .price(savedBookStock.getBook().getPrice()).build();
        return updateBookStockResponse;
    }

    public Book findBookById(String id)
    {
        return bookRepository.findById(id).orElse(null);
    }
}
