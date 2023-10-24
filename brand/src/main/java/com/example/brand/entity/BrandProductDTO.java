package com.example.brand.entity;

import lombok.*;


@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandProductDTO {

    private String name;
    private Integer productCount;


}