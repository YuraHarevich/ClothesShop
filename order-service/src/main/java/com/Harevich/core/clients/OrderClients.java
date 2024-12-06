package com.Harevich.core.clients;

import com.Harevich.core.order.dto.ClothesResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "clothes",
        url = "${application.config.clothes-url}"
)
public interface OrderClients {
    @GetMapping("/{customer-id}")
    Optional<ClothesResponse> findCustomerById(@PathVariable("customer-id") String customerId);

}
