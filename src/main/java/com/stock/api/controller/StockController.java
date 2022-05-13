package com.stock.api.controller;

import com.stock.api.dto.StockDTO;
import com.stock.api.mapper.StockMapper;
import com.stock.api.mapper.StockMapperImpl;
import com.stock.api.model.Stock;
import com.stock.api.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private StockService stockService;

    private StockMapper stockMapper = new StockMapperImpl();

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<StockDTO> getStocks(){
        List<StockDTO> list = stockService.getStocks().stream()
                .map(stockMapper::toDTO)
                .collect(Collectors.toList());
        return list;
    }

    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void createStocks(@RequestBody StockDTO stockDTO){
        Stock stock = stockMapper.toEntity(stockDTO);
        stockService.createStocks(stock);
    }

    @CrossOrigin
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody StockDTO getStock(@PathVariable long id){
        Stock stock = stockService.getStock(id);
        return stockMapper.toDTO(stock);
    }

    @CrossOrigin
    @PatchMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void updateStock(@PathVariable long id){
        stockService.updateStock(id);
    }

    @CrossOrigin
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void deleteStock(@PathVariable long id){
        stockService.deleteStock(id);
    }
}
