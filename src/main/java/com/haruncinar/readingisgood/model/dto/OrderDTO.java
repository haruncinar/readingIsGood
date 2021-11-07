package com.haruncinar.readingisgood.model.dto;

import com.haruncinar.readingisgood.model.enums.OrderStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO implements Serializable
{
    String orderId;

    String customerId;

    Date createTime;

    OrderStatus orderStatus;

    List<OrderLineDTO> orderLines;
}
