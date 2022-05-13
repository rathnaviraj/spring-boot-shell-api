package com.stock.api.mapper;

import com.stock.api.dto.StockDTO;
import com.stock.api.model.Stock;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StockMapper {

    StockDTO toDTO(Stock stock);

    Stock toEntity(StockDTO stockDTO);
}
