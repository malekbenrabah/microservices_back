package com.example.prodcut.controller;


import com.example.prodcut.dto.ProductDTO;
import com.example.prodcut.entity.Product;
import com.example.prodcut.service.IProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    IProductService productService;

    @PostMapping("/addProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestParam("product") String productJSON, @RequestParam(value="photo",required = false) MultipartFile[] photo) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ProductDTO productDTO = objectMapper.readValue(productJSON, ProductDTO.class);
        return productService.createProduct(productDTO,photo);
    }

    @GetMapping("/getProducts")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getAllProducts() {
        return  productService.getAllProducts();
    }

    @GetMapping("/findById")
    @ResponseStatus(HttpStatus.FOUND)
    public ProductDTO findProductById(@RequestParam("id") Long id) {
        return productService.findProductById(id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.FOUND)
    public List<ProductDTO> searchProducts(@RequestParam(value="title",required = false) String name,
                                           @RequestParam(value="description",required = false) String description,
                                           @RequestParam(value="brand",required = false) String brand,
                                           @RequestParam(value="maxPrice",required = false) Float maxPrice,
                                           @RequestParam(value="minPrice",required = false) Float minPrice) {
        return productService.searchProducts(name,description,brand,maxPrice, minPrice);
    }


    @GetMapping("/similarProducts")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> similarProducts(@RequestParam("id") Long id){
        return productService.similarProducts(id);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO)  {
        return  productService.updateProduct(productDTO);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@RequestParam("id") Long id){
        productService.deleteProduct(id);
    }



}
