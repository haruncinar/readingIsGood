package com.haruncinar.readingisgood;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.haruncinar.readingisgood.entity.Book;
import com.haruncinar.readingisgood.model.CreateBookResponse;
import com.haruncinar.readingisgood.model.UpdateBookStockRequest;
import com.haruncinar.readingisgood.model.UpdateBookStockResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookControllerTest
{
    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @Test
    void testCreateBook() throws Exception
    {
        String testResult = mockMvc.perform(post("/api/book/create")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(
                        Book.builder()
                                .name("Sakkara'nın Kumları")
                                .isbn("124245412345")
                                .price(BigDecimal.ONE)
                                .authorName("Gleann Meade").build()
                ))).andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        CreateBookResponse createBookResponse = objectMapper.readValue(testResult, CreateBookResponse.class);
        assertNotNull(createBookResponse.getBookDTO().getBookName());
    }

    @Test
    void testUpdateBookStock() throws Exception
    {
        String testResult = mockMvc.perform(put("/api/book/updateBookStock")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(
                        UpdateBookStockRequest.builder()
                                .bookId("6187adaf0de4e4615d88fb04")
                                .amount(10).build()
                ))).andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        UpdateBookStockResponse updateBookStockResponse = objectMapper.readValue(testResult, UpdateBookStockResponse.class);
        assertEquals(updateBookStockResponse.getAmount(), 10);
    }

}
