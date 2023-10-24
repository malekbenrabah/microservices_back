package com.example.brand.controller;


import com.example.brand.entity.Brand;
import com.example.brand.entity.BrandProductDTO;
import com.example.brand.service.FullBrandResponse;
import com.example.brand.service.IBrandService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/brands")
@CrossOrigin(origins ="http://localhost:4200")
public class BrandController {

    @Autowired
    IBrandService brandService;


    @PostMapping("/add")
    public Brand addBrand(@RequestParam("brand") String brandJSON,
                          @RequestParam(value="photo",required = false) MultipartFile photo) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Brand brand = objectMapper.readValue(brandJSON, Brand.class);
        return brandService.addBrand(brand,photo);
    }

    @GetMapping("/getBrands")
    public List<Brand> showBrands(){
        return brandService.showBrands();
    }

    @GetMapping("/findById")
    public Optional<Brand> findById(@RequestParam("id") Long id){
        return brandService.findById(id);
    }
    @PutMapping("/updateBrand")
    public Brand updateBrand(@RequestBody Brand brand)  {
        return brandService.updateBrand(brand);
    }

    @PutMapping("/updateBrandPhoto")
    public Brand updateBrandPhoto(@RequestParam("id") Long id,
                                  @RequestParam(value="photo",required = false) MultipartFile photo) throws IOException{
        return brandService.updateBrandPhoto(id,photo);
    }

    @DeleteMapping("/deleteBrand")
    public void deleteBrand(@RequestParam("id") Long id){
        brandService.deleteBrand(id);
    }

    @GetMapping("/getProducts/{id}")
    public FullBrandResponse findBrandsProducts(@PathVariable("id") Long id){
        return brandService.findBrandsProducts(id);
    }

    @GetMapping("/getAllBrandsProducts")
    public List<BrandProductDTO> getAllBrandsProducts(){
        return  brandService.getAllBrandsProducts();
    }
}
