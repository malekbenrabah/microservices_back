package com.example.brand.client;

import com.example.brand.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service", url = "${application.config.products-url}")
public interface ProductClient {

    @GetMapping("/findByBrand/{brand-id}")
    List<Product> findAllProductsByCategory(@PathVariable("brand-id") Long id);
}
