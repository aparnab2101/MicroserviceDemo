package com.example.Product_Service.repository;

import com.example.Product_Service.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <ProductEntity,Long>{
    @Query("select p from ProductEntity p where p.pid = :pid")
    ProductEntity findByPid(@Param("pid") Long pid);

    List<ProductEntity> findByNameContainingIgnoreCase(String name);
}
