package com.example.brand.service;


import com.example.brand.Repository.BrandRepository;
import com.example.brand.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService implements  IBrandService{

    @Autowired
    BrandRepository brandRepository;

    @Override
    public Brand addBrand(Brand brand) {
        brandRepository.save(brand);
    }

    @Override
    public List<Brand> showBrands() {
        return brandRepository.findAll();
    }
}
