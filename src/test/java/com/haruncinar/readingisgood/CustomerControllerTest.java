package com.haruncinar.readingisgood;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haruncinar.readingisgood.entity.Address;
import com.haruncinar.readingisgood.entity.Customer;
import com.haruncinar.readingisgood.model.CreateCustomerResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerControllerTest
{
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @Test
    void testCreateCustomer() throws Exception
    {
        String testResult = mockMvc.perform(post("/api/customer/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(
                        Customer.builder()
                                .name("Harun Çınar")
                                .phoneNumber("05553331122")
                                .email("test2@gmail.com")
                                .address(Address.builder()
                                        .buildingNumber(1)
                                        .doorNumber(1)
                                        .streetName("test")
                                        .neighborhoodName("test")
                                        .district("Maltepe")
                                        .city("İstanbul").build()).build()
                ))).andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        CreateCustomerResponse createCustomerResponse = objectMapper.readValue(testResult, CreateCustomerResponse.class);
        assertNotNull(createCustomerResponse.getCustomerDTO().getName());
    }
}
