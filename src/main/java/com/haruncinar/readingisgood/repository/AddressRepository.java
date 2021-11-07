package com.haruncinar.readingisgood.repository;

import com.haruncinar.readingisgood.entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String>
{
    public Address findByDoorNumberAndBuildingNumberAndStreetNameAndDistrictAndCity(int doorNumber, int buildingNumber, String streetName, String district, String city);
}
