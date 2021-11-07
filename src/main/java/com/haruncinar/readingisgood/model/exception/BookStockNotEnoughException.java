package com.haruncinar.readingisgood.model.exception;

import com.haruncinar.readingisgood.util.ReadingIsGoodConstants;
import lombok.Getter;

@Getter
public class BookStockNotEnoughException extends BaseReadingIsGoodException
{
    private final String code =  ReadingIsGoodConstants.BOOK_STOCK_NOT_ENOUGH_CODE;;

    public BookStockNotEnoughException()
    {
        super(ReadingIsGoodConstants.BOOK_STOCK_NOT_ENOUGH_MESSAGE);
    }

}
