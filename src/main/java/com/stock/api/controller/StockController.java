package com.stock.api.controller;

import com.stock.api.dto.StockDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<StockDTO> getStocks(){
        return null;
    }

    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void createStocks(@RequestBody StockDTO stockDTO){

    }

    @CrossOrigin
    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody StockDTO getStock(@PathVariable int id){
        return null;
    }

    @CrossOrigin
    @PatchMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody StockDTO updateStock(@PathVariable int id){
        return null;
    }

    @CrossOrigin
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody void deleteStock(@PathVariable int id){

    }
}
