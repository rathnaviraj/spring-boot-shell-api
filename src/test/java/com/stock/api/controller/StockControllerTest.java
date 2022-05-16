package com.stock.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.api.exception.ResourceNotFoundException;
import com.stock.api.model.Stock;
import com.stock.api.service.StockService;
import com.stock.api.utils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(StockController.class)
class StockControllerTest {

    @MockBean
    StockService stockService;

    @Autowired
    MockMvc mockMvc;

    private TestUtils testUtils;
    @BeforeAll
    void initTestUtils() {
        this.testUtils = new TestUtils(this.mockMvc);
    }

    @Test
    void getStocks() throws Exception {
        Stock stock = new Stock();
        List<Stock> stocks = Arrays.asList(stock);
        Mockito.when(stockService.getStocks(1, null)).thenReturn(stocks);

        MvcResult result = testUtils.testGetStocks("1", null);
    }

    @Test
    void createStocks() throws Exception {
        doNothing().when(stockService).createStock(isA(Stock.class));

        MvcResult result = testUtils.testCreateStock("{\"name\": \"ABC\", \"currentPrice\": 2.35}");

        Assertions.assertThat(result).isNotEqualTo(null);
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void getStock() throws Exception {
        Stock stock = new Stock();
        stock.setId(3l);
        stock.setName("ABC");
        stock.setCurrentPrice(2.3);
        stock.setLastUpdate(new Date());

        Mockito.when(stockService.getStock(3l)).thenReturn(stock);

        MvcResult result = testUtils.testGetStock(3l);

        Assertions.assertThat(result).isNotEqualTo(null);
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        String response = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(response);

        Assertions.assertThat(actualObj.get("id").asLong()).isEqualTo(stock.getId());
        Assertions.assertThat(actualObj.get("name").asText()).isEqualTo(stock.getName());
        Assertions.assertThat(actualObj.get("currentPrice").asDouble()).isEqualTo(stock.getCurrentPrice());
    }

    @Test
    void updateStock() throws Exception {
        long id = 2l;
        doNothing().when(stockService).updateStock(id, 2.25d);

        MvcResult result = testUtils.testUpdateStock(id, "2.25");

        Assertions.assertThat(result).isNotEqualTo(null);
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void updateStockWithException() throws Exception {
        long id = 40l;
        doThrow(new ResourceNotFoundException("Resource Not Found", "Requested resource not found to update", id))
                .when(stockService).updateStock(id, 2.25d);

        MvcResult result = testUtils.testUpdateStock(id, "2.25");

        Assertions.assertThat(result).isNotEqualTo(null);
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void deleteStock() throws Exception {
        doNothing().when(stockService).deleteStock(isA(Long.class));

        MvcResult result = testUtils.testDeleteStock(1l);

        Assertions.assertThat(result).isNotEqualTo(null);
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void deleteStockWithException() throws Exception {
        long id = 40l;
        doThrow(new ResourceNotFoundException("Resource Not Found", "Requested resource not found to delete", id)).
                when(stockService).deleteStock(id);

        MvcResult result = testUtils.testDeleteStock(id);

        Assertions.assertThat(result).isNotEqualTo(null);
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }
}