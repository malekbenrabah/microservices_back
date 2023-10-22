package com.example.prodcut.dto;

import com.example.prodcut.entity.Product;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long id;

    private String name;

    private String description;

    private Float price;

    private String brand;

    private LocalDateTime created_at;

    private List<String> photo;

    public static ProductDTO fromEntityToDTO(Product product){

        ProductDTO productDTO = ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .brand(product.getBrand())
                .created_at(product.getCreated_at())
                .photo(product.getPhoto())
                .build();

        return productDTO;
    }

    public static Product fromDTOtoEntity(ProductDTO productDTO){

        Product product=Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .brand(productDTO.getBrand())
                .created_at(productDTO.getCreated_at())
                .photo(productDTO.getPhoto())
                .build();
        return product;

    }

}
