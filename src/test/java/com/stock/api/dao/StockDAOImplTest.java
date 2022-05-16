package com.stock.api.dao;

import com.stock.api.model.Stock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
class StockDAOImplTest {

    @Autowired
    StockDAO stockDAO;

    @Test
    @DisplayName("Get stock list service layer unit test")
    void getStocks() {
        List<Stock> stocks = stockDAO.getStocks(1,1);
        Assertions.assertThat(stocks.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Get stock by id dao layer unit test")
    void findById() {
        Stock stocks = stockDAO.findById(1,Stock.class);
        Assertions.assertThat(stocks.getId()).isEqualTo(1);
    }

    @Test
    @Transactional
    @DisplayName("Save stock dao layer unit test")
    void save() {
        Stock stock = new Stock();
        stock.setName("AAA");
        stock.setCurrentPrice(2.35);
        stockDAO.save(stock);

        Stock savedStock = stockDAO.findById(stock.getId(), Stock.class);
        Assertions.assertThat(savedStock).isNotEqualTo(null);
    }

    @Test
    @Transactional
    @DisplayName("Update stock dao layer unit test")
    void update() {
        Stock stock = stockDAO.findById(1l, Stock.class);

        double priceUpdate = 2.34d;
        stock.setCurrentPrice(priceUpdate);
        stockDAO.update(stock);

        Stock updatedStock = stockDAO.findById(stock.getId(), Stock.class);
        Assertions.assertThat(updatedStock.getCurrentPrice()).isEqualTo(priceUpdate);
    }

    @Test
    @Transactional
    @DisplayName("Delete stock dao layer unit test")
    void delete() {
        Stock stock = stockDAO.findById(5l, Stock.class);

        stockDAO.delete(stock);

        Stock deletedStock = stockDAO.findById(stock.getId(), Stock.class);
        Assertions.assertThat(deletedStock).isNull();
    }
}