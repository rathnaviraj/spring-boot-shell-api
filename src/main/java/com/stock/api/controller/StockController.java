package com.stock.api.controller;

import com.stock.api.dto.ErrorDTO;
import com.stock.api.dto.StockDTO;
import com.stock.api.exception.ResourceNotFoundException;
import com.stock.api.mapper.StockMapper;
import com.stock.api.mapper.StockMapperImpl;
import com.stock.api.model.Stock;
import com.stock.api.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public @ResponseBody List<StockDTO> getStocks(@RequestParam int page,
                                                  @RequestParam(required = false) Integer pageSize){
        List<Stock> list = stockService.getStocks(page, pageSize);
        return list != null ? list.stream().map(stockMapper::toDTO).collect(Collectors.toList()) : null;
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
    public @ResponseBody void updateStock(@PathVariable long id, @RequestBody double price){
        stockService.updateStock(id, price);
    }

    @CrossOrigin
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void deleteStock(@PathVariable long id){
        stockService.deleteStock(id);
    }

    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<ErrorDTO> handleAllExceptions(RuntimeException ex) {
        ErrorDTO error = new ErrorDTO(ex);
        error.setTitle("Application Error");
        error.setInstance("-");
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ErrorDTO> handleAllExceptions(ResourceNotFoundException ex) {
        ErrorDTO error = new ErrorDTO(ex);
        error.setTitle(ex.getTitle());
        error.setInstance(ex.getResource());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<ErrorDTO>(error, HttpStatus.NOT_FOUND);
    }
}
