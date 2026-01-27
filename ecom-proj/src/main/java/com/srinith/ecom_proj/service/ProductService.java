package com.srinith.ecom_proj.service;

import com.srinith.ecom_proj.model.Product;
import com.srinith.ecom_proj.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Product addproduct(Product product, MultipartFile imageFile) throws IOException {
        try {
            // Handle image file if provided
            if (imageFile != null && !imageFile.isEmpty()) {
                product.setImageName(imageFile.getOriginalFilename());
                product.setImageType(imageFile.getContentType());
                product.setImageDate(imageFile.getBytes());
            }
            
            // Validate required fields
            if (product.getName() == null || product.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Product name is required");
            }
            if (product.getPrice() == null) {
                throw new IllegalArgumentException("Product price is required");
            }
            
            // Set default stockQuantity if null
            if (product.getStockQuantity() == null) {
                product.setStockQuantity(0);
            }
            
            return repo.save(product);
        } catch (Exception e) {
            throw new IOException("Error saving product: " + e.getMessage(), e);
        }
    }

    public Product updateProduct(int id, Product product, MultipartFile imageFile) throws IOException {
        try {
            // Check if product exists
            Product existingProduct = repo.findById(id).orElse(null);
            if (existingProduct == null) {
                throw new IllegalArgumentException("Product with ID " + id + " not found");
            }
            
            // Set the ID to ensure we update the existing product, not create a new one
            product.setId(id);
            
            // Handle image file if provided
            if (imageFile != null && !imageFile.isEmpty()) {
                product.setImageName(imageFile.getOriginalFilename());
                product.setImageType(imageFile.getContentType());
                product.setImageDate(imageFile.getBytes());
            } else {
                // Keep existing image data if no new image is provided
                product.setImageName(existingProduct.getImageName());
                product.setImageType(existingProduct.getImageType());
                product.setImageDate(existingProduct.getImageDate());
            }
            
            // Validate required fields
            if (product.getName() == null || product.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Product name is required");
            }
            if (product.getPrice() == null) {
                throw new IllegalArgumentException("Product price is required");
            }
            
            // Set default stockQuantity if null
            if (product.getStockQuantity() == null) {
                product.setStockQuantity(0);
            }
            
            return repo.save(product);
        } catch (Exception e) {
            throw new IOException("Error updating product: " + e.getMessage(), e);
        }
    }

    public void deleteProduct(int id) {
        repo.deleteById(id);
    }
}
