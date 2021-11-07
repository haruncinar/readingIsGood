package com.haruncinar.readingisgood.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigInteger;

@Data
public class CustomerDTO implements Serializable
{
    String name;

    String phoneNumber;

    String email;
}
