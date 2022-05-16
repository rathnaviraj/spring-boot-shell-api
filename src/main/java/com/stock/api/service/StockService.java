package com.stock.api.service;

import com.stock.api.exception.ResourceNotFoundException;
import com.stock.api.model.Stock;

import java.util.List;

public interface StockService {

    /**
     * Service method to get list of paginated stock objects.
     * @param pageNo is the number of the page need to list on paginating through stock data.
     * @param pageSize is optional page size for pagination, default or env configured value will evaluate if not provided.
     * @return list of stock objects
     */
    List<Stock> getStocks(int pageNo, Integer pageSize);

    /**
     * Service method to create new stock item.
     * @param stock is the detached entity of new stock item
     */
    void createStock(Stock stock);

    /**
     * Service method to get specific stock item by id.
     * @param id is the primary key id of stock item
     * @return a persisted stock object or null if not exists
     */
    Stock getStock(long id);

    /**
     * Service method to update the price value of specific stock item by id.
     * @param id is the primary key id of stock item
     * @param price is new price value needs to be updated to stock item
     * @throws ResourceNotFoundException if the specific stock item is not found to update
     */
    void updateStock(long id, double price) throws ResourceNotFoundException;

    /**
     * Service method to delete specific stock item by id.
     * @param id is the primary key id of stock item
     * @throws ResourceNotFoundException if the specific stock item is not found to delete
     */
    void deleteStock(long id) throws ResourceNotFoundException;
}
