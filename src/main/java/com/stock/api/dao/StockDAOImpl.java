package com.stock.api.dao;

import com.stock.api.model.Stock;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StockDAOImpl extends BaseDAOImpl implements StockDAO{

    @Override
    public List<Stock> getStocks(int pageNo, int pageSize) {

        String queryString = "FROM Stock stock ORDER BY stock.name ASC";
        Query query = getSession().createQuery(queryString);
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);

        return query.list();
    }
}
