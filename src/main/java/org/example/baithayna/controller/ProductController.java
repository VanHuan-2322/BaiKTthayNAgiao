package org.example.baithayna.controller;

import org.example.baithayna.model.Category;
import org.example.baithayna.model.Product;
import org.example.baithayna.service.CategoryService;
import org.example.baithayna.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product product = productService.findById(id);
        if (product != null) {
            product.setName(productDetails.getName());
            product.setPrice(productDetails.getPrice());
            product.setAmount(productDetails.getAmount());
            product.setCategory(productDetails.getCategory());
            return ResponseEntity.ok(productService.save(product));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/searchByPrice")
    public List<Product> searchByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return productService.searchByPriceRange(minPrice, maxPrice);
    }

    @GetMapping("/sortByAmount")
    public List<Product> sortByAmount() {
        return productService.sortByAmount();
    }

    @GetMapping("/top3Expensive")
    public List<Product> getTop3ExpensiveProducts() {
        return productService.findTop3ExpensiveProducts();
    }

    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable Long categoryId) {
        Category category = new Category();
        category.setId(categoryId);
        return productService.findByCategory(category);
    }
}

