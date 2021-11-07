package com.haruncinar.readingisgood.controller;

import com.haruncinar.readingisgood.entity.Customer;
import com.haruncinar.readingisgood.entity.Order;
import com.haruncinar.readingisgood.model.CreateCustomerResponse;
import com.haruncinar.readingisgood.model.GetAllCustomerOrdersByIdWithPaginationResponse;
import com.haruncinar.readingisgood.service.CustomerService;
import com.haruncinar.readingisgood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController
{
    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public CreateCustomerResponse createCustomer(@RequestBody Customer customer)
    {
        return customerService.saveCustomer(customer);
    }

    @GetMapping("/getAllCustomerOrdersByIdWithPagination/{id}")
    public GetAllCustomerOrdersByIdWithPaginationResponse getAllCustomerOrdersByIdWithPagination(@PathVariable String id, @RequestParam int pageNo, @RequestParam int pageSize)
    {
        return orderService.findAllCustomerOrdersByIdWithPagination(id, pageNo, pageSize);
    }


}
