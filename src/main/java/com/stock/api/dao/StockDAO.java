package com.stock.api.dao;

import com.stock.api.model.Stock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockDAO extends BaseDAO {

    /**
     * DAO method to get list of paginated stock objects.
     * @param pageNo is the number of the page need to list on paginating through stock data.
     * @param pageSize is optional page size for pagination, default or env configured value will evaluate if not provided.
     * @return list of stock objects
     */
    List<Stock> getStocks(int pageNo, int pageSize);
}
