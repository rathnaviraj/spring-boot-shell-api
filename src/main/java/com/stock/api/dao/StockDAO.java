package com.stock.api.dao;

import com.stock.api.model.Stock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockDAO extends BaseDAO {
    List<Stock> getStocks(int pageNo, int pageSize);
}
