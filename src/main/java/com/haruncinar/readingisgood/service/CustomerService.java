package com.haruncinar.readingisgood.service;

import com.haruncinar.readingisgood.entity.Customer;
import com.haruncinar.readingisgood.model.CreateCustomerResponse;
import com.haruncinar.readingisgood.model.dto.CustomerDTO;
import com.haruncinar.readingisgood.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService
{
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressService addressService;

    @Transactional
    public CreateCustomerResponse saveCustomer(Customer customer)
    {
        try
        {
            addressService.saveAddress(customer.getAddress());
            customer = customerRepository.save(customer);
            return convertCustomerToCreateCustomerResponse(customer);
        }
        catch (Exception exception)
        {
            CreateCustomerResponse response = new CreateCustomerResponse();
            response.setMessage(exception.getMessage());
            return response;
        }
    }

    public Customer findCustomerById(String id)
    {
        return customerRepository.findById(id).orElse(null);
    }

    private CreateCustomerResponse convertCustomerToCreateCustomerResponse(Customer customer)
    {
        CreateCustomerResponse response = new CreateCustomerResponse();
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setName(customer.getName());
        customerDTO.setPhoneNumber(customer.getPhoneNumber());
        response.setCustomerDTO(customerDTO);
        return response;
    }

}
