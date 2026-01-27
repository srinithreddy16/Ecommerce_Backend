package com.srinith.ecom_proj.controller;

import com.srinith.ecom_proj.model.Product;
import com.srinith.ecom_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService service;



    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }


    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){

        Product product = service.getProductById(id);

        if (product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/product", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addProduct(@RequestPart("product") Product product,
                                        @RequestPart(value = "imageFile", required = false) MultipartFile imageFile){

        try {
            Product savedProduct = service.addproduct(product, imageFile);
            return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
        }
        catch(Exception e){
            String errorMessage = e.getMessage() != null ? e.getMessage() : "Unknown error occurred";
            return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){

        Product product = service.getProductById(productId);
        if (product == null || product.getImageDate() == null) {
            return ResponseEntity.notFound().build();
        }
        
        byte[] imageFile = product.getImageDate();
        String imageType = product.getImageType();
        
        // Handle null imageType
        if (imageType == null || imageType.isEmpty()) {
            imageType = "image/jpeg"; // Default to JPEG if not specified
        }

        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(imageType))
                .body(imageFile);


    }
    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id, @RequestPart("product") Product product,
                                                @RequestPart(value = "imageFile", required = false) MultipartFile imageFile){
        try {
            Product updatedProduct = service.updateProduct(id, product, imageFile);
            if (updatedProduct != null) {
                return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to Update", HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to Update: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to Update: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id){

        Product product = service.getProductById(id);

        if (product != null){
            service.deleteProduct(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);

        }
        else
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);

    }


    @GetMapping("/product/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String keyword){
        List<Product> products = service.searchProducts(keyword);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }








}
