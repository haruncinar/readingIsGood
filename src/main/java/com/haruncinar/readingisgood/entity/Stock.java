package com.haruncinar.readingisgood.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "stock")
public class Stock extends MainEntity
{
    @Field(name = "amount")
    private Integer amount;

    @DBRef
    private Book book;

    public void handleNewAmount(Integer amount)
    {
        this.amount -= amount;
    }
}
