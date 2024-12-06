package com.Harevich.core.service;

import com.Harevich.core.mapper.ClothesMapper;
import com.Harevich.core.model.Clothes;
import com.Harevich.core.dto.ClothesRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.Harevich.core.repository.ClothesRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClothesService {
    private final ClothesRepository repository;
    public List<Clothes> findAll(){
        return repository.findAll();
    }
    public Clothes findById(Long id){
        Optional<Clothes> optional = repository.findById(id);
        return optional
                .orElseThrow(()->new EntityNotFoundException("Product not found with the id"+id));
    }

    public Long createClothes(ClothesRequest request) {
        return repository.saveAndFlush(ClothesMapper
                .toClothes(request)).getId();
    }
}
