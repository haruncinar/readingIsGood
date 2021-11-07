package com.haruncinar.readingisgood.model;

import com.haruncinar.readingisgood.model.dto.OrderDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetOrderByDateIntervalResponse implements Serializable
{
    List<OrderDTO> orders;

    String message;
}
