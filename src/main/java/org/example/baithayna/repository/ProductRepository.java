package org.example.baithayna.repository;

import org.example.baithayna.model.Category;
import org.example.baithayna.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByPriceBetween(double minPrice, double maxPrice);
    List<Product> findByCategory(Category category);
    List<Product> findTop3ByOrderByPriceDesc();
    List<Product> findByOrderByAmount();
}

