package com.srinith.ecom_proj.controller;

import com.srinith.ecom_proj.model.Product;
import com.srinith.ecom_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
            System.out.println("========== ADD PRODUCT REQUEST ==========");
            System.out.println("Received product: " + product);
            System.out.println("Product name: " + product.getName());
            System.out.println("Product brand: " + product.getBrand());
            System.out.println("Product price: " + product.getPrice());
            System.out.println("Product stockQuantity: " + product.getStockQuantity());
            System.out.println("Product available: " + product.isProductAvailable());
            System.out.println("Product category: " + product.getCategory());
            System.out.println("Product releaseDate: " + product.getReleaseDate());
            System.out.println("Image file: " + (imageFile != null ? imageFile.getOriginalFilename() : "null"));
            System.out.println("==========================================");
            
            Product product1 = service.addproduct(product, imageFile);
            System.out.println("Product saved successfully with ID: " + product1.getId());
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }
        catch(Exception e){
            System.err.println("========== ERROR IN addProduct ==========");
            System.err.println("ERROR type: " + e.getClass().getName());
            System.err.println("ERROR message: " + e.getMessage());
            e.printStackTrace(); // Log the full stack trace
            System.err.println("==========================================");
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



}
