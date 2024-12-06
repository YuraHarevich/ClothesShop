package com.Harevich.core.mapper;

import com.Harevich.core.model.Clothes;
import com.Harevich.core.dto.ClothesRequest;

public class ClothesMapper {
    public static Clothes toClothes(ClothesRequest request){
        return Clothes.builder()
                .name(request.name())
                .color(request.color())
                .size(request.size())
                .price(request.price())
                .availableQuantity(request.availableQuantity())
                .build();
    }
    public static ClothesRequest toClothesRequest(Clothes clothes){
        return new ClothesRequest(
                clothes.getName(),
                clothes.getColor(),
                clothes.getSize(),
                clothes.getPrice(),
                clothes.getAvailableQuantity()
        );
    }
}
