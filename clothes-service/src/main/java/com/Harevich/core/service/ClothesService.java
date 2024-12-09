package com.Harevich.core.service;

import com.Harevich.core.dto.ClothesResponse;
import com.Harevich.core.external.OrderLineRequest;
import com.Harevich.core.external.OrderRequest;
import com.Harevich.core.external.OrderResponse;
import com.Harevich.core.mapper.ClothesMapper;
import com.Harevich.core.model.Clothes;
import com.Harevich.core.dto.ClothesRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.Harevich.core.repository.ClothesRepository;

import java.util.ArrayList;
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

    public List<OrderLineRequest> checkClothes(OrderRequest request) {

        List<OrderLineRequest> errorOrderLineRequests = new ArrayList<>();
        for(OrderLineRequest orderLineRequest:request.orderLines()){
            Optional<Clothes> optional = repository.findById(orderLineRequest.clothesId());
            if(optional.isEmpty() || optional.get().getAvailableQuantity()<orderLineRequest.quantity())
                errorOrderLineRequests.add(orderLineRequest);
        }
        return errorOrderLineRequests;
    }
    public void purchaseClothes(OrderRequest request){
        request.orderLines()
                .stream()
                .forEach(
                        orderLineRequest-> {
                            Clothes clothes = repository.findById(orderLineRequest.clothesId()).get();
                            clothes.reduceAvailableQuantity(orderLineRequest.quantity());
                            repository.save(clothes);
                        }
                );
    }
}
