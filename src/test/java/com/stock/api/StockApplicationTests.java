package com.stock.api;

import com.stock.api.controller.StockController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class StockApplicationTests {

	@Autowired
	StockController stockController;
	@Test
	void contextLoads() {
		Assertions.assertThat(stockController).isExactlyInstanceOf(StockController.class).isNotEqualTo(null);
	}

}
