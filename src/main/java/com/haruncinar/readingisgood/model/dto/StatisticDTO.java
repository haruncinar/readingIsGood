package com.haruncinar.readingisgood.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class StatisticDTO implements Serializable
{
    private String month;

    private Integer totalOrderCount;

    private Integer totalBookCount;

    private BigDecimal totalPurchasedAmount;
}
