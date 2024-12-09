package com.Harevich.core.controller;

import com.Harevich.core.dto.ClothesResponse;
import com.Harevich.core.external.OrderLineRequest;
import com.Harevich.core.external.OrderRequest;
import com.Harevich.core.external.OrderResponse;
import com.Harevich.core.mapper.ClothesMapper;
import com.Harevich.core.model.Clothes;
import com.Harevich.core.dto.ClothesRequest;
import com.Harevich.core.service.ClothesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/clothes")
@Tag(name = "Clothes")
public class ClothesController {
    private final ClothesService service;

    @GetMapping
    @Operation(summary = "finds all clothes", description = "returns all available clothes")
    public ResponseEntity<List<ClothesResponse>> findAllClothes(){
        return ResponseEntity.ok(service.findAll()
                .stream()
                .map(ClothesMapper::toClothesResponse)
                .collect(Collectors.toList()));
    }
    @GetMapping("/{id}")
    @Operation(summary = "finds clothes by id", description = "requires id and returns clothes that matches this id")
    public ResponseEntity<ClothesResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                ClothesMapper
                        .toClothesResponse(service.findById(id)));
    }
    @PostMapping
    @Operation(summary = "creates clothes", description = "requires info about clothes")
    public Long createClothes(@RequestBody ClothesRequest request){
        return service.createClothes(request);
    }

    @PostMapping("/purchase")
    @Operation(summary = "purchasing clothes", description = "requires info about clothes")
    public OrderResponse purchaseClothes(@RequestBody OrderRequest request){
        List<OrderLineRequest> errorOrderLineRequests = service.checkClothes(request);
        if(errorOrderLineRequests.isEmpty()) {
            service.purchaseClothes(request);
            return new OrderResponse(null);
        }
        else{
            return new OrderResponse(errorOrderLineRequests);
        }
    }

}
