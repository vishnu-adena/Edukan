package com.adena.productservice.controllers;


import com.adena.productservice.Exceptions.ProductNotFound;
import com.adena.productservice.dto.RequestDTO;
import com.adena.productservice.models.Category;
import com.adena.productservice.models.Product;
import com.adena.productservice.service.IFakeStoreProductService;
import com.adena.productservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products/fakestore")
public class FakeStoreProductController {

    IFakeStoreProductService productService;
    @Autowired
    public FakeStoreProductController(@Qualifier("fakeStore") IFakeStoreProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<Product> getAllProducts() {
        ResponseEntity response = new ResponseEntity(productService.getAllProducts(),
                HttpStatus.OK);
        return response;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody RequestDTO product) {
        ResponseEntity response = new ResponseEntity(
                productService.addProduct(product),
                HttpStatus.CREATED
        );
        return response;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id) throws ProductNotFound {
       ResponseEntity responseEntity = new ResponseEntity(
               productService.getProductById(id),
               HttpStatus.OK
       );
        return responseEntity;
    }

    @GetMapping("/categories")
    public ResponseEntity<String> getAllCategories() {
        ResponseEntity responseEntity = new ResponseEntity(
          productService.getAllCategories(),
          HttpStatus.OK
        );
        return responseEntity;
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Product> getProductsByCategory(@PathVariable("category") String category) {

        ResponseEntity responseEntity = new ResponseEntity(
          productService.getAllProductsByCategory(category),
          HttpStatus.OK
        );
        return responseEntity;
    }

}
