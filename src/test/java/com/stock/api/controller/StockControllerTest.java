package com.stock.api.controller;

import com.stock.api.model.Stock;
import com.stock.api.service.StockService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(StockController.class)
class StockControllerTest {

    @MockBean
    StockService stockService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getStocks() {
        Stock stock = new Stock();
        List<Stock> stocks = Arrays.asList(stock);

        Mockito.when(stockService.getStocks(1, null)).thenReturn(stocks);

        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/stocks")
                            .queryParam("page", "1")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", Matchers.hasSize(1)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void createStocks() {
        doNothing().when(stockService).createStock(isA(Stock.class));

        try {
            mockMvc.perform(MockMvcRequestBuilders.post("/api/stocks")
                            .content("{\"name\":\"abc\", \"currentPrice\": 2.3}")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getStock() {
        Stock stock = new Stock();
        stock.setId(1l);
        stock.setName("ABC");
        stock.setCurrentPrice(2.3);
        stock.setLastUpdate(new Date());
        Mockito.when(stockService.getStock(1l)).thenReturn(stock);

        try {
            mockMvc.perform(MockMvcRequestBuilders.get(String.format("/api/stocks/%d", 1))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void updateStock() {
        doNothing().when(stockService).updateStock(isA(Long.class), isA(Double.class));

        try {
            mockMvc.perform(MockMvcRequestBuilders.patch(String.format("/api/stocks/%d", 1))
                            .content("2.35")
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void deleteStock() {
        doNothing().when(stockService).deleteStock(isA(Long.class));

        try {
            mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/api/stocks/%d", 1))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}