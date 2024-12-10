package com.Harevich.core.mapper;

import com.Harevich.core.kafka.consumer.SupplyRequest;
import com.Harevich.core.kafka.producer.ClothesSupply;

public class SupplyClothesMapper {
    public static SupplyRequest toSupply(ClothesSupply clothesSupply){
        return new SupplyRequest(
                clothesSupply.orderLines()
        );
    }
    public static ClothesSupply toClothes(SupplyRequest supplyRequest){
        return new ClothesSupply(
                supplyRequest.orderLines()
        );
    }
}
