package com.inditex.test.controller;

import com.inditex.test.model.ProductPriceResponse;
import com.inditex.test.service.ProductPriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Tag(name = "Pricing", description = "Query all prices")
@RestController
@RequestMapping("/api/pricing")
@RequiredArgsConstructor
public class PricingController {

    private final ProductPriceService productPriceService;

    @Operation(summary = "Get price for a product in a specific date")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping
    public ResponseEntity<ProductPriceResponse> getPrice(
            @Parameter(name ="requestedDate", description = "Requested date", example = "2025-05-24 23:00:00")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @RequestParam("requestedDate") LocalDateTime requestedDate,
            @Parameter(name ="productId", description = "Product indentifier", example = "35455")
            @RequestParam("productId")  Long productId,
            @Parameter(name ="brandId", description = "Brand identifier", example = "1")
            @RequestParam("brandId")  Long brandId) {


        return ResponseEntity.ok(productPriceService.getProductPriceByDate(requestedDate, productId, brandId));
    }
}

