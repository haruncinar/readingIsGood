package com.haruncinar.readingisgood.model;

import com.haruncinar.readingisgood.model.dto.CustomerDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class CreateCustomerResponse implements Serializable
{
    CustomerDTO customerDTO;

    String message;
}
