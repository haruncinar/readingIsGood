package com.haruncinar.readingisgood.model.enums;

public enum OrderStatus
{
    NEW("NEW", "Order has been created"),
    PREPARING("PREPARING", "Order is preparing"),
    SHIPPING("SHIPPING", "Order is shipping"),
    DONE("DONE", "Order has delivered");

    private final String name;
    private final String description;

    OrderStatus(String name, String description)
    {
        this.name = name;
        this.description = description;
    }
}
