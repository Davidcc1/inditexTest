package com.inditex.test.service;

import com.inditex.test.model.ProductPriceResponse;

import java.time.LocalDateTime;

public interface ProductPriceService {

    ProductPriceResponse getProductPriceByDate(LocalDateTime requestedDate, Long productId, Long brandId);

}
