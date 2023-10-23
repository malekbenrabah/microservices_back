package com.example.brand.controller;


import com.example.brand.entity.Brand;
import com.example.brand.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    IBrandService brandService;


    @PostMapping("/add")
    public Brand addBrand(@RequestBody  Brand brand){
        return brandService.addBrand(brand);
    }

    @GetMapping("/brands")
    public List<Brand> showBrands(){
        return brandService.showBrands();
    }
}
