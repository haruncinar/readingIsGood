package com.haruncinar.readingisgood.service;

import com.haruncinar.readingisgood.entity.OrderLine;
import com.haruncinar.readingisgood.repository.OrderLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderLineService
{

    @Autowired
    OrderLineRepository orderLineRepository;

    @Transactional
    public List<OrderLine> saveOrderLines(List<OrderLine> orderLines)
    {
        return orderLineRepository.saveAll(orderLines);
    }
}
