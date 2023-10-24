package com.example.prodcut.dto;

import com.example.prodcut.entity.Photo;
import com.example.prodcut.entity.Product;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    private Long brandId;

    private LocalDateTime created_at;

    private List<Photo> photos;

    public static ProductDTO fromEntityToDTO(Product product){



        ProductDTO productDTO = ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .brandId(product.getBrandId())
                .created_at(product.getCreated_at())
                .photos(product.getPhotos())
                .build();

        return productDTO;
    }

    public static Product fromDTOtoEntity(ProductDTO productDTO){

        Product product=Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .brandId(productDTO.getBrandId())
                .created_at(productDTO.getCreated_at())
                .photos(productDTO.getPhotos())
                .build();
        return product;

    }

}
