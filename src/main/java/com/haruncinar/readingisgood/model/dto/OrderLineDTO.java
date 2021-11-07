package com.haruncinar.readingisgood.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrderLineDTO implements Serializable
{
    private String bookName;

    private Integer amount;
}
