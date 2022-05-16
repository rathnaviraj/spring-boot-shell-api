package com.stock.api.service;

import com.stock.api.dao.StockDAO;
import com.stock.api.model.Stock;
import com.stock.api.service.impl.StockServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@DisplayName("Stock Service Layer Unit Tests")
@ExtendWith(MockitoExtension.class)
class StockServiceImplTest {

    @InjectMocks
    private StockService stockService = new StockServiceImpl();

    @Mock
    private StockDAO stockDAO;

    @Test
    @DisplayName("Get stock list service layer unit test")
    void getStocks() {
        List<Stock> sample = new ArrayList<>();
        when(stockDAO.getStocks(1, 10)).thenReturn(sample);

        List<Stock> list = stockService.getStocks(1, 10);
        Assertions.assertThat(list).isNotEqualTo(null);
    }

    @Test
    @DisplayName("Stock creation service layer unit test")
    void createStocks() {
        doNothing().when(stockDAO).save(isA(Stock.class));
        Stock stock = new Stock();
        stockService.createStock(stock);

        verify(stockDAO,times(1)).save(stock);
    }

    @Test
    @DisplayName("Get stock item by given id service layer unit test")
    void getStock() {
        Stock result = new Stock();
        when(stockDAO.findById(1, Stock.class)).thenReturn(result);

        Stock stock = stockService.getStock(1);
        Assertions.assertThat(stock).isNotEqualTo(null);
    }

    @Test
    @DisplayName("Update stock item by given id service layer unit test")
    void updateStock() {
        Stock result = new Stock();
        when(stockDAO.findById(1, Stock.class)).thenReturn(result);
        doNothing().when(stockDAO).update(isA(Stock.class));
        stockService.updateStock(1, 2.3);

        verify(stockDAO,times(1)).findById(1, Stock.class);
        verify(stockDAO,times(1)).update(result);
    }

    @Test
    @DisplayName("Delete stock item by given id service layer unit test")
    void deleteStock() {
        Stock result = new Stock();
        when(stockDAO.findById(1l, Stock.class)).thenReturn(result);
        doNothing().when(stockDAO).delete(isA(Stock.class));

        stockService.deleteStock(1l);

        verify(stockDAO,times(1)).findById(1l, Stock.class);
        verify(stockDAO,times(1)).delete(result);
    }
}