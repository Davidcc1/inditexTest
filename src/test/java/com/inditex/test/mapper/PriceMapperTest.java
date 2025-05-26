package com.inditex.test.mapper;

import com.inditex.test.model.ProductPriceResponse;
import com.inditex.test.entity.PriceEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PriceMapperTest {

    private final PriceMapper priceMapper = PriceMapper.INSTANCE;

    @Test
    void shouldMapAllFieldsCorrectly() {
        PriceEntity entity = PriceEntity.builder()
                .productId(35455L)
                .brandId(1)
                .priceList(2)
                .startDate(LocalDateTime.of(2020, 6, 14, 15, 0))
                .endDate(LocalDateTime.of(2020, 6, 14, 18, 30))
                .price(25.45)
                .currency("EUR")
                .priority(1)
                .build();

        ProductPriceResponse response = priceMapper.toProductPriceResponse(entity);

        assertThat(response.getProductId()).isEqualTo(35455L);
        assertThat(response.getBrandId()).isEqualTo(1);
        assertThat(response.getPriceList()).isEqualTo(2);
        assertThat(response.getStartDate()).isEqualTo("2020-06-14T15:00");
        assertThat(response.getEndDate()).isEqualTo("2020-06-14T18:30");
        assertThat(response.getPrice()).isEqualTo(25.45);
    }

    @Test
    void shouldHandleNullFieldsGracefully() {
        PriceEntity entity = PriceEntity.builder()
                .productId(null)
                .brandId(null)
                .priceList(null)
                .startDate(null)
                .endDate(null)
                .price(null)
                .currency(null)
                .priority(null)
                .build();

        ProductPriceResponse response = priceMapper.toProductPriceResponse(entity);

        assertThat(response).isNotNull();
        assertThat(response.getProductId()).isNull();
        assertThat(response.getBrandId()).isNull();
        assertThat(response.getPriceList()).isNull();
        assertThat(response.getStartDate()).isNull();
        assertThat(response.getEndDate()).isNull();
        assertThat(response.getPrice()).isNull();
    }

    @Test
    void shouldMapPartialFields() {
        PriceEntity entity = PriceEntity.builder()
                .productId(99999L)
                .brandId(7)
                .priceList(null)
                .startDate(LocalDateTime.now())
                .price(19.99)
                .currency("USD")
                .build();

        ProductPriceResponse response = priceMapper.toProductPriceResponse(entity);

        assertThat(response.getProductId()).isEqualTo(99999L);
        assertThat(response.getBrandId()).isEqualTo(7);
        assertThat(response.getPriceList()).isNull();
        assertThat(response.getPrice()).isEqualTo(19.99);
    }
}
