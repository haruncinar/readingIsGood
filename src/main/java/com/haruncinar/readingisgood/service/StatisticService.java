package com.haruncinar.readingisgood.service;

import com.haruncinar.readingisgood.model.dto.StatisticDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService
{
    @Autowired
    private OrderService orderService;

    public List<StatisticDTO> findStatisticsDetails()
    {
        List<Object[]> objectList = orderService.findStatistics();
        return convertObjectsToStatisticDto(objectList);
    }

    private List<StatisticDTO> convertObjectsToStatisticDto(List<Object[]> objectList)
    {
        List<StatisticDTO> statisticDTOList = new ArrayList<>();
        for(Object[] object : objectList)
        {
            StatisticDTO statisticDTO = StatisticDTO.builder()
                                        .month(object[0].toString())
                                        .totalPurchasedAmount((BigDecimal) object[1])
                                        .totalOrderCount((Integer) object[2])
                                        .totalBookCount((Integer) object[3]).build();
            statisticDTOList.add(statisticDTO);
        }
        return statisticDTOList;
    }
}
