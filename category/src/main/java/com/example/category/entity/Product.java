package com.example.category.entity;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    private String name;

    private String description;

    private Float price;

    private String brand;

    private LocalDateTime created_at;

}
