package com.example.brand.service;


import com.example.brand.Repository.BrandRepository;
import com.example.brand.client.ProductClient;
import com.example.brand.entity.Brand;
import com.example.brand.entity.BrandProductDTO;
import com.example.brand.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService implements  IBrandService{

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    ProductClient productClient;

    @Override
    public Brand addBrand(Brand brand, MultipartFile photo) throws IOException {

        brand.setPhoto(storeImage(photo));
        return brandRepository.save(brand);
    }

    public String storeImage(MultipartFile profileImage) throws IOException {
        String imagePath = null;
        String angularImagePath = null;

        if (profileImage != null && !profileImage.isEmpty()) {
            String fileName = StringUtils.cleanPath(profileImage.getOriginalFilename());

            // save image to angular project directory
            Path angularUploadDir = Paths.get("C:","Users", "Mohamed", "Desktop","Malek","ms-front","jobFinder","src", "assets", "images");
            if (!Files.exists(angularUploadDir)) {
                Files.createDirectories(angularUploadDir);
            }
            try (InputStream inputStream = profileImage.getInputStream()) {
                Path angularFilePath = angularUploadDir.resolve(fileName);
                Files.copy(inputStream, angularFilePath, StandardCopyOption.REPLACE_EXISTING);
                angularImagePath = "/assets/images/" + fileName;
            } catch (IOException ex) {
                throw new IOException("Could not store file " + fileName + ". Please try again!", ex);
            }



        }
        return angularImagePath;
    }


    @Override
    public List<Brand> showBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Brand updateBrand(Brand brand){
        Brand b= brandRepository.findById(brand.getId()).get();
        b.setName(brand.getName());
        b.setPhoto(b.getPhoto());
        return brandRepository.save(b);
    }

    @Override
    public Brand updateBrandPhoto(Long id, MultipartFile photo) throws IOException {
        Brand brand = brandRepository.findById(id).get();
        brand.setPhoto(storeImage(photo));
        return  brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(Long id) {
        Brand brand = brandRepository.findById(id).get();
        brandRepository.delete(brand);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        System.out.println("find by id starts");
        return brandRepository.findById(id);
    }

    @Override
    public FullBrandResponse findBrandsProducts(Long id) {
        Brand brand = brandRepository.findById(id).orElse(null);
        List<Product> products= productClient.findAllProductsByCategory(id);

        return FullBrandResponse.builder()
                .name(brand.getName())
                .products(products)
                .build();
    }

    @Override
    public List<BrandProductDTO> getAllBrandsProducts() {

        List<BrandProductDTO> brandsprod = new ArrayList<>();

        List<Brand> brands= brandRepository.findAll();
        for (Brand b:brands) {
            Brand brand = brandRepository.findById(b.getId()).orElse(null);
            List<Product> products= productClient.findAllProductsByCategory(b.getId());
            brandsprod.add(new BrandProductDTO(brand.getName(),products.size()));
        }
        return brandsprod;
    }
}
