package com.stock.api.service;

import com.stock.api.dao.StockDAO;
import com.stock.api.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockDAO stockDAO;
    @Override
    public List<Stock> getStocks() {
        return null;
    }

    @Override
    @Transactional
    public void createStocks(Stock stock) {
        stockDAO.save(stock);
    }

    @Override
    public Stock getStock(long id) {
        return stockDAO.findById(id, Stock.class);
    }

    @Override
    public void updateStock(long id) {

    }

    @Override
    public void deleteStock(long id) {

    }
}
