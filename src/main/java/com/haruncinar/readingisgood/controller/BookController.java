package com.haruncinar.readingisgood.controller;

import com.haruncinar.readingisgood.entity.Book;
import com.haruncinar.readingisgood.model.UpdateBookStockRequest;
import com.haruncinar.readingisgood.model.UpdateBookStockResponse;
import com.haruncinar.readingisgood.service.BookService;
import com.haruncinar.readingisgood.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController
{
    @Autowired
    private BookService bookService;

    @PostMapping("/create")
    public Object createBook(@RequestBody Book book)
    {
        try
        {
            return bookService.createBook(book);
        }
        catch (Exception exception)
        {
            return exception.getMessage();
        }
    }

    @PutMapping("/updateBookStock")
    public UpdateBookStockResponse updateBookStock(@RequestBody UpdateBookStockRequest updateBookStockRequest) //FIXME @Valid
    {
        return bookService.updateBookStock(updateBookStockRequest);
    }
}
