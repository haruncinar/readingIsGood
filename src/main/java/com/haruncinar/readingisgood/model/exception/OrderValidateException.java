package com.haruncinar.readingisgood.model.exception;

import com.haruncinar.readingisgood.util.ReadingIsGoodConstants;
import lombok.Getter;

@Getter
public class OrderValidateException extends BaseReadingIsGoodException
{
    private final String code = ReadingIsGoodConstants.MISSING_INPUT_FOR_ORDER_CODE;

    public OrderValidateException(String input)
    {
        super(String.format(ReadingIsGoodConstants.MISSING_INPUT_FOR_ORDER_MESSAGE, input));
    }
}
