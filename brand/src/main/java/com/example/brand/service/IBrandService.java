package com.example.brand.service;

import com.example.brand.Repository.BrandRepository;
import com.example.brand.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IBrandService {

   public Brand addBrand(Brand brand);

   public List<Brand> showBrands();
}
