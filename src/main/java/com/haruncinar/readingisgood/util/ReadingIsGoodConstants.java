package com.haruncinar.readingisgood.util;


public class ReadingIsGoodConstants
{
    public static final String BOOK_STOCK_NOT_FOUND_CODE = "0002";
    public static final String BOOK_STOCK_NOT_FOUND_MESSAGE = "The book stock not found for book id: %s";

    public static final String BOOK_STOCK_NOT_ENOUGH_CODE = "0003";
    public static final String BOOK_STOCK_NOT_ENOUGH_MESSAGE = "The book stock not enough for book id: %s";

    public static final String MISSING_INPUT_FOR_ORDER_CODE = "0004";
    public static final String MISSING_INPUT_FOR_ORDER_MESSAGE = "Missing Input for Order: %s";

    public static final String MISSING_DATE_FORMAT_FOR_ORDER_CODE = "0005";
    public static final String MISSING_DATE_FORMAT_FOR_ORDER_MESSAGE = "Missing Date Format for Order: %s";

    public static final String BOOK_NOT_FOUND_CODE = "0006";
    public static final String BOOK_NOT_FOUND_MESSAGE = "The book not found for book id: %s";

    public static final String CUSTOMER = "CUSTOMER";
    public static final String ADDRESS = "ADDRESS";
    public static final String ORDER_LINE = "ORDER_LINE";
    public static final String AMOUNT = "AMOUNT";
    public static final String BOOK = "BOOK";

    public static final String DATE_FORMAT = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
}
