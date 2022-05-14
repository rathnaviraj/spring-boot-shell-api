package com.stock.api.service;

import com.stock.api.model.Stock;

import java.util.List;

public interface StockService {
    List<Stock> getStocks(int pageNo, Integer pageSize);

    void createStocks(Stock stock);

    Stock getStock(long id);

    void updateStock(long id, double price);

    void deleteStock(long id);
}
