package com.haruncinar.readingisgood.entity;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Document(collection = "orderLine")
public class OrderLine extends MainEntity
{
    @DBRef
    private Book book;

    @Field(name = "amount")
    private Integer amount;
}
