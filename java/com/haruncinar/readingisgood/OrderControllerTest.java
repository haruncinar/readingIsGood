package com.haruncinar.readingisgood;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.haruncinar.readingisgood.entity.Address;
import com.haruncinar.readingisgood.entity.Book;
import com.haruncinar.readingisgood.entity.Customer;
import com.haruncinar.readingisgood.entity.Order;
import com.haruncinar.readingisgood.entity.OrderLine;
import com.haruncinar.readingisgood.model.CreateNewOrderResponse;
import com.haruncinar.readingisgood.model.GetOrderByDateIntervalResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderControllerTest
{
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @Test
    void testCreateNewOrder() throws Exception
    {
        String testResult = mockMvc.perform(post("/api/order/create")
                .contentType("application/json")
                .content(objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY).writeValueAsString(Order.builder()
                        .orderLines(Arrays.asList(OrderLine.builder()
                                .book(Book.builder()
                                        .name("Sakkara'nın Kumları")
                                        .isbn("12424215612235")
                                        .price(BigDecimal.ONE)
                                        .authorName("Gleann Meade").build())
                                .amount(1)
                                .build()))
                        .customer(Customer.builder()
                                .name("Harun Çınar")
                                .phoneNumber("055533311365")
                                .email("test77@gmail.com")
                                .address(Address.builder()
                                        .buildingNumber(1)
                                        .doorNumber(1)
                                        .streetName("test2")
                                        .neighborhoodName("test2")
                                        .district("Maltep2e")
                                        .city("İstanb4ul").build()).build()))))
                        .andDo(print()).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        CreateNewOrderResponse createNewOrderResponse = objectMapper.readValue(testResult, CreateNewOrderResponse.class);
        assertTrue(createNewOrderResponse.getOrderDTO().getOrderLines().size() > 0);
    }

}
