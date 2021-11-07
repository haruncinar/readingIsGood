package com.haruncinar.readingisgood.controller;

import com.haruncinar.readingisgood.entity.Order;
import com.haruncinar.readingisgood.model.CreateNewOrderResponse;
import com.haruncinar.readingisgood.model.GetOrderByDateIntervalResponse;
import com.haruncinar.readingisgood.model.GetOrderByIdResponse;
import com.haruncinar.readingisgood.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/order")
public class OrderController
{
    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public CreateNewOrderResponse createNewOrder(@RequestBody Order order)
    {
        return orderService.createNewOrder(order);
    }

    @GetMapping("/getOrderById/{id}")
    public GetOrderByIdResponse getOrderById(@PathVariable String id)
    {
        return orderService.findOrderById(id);
    }

    @GetMapping("/getOrdersByDateInterval")
    public GetOrderByDateIntervalResponse getOrdersByDateInterval(@RequestParam String startDate, @RequestParam String endDate)
    {
        return orderService.findOrdersByDateInterval(startDate, endDate);
    }
}
