package com.haruncinar.readingisgood.service;

import com.haruncinar.readingisgood.entity.Book;
import com.haruncinar.readingisgood.entity.Stock;
import com.haruncinar.readingisgood.model.CreateBookResponse;
import com.haruncinar.readingisgood.model.UpdateBookStockRequest;
import com.haruncinar.readingisgood.model.UpdateBookStockResponse;
import com.haruncinar.readingisgood.model.dto.BookDTO;
import com.haruncinar.readingisgood.model.exception.BookNotFoundException;
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
    public CreateBookResponse createBook(Book book)
    {
        try
        {
            book = bookRepository.save(book);
            return convertBookToCreateBookResponse(book);
        }
        catch (Exception exception)
        {
            CreateBookResponse response = new CreateBookResponse();
            response.setMessage(exception.getMessage());
            return response;
        }
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

    private CreateBookResponse convertBookToCreateBookResponse(Book book)
    {
        CreateBookResponse response = new CreateBookResponse();
        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookName(book.getName());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setAuthorName(book.getAuthorName());
        bookDTO.setPrice(book.getPrice());
        response.setBookDTO(bookDTO);
        return response;
    }
}
