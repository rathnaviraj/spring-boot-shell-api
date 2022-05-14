package com.stock.api.service;

import com.stock.api.dao.StockDAO;
import com.stock.api.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class StockServiceImpl implements StockService {

    @Value("${DEFAULT_PAGE_SIZE:10}")
    private int DEFAULT_PAGE_SIZE;

    @Autowired
    private StockDAO stockDAO;

    @Override
    public List<Stock> getStocks(int pageNo, Integer pageSize) {
        int pageSizeFinal = pageSize == null ? DEFAULT_PAGE_SIZE : pageSize;
        return stockDAO.getStocks(pageNo, pageSizeFinal);
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
    @Transactional
    public void updateStock(long id, double price) {
        Stock stock = stockDAO.findById(id, Stock.class);
        stock.setCurrentPrice(price);
        stockDAO.update(stock);
    }

    @Override
    @Transactional
    public void deleteStock(long id) {
        Stock stock = stockDAO.findById(id, Stock.class);
        stockDAO.delete(stock);
    }
}
