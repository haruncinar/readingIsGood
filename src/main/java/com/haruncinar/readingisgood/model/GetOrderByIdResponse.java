package com.haruncinar.readingisgood.model;

import com.haruncinar.readingisgood.model.dto.OrderDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class GetOrderByIdResponse implements Serializable
{

    OrderDTO orderDTO;

    String message;
}
