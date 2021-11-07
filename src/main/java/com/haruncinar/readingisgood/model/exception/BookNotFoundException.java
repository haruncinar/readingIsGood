package com.haruncinar.readingisgood.model.exception;

import com.haruncinar.readingisgood.util.ReadingIsGoodConstants;
import lombok.Getter;

@Getter
public class BookNotFoundException extends BaseReadingIsGoodException
{
    private final String code = ReadingIsGoodConstants.BOOK_NOT_FOUND_CODE;

    public BookNotFoundException()
    {
        super(ReadingIsGoodConstants.BOOK_NOT_FOUND_MESSAGE);
    }
}
