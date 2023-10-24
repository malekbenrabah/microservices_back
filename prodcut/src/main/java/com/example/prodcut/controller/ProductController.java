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
@CrossOrigin(origins ="http://localhost:4200")
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
    public List<ProductDTO> getAllProducts() {
        return  productService.getAllProducts();
    }

    @GetMapping("/findById")
    public ProductDTO findProductById(@RequestParam("id") Long id) {
        return productService.findProductById(id);
    }

    @GetMapping("/search")
    public List<ProductDTO> searchProducts(@RequestParam(value="name",required = false) String name,
                                           @RequestParam(value="description",required = false) String description,
                                           @RequestParam(value="brand",required = false) Long brand,
                                           @RequestParam(value="maxPrice",required = false) Float maxPrice,
                                           @RequestParam(value="minPrice",required = false) Float minPrice) {
        return productService.searchProducts(name,description,brand,maxPrice, minPrice);
    }


    @GetMapping("/similarProducts")
    public List<ProductDTO> similarProducts(@RequestParam("id") Long id){
        return productService.similarProducts(id);
    }

    @PutMapping("/update")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO)  {
        return  productService.updateProduct(productDTO);
    }

    @PutMapping("/updatePhoto")
    public ProductDTO addPhoto( @RequestParam("id") Long productId, @RequestParam(value="photo",required = false) MultipartFile[] newPhotos) throws IOException {
        return productService.addPhoto(productId, newPhotos);
    }

    @DeleteMapping("/deletePhoto")
    public ProductDTO deletePhoto( @RequestParam("productId") Long productId, @RequestParam("photoId")  Long photoId) {
        return productService.deletePhoto(productId,photoId);
    }


    @DeleteMapping("/delete")
    public void deleteProduct(@RequestParam("id") Long id){
        productService.deleteProduct(id);
    }

    @GetMapping("/findByBrand/{brand-id}")
    public List<Product> findAllProductsByBrand(@PathVariable("brand-id")Long id){
        return productService.findAllProductsByBrand(id);
    }

    @GetMapping("/getProdByMonth")
    public List<Object[]> getProductsByMonth() {
        return productService.getProductsByMonth();
    }

}
