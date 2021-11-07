package com.haruncinar.readingisgood.model.exception;

import com.haruncinar.readingisgood.util.ReadingIsGoodConstants;
import lombok.Getter;

@Getter
public class BookStockNotFoundException extends BaseReadingIsGoodException
{
    private final String code = ReadingIsGoodConstants.BOOK_STOCK_NOT_FOUND_CODE;

    public BookStockNotFoundException(String id)
    {
        super(String.format(ReadingIsGoodConstants.BOOK_STOCK_NOT_FOUND_MESSAGE, id));
    }
}
