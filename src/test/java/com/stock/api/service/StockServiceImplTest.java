package com.stock.api.service;

import com.stock.api.dao.StockDAO;
import com.stock.api.model.Stock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @InjectMocks
    private StockService stockService = new StockServiceImpl();

    @Mock
    private StockDAO stockDAO;


    @Test
    void getStocks() {
        List<Stock> sample = new ArrayList<>();
        when(stockDAO.getStocks(1, 10)).thenReturn(sample);

        List<Stock> list = stockService.getStocks(1, 10);
        assertNotNull(list);
    }

    @Test
    void createStocks() {
        doNothing().when(stockDAO).save(isA(Stock.class));
        Stock stock = new Stock();
        stockService.createStock(stock);
    }

    @Test
    void getStock() {
        Stock result = new Stock();
        when(stockDAO.findById(1, Stock.class)).thenReturn(result);

        Stock stock = stockService.getStock(1);
        assertNotNull(stock);
    }

    @Test
    void updateStock() {
        Stock result = new Stock();
        when(stockDAO.findById(1, Stock.class)).thenReturn(result);
        doNothing().when(stockDAO).update(isA(Stock.class));

        stockService.updateStock(1, 2.3);
    }

    @Test
    void deleteStock() {
        Stock result = new Stock();
        when(stockDAO.findById(1, Stock.class)).thenReturn(result);
        doNothing().when(stockDAO).delete(isA(Stock.class));

        stockService.deleteStock(1);

    }
}