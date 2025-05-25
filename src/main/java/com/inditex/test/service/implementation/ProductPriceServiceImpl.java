package com.inditex.test.service.implementation;

import com.inditex.test.model.ProductPriceResponse;
import com.inditex.test.service.ProductPriceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

    @Override
    public ProductPriceResponse getProductPriceByDate(LocalDateTime requestedDate, Long productId, Long brandId) {
        return null;
    }
}
