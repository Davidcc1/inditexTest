package com.inditex.test.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Base64;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PricingIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAt10June14() throws Exception {
        mockMvc.perform(get("/api/pricing")
                        .param("requestedDate", "2020-06-14 10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("admin:admin".getBytes())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("35.5 EUR"));
    }

    @Test
    void testAt16June14() throws Exception {
        mockMvc.perform(get("/api/pricing")
                        .param("requestedDate", "2020-06-14 16:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("admin:admin".getBytes())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("25.45 EUR"));
    }

    @Test
    void testAt21June14() throws Exception {
        mockMvc.perform(get("/api/pricing")
                        .param("requestedDate", "2020-06-14 21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("admin:admin".getBytes())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("35.5 EUR"));
    }

    @Test
    void testAt10June15() throws Exception {
        mockMvc.perform(get("/api/pricing")
                        .param("requestedDate", "2020-06-15 10:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("admin:admin".getBytes())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("30.5 EUR"));
    }

    @Test
    void testAt21June16() throws Exception {
        mockMvc.perform(get("/api/pricing")
                        .param("requestedDate", "2020-06-16 21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("admin:admin".getBytes())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value("38.95 EUR"));
    }

    @Test
    void testNoContent() throws Exception {
        mockMvc.perform(get("/api/pricing")
                        .param("requestedDate", "2025-06-16 21:00:00")
                        .param("productId", "35455")
                        .param("brandId", "1")
                        .header("Authorization", "Basic " + Base64.getEncoder().encodeToString("admin:admin".getBytes())))
                .andExpect(status().isNotFound());
    }
}
