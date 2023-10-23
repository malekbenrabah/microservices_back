package com.example.category.entity;
import java.util.*;

import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FullCategoryResponse {
    private String name;
    private List<FullSCategoryResponse> subcategories ;
}
