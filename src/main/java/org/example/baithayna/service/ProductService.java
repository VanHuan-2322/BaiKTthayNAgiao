package org.example.baithayna.service;

import org.example.baithayna.model.Category;
import org.example.baithayna.model.Product;
import org.example.baithayna.repository.CategoryRepository;
import org.example.baithayna.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> searchByPriceRange(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> sortByAmount() {
        return productRepository.findByOrderByAmount();
    }

    public List<Product> findTop3ExpensiveProducts() {
        return productRepository.findTop3ByOrderByPriceDesc();
    }

    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }
}

