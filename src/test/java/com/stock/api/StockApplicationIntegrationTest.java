package com.stock.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stock.api.dao.StockDAO;
import com.stock.api.model.Stock;
import com.stock.api.utils.TestUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@DisplayName("Stock Application Integration Tests")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
public class StockApplicationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StockDAO stockDAO;

    private TestUtils testUtils;
    @BeforeAll
    void initTestUtils() {
        this.testUtils = new TestUtils(this.mockMvc);
    }

    @Test
    @DisplayName("Stock creation integration test")
    void createStock() throws Exception {
        MvcResult result = testUtils.testCreateStock("{\"name\": \"ABC\", \"currentPrice\": 2.35}");
        Assertions.assertThat(result).isNotEqualTo(null);
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Get stock list with no page size given integration test")
    void getStocksWithNoPageSize() throws Exception {
        MvcResult result = testUtils.testGetStocks("1", null);
        Assertions.assertThat(result).isNotEqualTo(null);
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        String response = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode stocks = mapper.readTree(response);

        Assertions.assertThat(stocks.size()).isEqualTo(10);
    }

    @Test
    @DisplayName("Get stock list with given page size integration test")
    void getStocksWithPageSize() throws Exception {
        MvcResult result = testUtils.testGetStocks("1", "5");
        Assertions.assertThat(result).isNotEqualTo(null);
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        String response = result.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode stocks = mapper.readTree(response);

        Assertions.assertThat(stocks.size()).isEqualTo(5);
    }

    @Test
    @DisplayName("Get stock item by given id integration test")
    void getStock() throws Exception  {
        MvcResult result = testUtils.testGetStock(3l);

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
    @DisplayName("Update stock item by given id integration test")
    void updateStock() throws Exception {
        MvcResult result = testUtils.testUpdateStock(2l, "2.25");

        Assertions.assertThat(result).isNotEqualTo(null);
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        Stock stock = stockDAO.findById(2l, Stock.class);
        Assertions.assertThat(stock.getCurrentPrice()).isEqualTo(2.25d);
    }

    @Test
    @DisplayName("Delete stock item by given id integration test")
    void deleteStock() throws Exception {
        MvcResult result = testUtils.testDeleteStock(1l);

        Assertions.assertThat(result).isNotEqualTo(null);
        Assertions.assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        Stock stock = stockDAO.findById(1l, Stock.class);
        Assertions.assertThat(stock).isNull();
    }
}