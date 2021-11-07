package com.haruncinar.readingisgood.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class UpdateBookStockResponse implements Serializable
{
    String isbn;

    String bookName;

    Integer amount;

    BigDecimal price;
}
