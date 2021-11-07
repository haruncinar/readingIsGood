package com.haruncinar.readingisgood.service;

import com.haruncinar.readingisgood.entity.Address;
import com.haruncinar.readingisgood.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService
{
    @Autowired
    private AddressRepository addressRepository;

    public Address findByDoorNumberAndBuildingNumberAndStreetNameAndDistrictAndCity(int doorNumber, int buildingNumber, String streetName, String district, String city)
    {
        return addressRepository.findByDoorNumberAndBuildingNumberAndStreetNameAndDistrictAndCity(doorNumber, buildingNumber, streetName, district, city);
    }

    public Address saveAddress(Address address)
    {
        return addressRepository.save(address);
    }
}
