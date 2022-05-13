package com.stock.api.dao;

import com.stock.api.model.Stock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StockDAOImpl extends BaseDAOImpl implements StockDAO{

    @Override
    public List<Stock> getStocks() {

        String query = "FROM Stock stock ORDER BY stock.name ASC";
        return getSession().createQuery(query).list();
    }
}
