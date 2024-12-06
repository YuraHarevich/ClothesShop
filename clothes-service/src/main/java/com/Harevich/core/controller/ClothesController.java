package com.Harevich.core.controller;

import com.Harevich.core.model.Clothes;
import com.Harevich.core.dto.ClothesRequest;
import com.Harevich.core.service.ClothesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/clothes")
@Tag(name = "Clothes")
public class ClothesController {
    private final ClothesService service;

    @GetMapping
    @Operation(summary = "finds all clothes", description = "returns all available clothes")
    public ResponseEntity<List<Clothes>> findAllClothes(){
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping("/{id}")
    @Operation(summary = "finds clothes by id", description = "requires id and returns clothes that matches this id")
    public ResponseEntity<Clothes> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    @PostMapping
    @Operation(summary = "creates clothes", description = "requires info about clothes")
    public Long createClothes(@RequestBody ClothesRequest request){
        return service.createClothes(request);
    }

}
