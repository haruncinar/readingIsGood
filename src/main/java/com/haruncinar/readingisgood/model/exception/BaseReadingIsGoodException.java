package com.haruncinar.readingisgood.model.exception;

public abstract class BaseReadingIsGoodException extends RuntimeException
{
    public abstract String getCode();

    public BaseReadingIsGoodException(String message)
    {
        super(message);
    }
}
