package com.example.brand.service;

import com.example.brand.entity.Product;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullBrandResponse {

    private String name;
    private List<Product> products;
}
