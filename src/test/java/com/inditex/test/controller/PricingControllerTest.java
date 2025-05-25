package com.inditex.test.controller;

import com.inditex.test.model.ProductPriceResponse;
import com.inditex.test.service.ProductPriceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;


import java.time.*;
import java.time.format.DateTimeFormatter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PricingController.class)
class PricingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductPriceService productPriceService;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Test
    void getPriceOk() throws Exception {
        LocalDate fixedDate = LocalDate.of(2025, 5, 5);
        Clock fixed = Clock.fixed(fixedDate.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());

        when(productPriceService.getProductPriceByDate(any(),any(),any())).thenReturn(new ProductPriceResponse());
        mockMvc.perform(get("/api/pricing")
                .param("requestedDate", formatter.format(LocalDateTime.now(fixed)))
                .param("productId", "1119")
                .param("brandId", "1"))
                .andExpect(status().isOk());
    }
}