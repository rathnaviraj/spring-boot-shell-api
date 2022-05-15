package com.stock.api.dao;

import com.stock.api.model.Stock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class StockDAOImplTest {

    @Autowired StockDAO stockDAO;

    @Test
    void getStocks() {
        Iterable<Stock> stocks = stockDAO.getStocks(1,10);
        Assertions.assertThat(stocks).extracting(Stock::getName).contains("ABC");
    }
}