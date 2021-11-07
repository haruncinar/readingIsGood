package com.haruncinar.readingisgood.model.exception;

public abstract class BaseReadingIsGoodException extends RuntimeException
{
    public abstract String getCode();

    protected BaseReadingIsGoodException(String message)
    {
        super(message);
    }
}
