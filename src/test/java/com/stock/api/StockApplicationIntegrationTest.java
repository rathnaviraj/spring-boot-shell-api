package com.stock.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.api.dao.StockDAO;
import com.stock.api.model.Stock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class StockApplicationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StockDAO stockDAO;

    @Test
    void createStock() throws Exception {
        MvcResult result = testCreateStock("{\"name\": \"ABC\", \"currentPrice\": 2.35}");
        Assertions.assertThat(result).isNotEqualTo(null);
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void getStocksWithNoPageSize() throws Exception {
        MvcResult result = testGetStocks("1", null);
        Assertions.assertThat(result).isNotEqualTo(null);
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        String response = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode stocks = mapper.readTree(response);

        Assertions.assertThat(stocks.size()).isEqualTo(10);
    }

    @Test
    void getStocksWithPageSize() throws Exception {
        MvcResult result = testGetStocks("1", "5");
        Assertions.assertThat(result).isNotEqualTo(null);
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        String response = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode stocks = mapper.readTree(response);

        Assertions.assertThat(stocks.size()).isEqualTo(5);
    }

    @Test
    void getStock() throws Exception  {
        MvcResult result = testGetStock(3l);

        Assertions.assertThat(result).isNotEqualTo(null);
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        String response = result.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        JsonNode actualObj = mapper.readTree(response);

        Stock stock = stockDAO.findById(3l, Stock.class);

        Assertions.assertThat(actualObj.get("id").asLong()).isEqualTo(stock.getId());
        Assertions.assertThat(actualObj.get("name").asText()).isEqualTo(stock.getName());
        Assertions.assertThat(actualObj.get("currentPrice").asDouble()).isEqualTo(stock.getCurrentPrice());
    }

    @Test
    void updateStock() throws Exception {
        MvcResult result = testUpdateStock(2l, "2.25");

        Assertions.assertThat(result).isNotEqualTo(null);
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        Stock stock = stockDAO.findById(2l, Stock.class);
        Assertions.assertThat(stock.getCurrentPrice()).isEqualTo(2.25d);
    }

    @Test
    void deleteStock() throws Exception {
        MvcResult result = testDeleteStock(1l);

        Assertions.assertThat(result).isNotEqualTo(null);
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        Stock stock = stockDAO.findById(1l, Stock.class);
        Assertions.assertThat(stock).isNull();
    }

    private MvcResult testCreateStock(String content) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/stocks")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        return result;
    }
    private MvcResult testGetStocks(String page, String pageSize) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/stocks")
                        .queryParam("page", page)
                        .queryParam("pageSize", pageSize)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        return result;
    }

    private MvcResult testGetStock(long id) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(String.format("/api/stocks/%d", id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        return result;
    }

    private MvcResult testUpdateStock(long id, String value) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.patch(String.format("/api/stocks/%d", id))
                        .content(value)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        return result;
    }

    private MvcResult testDeleteStock(long id) throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(String.format("/api/stocks/%d", id))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        return result;
    }
}