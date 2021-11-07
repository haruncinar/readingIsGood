package com.haruncinar.readingisgood.service;

import com.haruncinar.readingisgood.entity.Stock;
import com.haruncinar.readingisgood.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockService
{
    @Autowired
    private StockRepository stockRepository;

    public Stock findStockByBook(String bookId)
    {
        return stockRepository.findByBookId(bookId);
    }

    @Transactional
    public List<Stock> saveStockList(List<Stock> stockList)
    {
        return stockRepository.saveAll(stockList);
    }
}
