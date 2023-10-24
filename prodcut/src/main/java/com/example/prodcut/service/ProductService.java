package com.example.prodcut.service;

import com.example.prodcut.dto.ProductDTO;
import com.example.prodcut.entity.Photo;
import com.example.prodcut.entity.Product;
import com.example.prodcut.repository.IPhotoRepository;
import com.example.prodcut.repository.ProductRepository;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements  IProductService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    IPhotoRepository photoRepository;

    @Override
    public ProductDTO createProduct(ProductDTO productDTO, MultipartFile[] photos) throws IOException {

        Product product = ProductDTO.fromDTOtoEntity(productDTO);
        product.setCreated_at(LocalDateTime.now());
        /*
        List<String> photosPaths=storeImage(photos);
        product.setPhoto(photosPaths);

         */

        List<Photo> photoEntities = new ArrayList<>();
        if (photos != null && photos.length > 0) {
            List<String> photosPaths = storeImage(photos);
            for (String path : photosPaths) {
                Photo photo = new Photo();
                photo.setPath(path);
                photoEntities.add(photo);
            }
        }

        product.setPhotos(photoEntities);

        Product savedProduct = productRepository.save(product);

        for (Photo photo : photoEntities) {
            photo.setProduct(savedProduct);
            photoRepository.save(photo);
        }

        return ProductDTO.fromEntityToDTO(savedProduct);

    }

    public List<String> storeImage(MultipartFile[] images) throws IOException {
        List<String> imagePaths = new ArrayList<>();
        for (MultipartFile image : images) {
            if (image != null && !image.isEmpty()) {
                String fileName = StringUtils.cleanPath(image.getOriginalFilename());

                /*
                // Save image to Spring Boot project directory
                String currentDir = System.getProperty("user.dir");
                Path springUploadDir = Paths.get(currentDir, "src", "main", "resources", "images");
                if (!Files.exists(springUploadDir)) {
                    Files.createDirectories(springUploadDir);
                }
                try (InputStream inputStream = image.getInputStream()) {
                    Path springFilePath = springUploadDir.resolve(fileName);
                    Files.copy(inputStream, springFilePath, StandardCopyOption.REPLACE_EXISTING);
                    //String springImagePath = springFilePath.toAbsolutePath().toString();
                    //imagePaths.add(springImagePath);
                } catch (IOException ex) {
                    throw new IOException("Could not store file " + fileName + ". Please try again!", ex);
                }

                */

                // Save image to Angular project directory
                Path angularUploadDir = Paths.get("C:","Users", "Mohamed", "Desktop","Malek","ms-front","jobFinder","src", "assets", "images");
                if (!Files.exists(angularUploadDir)) {
                    Files.createDirectories(angularUploadDir);
                }
                try (InputStream inputStream = image.getInputStream()) {
                    Path angularFilePath = angularUploadDir.resolve(fileName);
                    Files.copy(inputStream, angularFilePath, StandardCopyOption.REPLACE_EXISTING);
                    String angularImagePath = "/assets/images/" + fileName;
                    imagePaths.add(angularImagePath);
                } catch (IOException ex) {
                    throw new IOException("Could not store file " + fileName + ". Please try again!", ex);
                }
            }
        }
        for (String img:imagePaths){
            System.out.println(img);
        }
        return imagePaths;
    }


    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products =productRepository.findProductsLatest();
        return products.stream()
                .map(product -> new ProductDTO().fromEntityToDTO(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findProductById(Long id) {
        System.out.println("find product by id begin");
        Product product = productRepository.findById(id).orElse(null);
        return ProductDTO.fromEntityToDTO(product);

    }

    @Override
    public List<ProductDTO> searchProducts(String name, String description, Long brand, Float maxPrice, Float minPrice) {
        System.out.println("begin search");
        List<Product> products = productRepository.findAll((Specification<Product>) (root, cq, cb) -> {
            Predicate p = cb.conjunction();
            if (name != null) {
                p = cb.and(p, cb.like(root.get("name"), "%" + name + "%"));
            }

            if (description != null) {
                p = cb.and(p, cb.like(root.get("description"), "%" + description + "%"));
            }

            if(brand!=null){
                p=cb.and(p,cb.equal(root.get("brandId"),brand));
            }

            if (maxPrice != null) {
                p = cb.and(p, cb.lessThanOrEqualTo(root.get("price"), maxPrice));
            }

            if (minPrice != null) {
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("price"), minPrice));
            }

            cq.orderBy(cb.desc(root.get("created_at")));
            return p;
        });


        return products.stream()
                .map(product -> new ProductDTO().fromEntityToDTO(product))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> similarProducts(Long id) {
        System.out.println("similar product");
        List<Product> products=productRepository.findAll();
        Product product = productRepository.findById(id).get();
        List<Product> similarProducts= new ArrayList<>();
        for (Product p:products) {
            if (product.getBrandId().equals(p.getBrandId()) && product.getId()!=p.getId()) {
                similarProducts.add(p);
            }
        }
        return similarProducts.stream()
                .map(similarProduct -> new ProductDTO().fromEntityToDTO(similarProduct))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO) {
        Product product = productRepository.findById(productDTO.getId()).get();

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setBrandId(productDTO.getBrandId());
        productRepository.save(product);
        return productDTO.fromEntityToDTO(product);
    }

    @Override
    public ProductDTO addPhoto(Long productId, MultipartFile[] newPhotos) throws IOException {
        Product product = productRepository.findById(productId).orElse(null);

        List<String> newPhotoPaths = storeImage(newPhotos);

        List<Photo> existingPhotos = product.getPhotos();
        for (String path : newPhotoPaths) {
            Photo photo = new Photo();
            photo.setPath(path);
            photo.setProduct(product);
            existingPhotos.add(photo);
            photoRepository.save(photo);
        }

        product.setPhotos(existingPhotos);
        Product updatedProduct = productRepository.save(product);
        return ProductDTO.fromEntityToDTO(updatedProduct);

    }

    @Override
    public ProductDTO deletePhoto(Long productId, Long photoId) {
        Product product = productRepository.findById(productId).orElse(null);

        List<Photo> existingPhotos = product.getPhotos();

        Photo photoToDelete = existingPhotos.stream()
                .filter(photo -> photo.getId().equals(photoId))
                .findFirst()
                .orElse(null);

        existingPhotos.remove(photoToDelete);
        // Delete the photo entity from the database
        photoRepository.delete(photoToDelete);
        product.setPhotos(existingPhotos);
        productRepository.save(product);
        return ProductDTO.fromEntityToDTO(product);



    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).get();
        productRepository.delete(product);
    }

    @Override
    public List<Product> findAllProductsByBrand(Long id) {
        return productRepository.findAllByBrandId(id);
    }

    @Override
    public List<Object[]> getProductsByMonth() {
        return productRepository.getJobsByMonth();
    }
}
