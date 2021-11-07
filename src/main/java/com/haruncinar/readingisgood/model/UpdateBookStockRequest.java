package com.haruncinar.readingisgood.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UpdateBookStockRequest implements Serializable
{
    @NotNull
    private String bookId;

    @NotNull
    private int amount;
}
