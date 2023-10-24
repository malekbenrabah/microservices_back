package com.example.brand.service;

import com.example.brand.entity.Brand;
import com.example.brand.entity.BrandProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public interface IBrandService {

   public Brand addBrand(Brand brand, MultipartFile photo) throws IOException;

   public List<Brand> showBrands();

   public Brand updateBrand(Brand brand);

   public Brand updateBrandPhoto(Long id ,MultipartFile photo)throws IOException;

   public void deleteBrand(Long id);

   public Optional<Brand> findById(Long id);

   public FullBrandResponse findBrandsProducts(Long id);

   public List<BrandProductDTO> getAllBrandsProducts();
}
