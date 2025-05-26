package com.inditex.test.service.implementation;

import com.inditex.test.entity.PriceEntity;
import com.inditex.test.mapper.PriceMapper;
import com.inditex.test.model.ProductPriceResponse;
import com.inditex.test.repository.PriceRepository;
import com.inditex.test.service.ProductPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

    private final PriceRepository priceRepository;

    private PriceMapper mapper;

    @Autowired
    public ProductPriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public ProductPriceResponse getProductPriceByDate(LocalDateTime requestedDate, Long productId, Long brandId) {

        List<PriceEntity> validPrices = Optional.ofNullable(priceRepository.findValidPrices(productId, brandId, requestedDate)).orElseThrow();

        return mapper.toProductPriceResponse(validPrices.getFirst());

    }
}
