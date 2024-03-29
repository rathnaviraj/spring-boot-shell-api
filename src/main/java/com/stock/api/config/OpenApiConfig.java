 package com.stock.api.config;

 import io.swagger.v3.oas.models.OpenAPI;
 import io.swagger.v3.oas.models.info.Info;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;

 @Configuration
 public class OpenApiConfig {
 
     @Bean
     public OpenAPI openApi() {
         return new OpenAPI()
                 .info(new Info()
                         .title("StockAPI")
                         .description("API for Stock Data")
                         .version("v1.0")
                 );
     }
 }