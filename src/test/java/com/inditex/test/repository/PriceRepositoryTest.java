package com.inditex.test.repository;

import com.inditex.test.entity.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(properties = "spring.sql.init.mode=never")
class PriceRepositoryTest {

    @Autowired
    private PriceRepository priceRepository;

    @BeforeEach
    void setUp(){

        List<Integer> integers = List.of(0, 1, 2);
        List<PriceEntity> prices = new ArrayList<>();
        integers.forEach( elem -> {
            PriceEntity price = PriceEntity.builder()
                    .brandId(1)
                    .productId(35455L)
                    .startDate(LocalDateTime.of(2020+elem, 6, 14, 0, 0))
                    .endDate(LocalDateTime.of(2020+elem, 12, 31, 23, 59))
                    .priceList(elem)
                    .priority(0)
                    .price(new BigDecimal(30+elem*0.5))
                    .currency("EUR")
                    .build();
            prices.add(price);

            }
        );

        prices.add(PriceEntity.builder()
                .brandId(1)
                .productId(35455L)
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59))
                .priceList(4)
                .priority(1)
                .price(new BigDecimal("45.50"))
                .currency("EUR")
                .build());

        priceRepository.saveAll(prices);
    }

    @Test
    @DisplayName("Get a single result")
    void testFindValidPrices() {

        List<PriceEntity> results = priceRepository.findValidPrices(
                35455L,
                1,
                LocalDateTime.of(2021, 6, 14, 10, 0)
        );

        assertThat(results).hasSize(1);
        assertThat(results.getFirst().getPrice()).isEqualTo(new BigDecimal("30.5"));
    }

    @Test
    @DisplayName("Get zero results")
    void testNotFound() {

        List<PriceEntity> results = priceRepository.findValidPrices(
                35455L,
                2,
                LocalDateTime.of(2020, 6, 14, 10, 0)
        );

        assertThat(results).isEmpty();
    }

    @Test
    @DisplayName("Get max priority 1st")
    void testMaxPriority() {

        List<PriceEntity> results = priceRepository.findValidPrices(
                35455L,
                1,
                LocalDateTime.of(2020, 8, 14, 0, 0)
        );

        assertThat(results).hasSize(2);
        assertThat(results.getFirst().getPrice()).isEqualTo(new BigDecimal("45.50"));
    }
}
