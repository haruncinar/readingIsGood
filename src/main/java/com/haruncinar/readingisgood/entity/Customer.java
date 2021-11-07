package com.haruncinar.readingisgood.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Document(collection = "customer")
public class Customer extends MainEntity
{
    @DBRef
    private List<Order> orders;

    @NotNull(message = "name can not be null")
    private String name;

    @NotNull(message = "phoneNumber can not be null")
    @Indexed(unique = true)
    private BigInteger phoneNumber;

    @NotNull(message = "email can not be null")
    @Indexed(unique = true)
    private String email;

    @NotNull(message = "address can not be null")
    @DBRef
    private Address address;

    public void settingOrder(Customer customer, Order order)
    {
        if(customer.getOrders() == null)
        {
            customer.setOrders(new ArrayList<>());
        }
        customer.getOrders().add(order);
    }
}
