package com.haruncinar.readingisgood.controller;

import com.haruncinar.readingisgood.model.dto.StatisticDTO;
import com.haruncinar.readingisgood.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController
{
    @Autowired
    private StatisticService statisticService;

    @GetMapping("/getStatistics")
    public List<StatisticDTO> getStatistics()
    {
        return statisticService.findStatisticsDetails();
    }
}
