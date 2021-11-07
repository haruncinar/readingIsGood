package com.haruncinar.readingisgood.model.exception;

import com.haruncinar.readingisgood.util.ReadingIsGoodConstants;
import lombok.Getter;

@Getter
public class OrderDateFormatException extends BaseReadingIsGoodException
{
    private final String code = ReadingIsGoodConstants.MISSING_DATE_FORMAT_FOR_ORDER_CODE;

    public OrderDateFormatException()
    {
        super(ReadingIsGoodConstants.MISSING_DATE_FORMAT_FOR_ORDER_MESSAGE);
    }
}
