package com.adena.productservice.controllers;

import com.adena.productservice.Exceptions.*;
import com.adena.productservice.dto.ResponseDTO;
import com.adena.productservice.models.Category;
import com.adena.productservice.models.Product;
import com.adena.productservice.dto.RequestDTO;
import com.adena.productservice.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    IProductService productService;

    @Autowired
    public ProductController(@Qualifier("DBProductService") IProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<ResponseDTO> getProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") long id) throws ProductNotFound {
        ResponseEntity responseEntity = new ResponseEntity(
                productService.getProductById(id),
                HttpStatus.OK
        );
        return responseEntity;
    }

    @GetMapping("/categories")
    public List<String> getCategories() {
        return productService.getAllCategories();
    }

    @GetMapping("/category/{categoryName}")
    public List<ResponseDTO> getAllProductsByCatogory(@PathVariable("categoryName") String categoryName) throws ProductNotFound {
        return productService.getProductsByCategory(categoryName);
    }

    @PostMapping("")
    public Product addProduct(@RequestBody RequestDTO requestDTO) throws ProductNotFound {
        Product product = new Product();
        product.setName(requestDTO.getTitle());
        product.setDescription(requestDTO.getDescription());
        product.setPrice(requestDTO.getPrice());
        product.setCategory(new Category());
        product.getCategory().setName(requestDTO.getTitle());
        product.setImageUrl(requestDTO.getImage());
        Product saveProduct = productService.addNewProduct(product);
        return saveProduct;
    }

    @PatchMapping("/{id}")
    public ResponseDTO updateProduct(@RequestBody RequestDTO requestDTO, @PathVariable("id") long id) throws ProductNotFound {
        return productService.UpdateProduct(id, requestDTO);
    }

    @PutMapping("/{id}")
    public ResponseDTO replaceProduct(@RequestBody RequestDTO requestDTO, @PathVariable("id") long id) throws ProductNotFound {
        if (requestDTO.getTitle() == null || requestDTO.getPrice() <= 0 || requestDTO.getDescription() == null || requestDTO.getImage() == null || requestDTO.getCategory() == null) {
            throw new ProductNotFound("Add all Parameters for product with id " + id + " for Put Request");
        }
        return productService.replaceProduct(id, requestDTO);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") long id) throws ProductNotFound {
        return productService.deleteProduct(id);
    }
}
