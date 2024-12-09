package com.Harevich.core.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Clothes {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clothes_seq")
    @SequenceGenerator(name = "clothes_seq", sequenceName = "clothes_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String color;
    @Enumerated(EnumType.STRING)
    private Size size;
    private BigDecimal price;
    private Integer availableQuantity;
    public void reduceAvailableQuantity(Integer quantity){
        availableQuantity-=quantity;
    }
}
