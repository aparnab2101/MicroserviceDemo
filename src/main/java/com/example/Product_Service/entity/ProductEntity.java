package com.example.Product_Service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private Long pid;

    @Column(name = "prd_name", length = 255)
    private String name;

    @Column(name = "prd_des", length = 255)
    private String description;

    @Column(name = "prd_category", length = 255)
    private String category;

    @Column(name = "prd_brand", length = 255)
    private String brand;

    @Column(name = "prd_price")
    private Double price;

    @Column(name = "prd_image")
    private byte[] prdImage;
}
