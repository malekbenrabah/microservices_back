package com.example.prodcut.service;

import com.example.prodcut.dto.ProductDTO;
import com.example.prodcut.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {

    public ProductDTO createProduct(ProductDTO productDTO, MultipartFile[] photos) throws IOException;

    public List<ProductDTO> getAllProducts();

    public ProductDTO findProductById(Long id);

    public List<ProductDTO> searchProducts(String name, String description, String brand, Float maxPrice, Float minPrice);

    public List<ProductDTO> similarProducts(Long id);

    public ProductDTO updateProduct(ProductDTO productDTO);

    public ProductDTO addPhoto(Long productId, MultipartFile[] newPhotos) throws IOException;

    public ProductDTO deletePhoto(Long productId, Long photoId);


    public void deleteProduct(Long id);
}
