package com.example.Product_Service.controller;

import com.example.Product_Service.entity.ProductEntity;
import com.example.Product_Service.exception.InputValidation;
import com.example.Product_Service.model.ProductModel;
import com.example.Product_Service.servie.ProductService;
import com.example.Product_Service.util.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/productService")
public class ProductController {

    @Autowired
    ProductService productService;

    //Adding product api
    @PostMapping(value = "/addProduct")
    public ResponseEntity<?> addProduct(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("category") String category,
            @RequestParam("brand") String brand,
            @RequestParam("price") Double price,
            @RequestParam("image") MultipartFile image){

       try{
           productService.addProducts(name,description,category,brand,price,image);
           return ResponseHandler.generateResponse(HttpStatus.OK,"Product added Successfully",null);
       }catch (InputValidation e){
           return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST,"Input Validation failed",e.getErrors());
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

    }

    //Get Product by Id

    @GetMapping("/getProduct/{pid}")
        public ResponseEntity<Object> getProductById(@PathVariable("pid") Long pid) {
        try {
           ProductEntity productModel= productService.getProductById(pid);
           return ResponseEntity.ok(productModel);
        } catch (InputValidation e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "Input Validation failed", e.getErrors());
        }
    }

    //get all products
    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductEntity>> findAllProducts() {
        try {
            List<ProductEntity> products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    //search product
    @GetMapping("/searchProduct")
    public ResponseEntity<List<ProductEntity>> searchProduct(@RequestParam("name") String name){
        try{
            List<ProductEntity> products = productService.searchProduct(name);
            return ResponseEntity.ok(products);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
