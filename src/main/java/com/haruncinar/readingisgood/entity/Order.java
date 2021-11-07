package com.haruncinar.readingisgood.entity;

import com.haruncinar.readingisgood.model.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "order")
public class Order extends MainEntity
{
    @DBRef
    private List<OrderLine> orderLines;

    @DBRef
    private Customer customer;

    @Field(name = "orderStatus")
    private OrderStatus orderStatus = OrderStatus.NEW;
}
