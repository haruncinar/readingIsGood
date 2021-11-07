package com.haruncinar.readingisgood.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Document(collection = "book")
public class Book extends MainEntity
{
    @Field(name = "authorName")
    @NotNull
    private String authorName;

    @Field(name = "isbn")
    @Indexed(unique = true)
    @NotNull
    private String isbn;

    @Field(name = "name")
    @NotNull
    private String name;

    @NotNull
    @Field(name = "price")
    private BigDecimal price;
}
