package com.haruncinar.readingisgood.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class BookDTO implements Serializable
{
    String bookName;

    String isbn;

    String authorName;

    BigDecimal price;
}
