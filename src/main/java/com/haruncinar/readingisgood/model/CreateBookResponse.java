package com.haruncinar.readingisgood.model;

import com.haruncinar.readingisgood.model.dto.BookDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateBookResponse implements Serializable
{
    BookDTO bookDTO;

    String message;
}
