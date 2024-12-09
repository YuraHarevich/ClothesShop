package com.Harevich.core.clients;

import com.Harevich.core.clothes.dto.ClothesResponse;
import com.Harevich.core.order.dto.OrderRequest;
import com.Harevich.core.order.dto.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@FeignClient(
        name = "clothes",
        url = "${application.config.clothes-url}"
)
public interface ClothesClient {
    @GetMapping("/{clothes-id}")
    Optional<ClothesResponse> findClothesById(@PathVariable("clothes-id") Long clothesId);

    @PostMapping("/purchase")
    OrderResponse purchaseClothes(@RequestBody OrderRequest request);


}
