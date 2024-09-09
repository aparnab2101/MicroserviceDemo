package com.example.Product_Service.model;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductModel {
    private String name;
    private String description;
    private String category;
    private String brand;
    private Double price;
    private byte[] image;


}
