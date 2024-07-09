package com.adena.productservice.service;

import com.adena.productservice.Exceptions.ProductNotFound;
import com.adena.productservice.dto.RequestDTO;
import com.adena.productservice.dto.ResponseDTO;
import com.adena.productservice.models.Product;

import java.util.List;
import java.util.Optional;

public interface IFakeStoreProductService {

    Optional<Product> getProductById(long id);

    List<Product> getAllProducts();

    Product replaceProduct(Product product) throws ProductNotFound;

    Product addNewProduct(Product product);

    Product replaceProduct(long id, RequestDTO requestDTO);

    List<String> getAllCategories();


    List<Product> getAllProductsByCategory(String category);

    Product addProduct(RequestDTO requestDTO);
}
