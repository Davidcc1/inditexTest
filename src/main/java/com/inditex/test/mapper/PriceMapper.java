package com.inditex.test.mapper;

import com.inditex.test.model.ProductPriceResponse;
import com.inditex.test.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    @Mapping(target = "price", expression = "java(getPriceWithCurrecy(priceEntity))")
    ProductPriceResponse toProductPriceResponse(PriceEntity priceEntity);


    default String getPriceWithCurrecy(PriceEntity priceEntity){
        return Optional.ofNullable(priceEntity.getPrice()).map(price -> price.toString().concat(" ").concat(priceEntity.getCurrency())).orElse("");
    }
}
