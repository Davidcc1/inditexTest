package com.inditex.test.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductPriceResponse {

    private Long productId;
    private Long brandId;
    private Long priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;

}
