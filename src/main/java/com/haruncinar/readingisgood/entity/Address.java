package com.haruncinar.readingisgood.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@Document(collection = "address")
public class Address extends MainEntity
{
    @Field(name = "addressText")
    private String addressText;

    @NotNull
    @Field(name = "doorNumber")
    private Integer doorNumber;

    @Field(name = "buildingName")
    private String buildingName;

    @NotNull
    @Field(name = "buildingNumber")
    private Integer buildingNumber;

    @NotNull
    @Field(name = "streetName")
    private String streetName;

    @NotNull
    @Field(name = "neighborhoodName")
    private String neighborhoodName;

    @NotNull
    @Field(name = "district")
    private String district;

    @NotNull
    @Field(name = "city")
    private String city;
}
