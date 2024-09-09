package com.example.Product_Service.servie;

import com.example.Product_Service.entity.ProductEntity;
import com.example.Product_Service.exception.InputValidation;
import com.example.Product_Service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public void addProducts(String name, String description, String category, String brand, Double price, MultipartFile image) throws IOException {
        List<String> userError = new ArrayList<>();
        if (name == null || name.isEmpty()) {
            userError.add("Product name cannot be empty");
        }
        if (description == null || description.isEmpty()) {
            userError.add(" Product Description name cannot be empty");
        }
        if (category == null || category.isEmpty()) {
            userError.add("Product  category name cannot be empty");
        }
        if (brand == null || brand.isEmpty()) {
            userError.add("Product brand cannot be empty");
        }
        if (image == null || image.isEmpty()) {
            userError.add("file cannot be empty");
        }
        if (!userError.isEmpty()) {
            throw new InputValidation(userError, "Input Validation Failed");
        }


        final String UPLOAD_DIR = "uploads/";

        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Get the original filename
        String originalFilename = image.getOriginalFilename();

        // Create the file path where the image will be stored
        Path filePath = uploadPath.resolve(originalFilename);

        // Save the file to the specified path
        Files.write(filePath, image.getBytes());

        // Read the image bytes from the saved file
        byte[] imageData = Files.readAllBytes(filePath);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(name);
        productEntity.setDescription(description);
        productEntity.setCategory(category);
        productEntity.setBrand(brand);
        productEntity.setPrice(price);

        productEntity.setPrdImage(imageData); // Storing the image as a byte array (BLOB)
        productRepository.save(productEntity);
    }



        public ProductEntity getProductById(Long pid) {
        List<String> userError = new ArrayList<>();
        ProductEntity productEntity = productRepository.findByPid(pid);


        if (productEntity == null) {
            userError.add("Product not found");
        }
        if (!userError.isEmpty()) {
            throw new InputValidation(userError, "Input Validation Failed");
        }

        return productEntity;
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }


    public List<ProductEntity> searchProduct(String name) {

        return productRepository.findByNameContainingIgnoreCase(name);
    }
}


