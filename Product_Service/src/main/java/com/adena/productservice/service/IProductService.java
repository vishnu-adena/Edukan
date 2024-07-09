package com.adena.productservice.service;

import com.adena.productservice.Exceptions.ProductNotFound;
import com.adena.productservice.dto.RequestDTO;
import com.adena.productservice.dto.ResponseDTO;
import com.adena.productservice.models.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService  {


    Optional<ResponseDTO> getProductById(long id) throws ProductNotFound;

    List<ResponseDTO> getAllProducts();

    List<ResponseDTO> getProductsByCategory(String category) throws ProductNotFound;

    Product addNewProduct(Product product) throws ProductNotFound;

    ResponseDTO replaceProduct(long id, RequestDTO requestDTO) throws ProductNotFound;

    List<String> getAllCategories();

    ResponseDTO UpdateProduct(long id, RequestDTO requestDTO) throws ProductNotFound;

    String deleteProduct(long id) throws ProductNotFound;
}

