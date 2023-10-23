package com.example.category.entity;

import lombok.*;
import java.util.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullSCategoryResponse {
    private String name;
    private Category category;
    private List<Product> products;
}
