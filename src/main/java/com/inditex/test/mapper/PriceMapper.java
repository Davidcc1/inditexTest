package com.inditex.test.mapper;

import com.inditex.test.model.ProductPriceResponse;
import com.inditex.test.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    ProductPriceResponse toProductPriceResponse(PriceEntity price);
}
