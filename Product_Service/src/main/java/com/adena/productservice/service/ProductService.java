package com.adena.productservice.service;

import com.adena.productservice.Exceptions.ProductNotFound;
import com.adena.productservice.dto.RequestDTO;
import com.adena.productservice.dto.ResponseDTO;
import com.adena.productservice.models.Category;
import com.adena.productservice.models.Product;
import com.adena.productservice.models.UserResponse;
import com.adena.productservice.repositories.CategoryRepository;
import com.adena.productservice.repositories.ProductRespository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("DBProductService")
public class ProductService implements IProductService {

    ProductRespository productRepository;
    CategoryRepository categoryRepository;
    public ProductService(ProductRespository productRepository, CategoryRepository categoryRepository  ) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Optional<ResponseDTO> getProductById(long id) throws ProductNotFound{
        Optional<Product> product = productRepository.findByisDeletedAndId(false,id);
        if (product.isEmpty()){
            throw new ProductNotFound("Product not Found with id "+id);
        }
        Product product1 = product.get();
        ResponseDTO responseDTO = modifyProduct(product1);
        return Optional.ofNullable(responseDTO);
    }

    @Override
    public List<ResponseDTO> getAllProducts() {

        List<Product> products = productRepository.findAllByisDeleted(false);
        List responseDTO = new ArrayList<ResponseDTO>();
        for (Product product : products){
            responseDTO.add(modifyProduct(product));
        }

        return responseDTO;
    }



    @Override
    public ResponseDTO replaceProduct(long id, RequestDTO requestDTO) throws ProductNotFound {
        Optional<Product> product = productRepository.findById(id);
        Product product1 ;
        if (product.isEmpty()){
            throw new ProductNotFound("Product not Found with id "+id);
        }
        product.get().setImageUrl(requestDTO.getImage());
        product.get().setName(requestDTO.getTitle());
        product.get().setPrice(requestDTO.getPrice());
        product.get().setDescription(requestDTO.getDescription());
        Category category;
        Optional<Category> category1 = categoryRepository.findByName(requestDTO.getCategory());
        if (category1.isEmpty()){
            category = new Category();
            category.setName(requestDTO.getCategory());
            Category category2 = categoryRepository.save(category);
        }
        else {
            category = category1.get();
        }
        product.get().setCategory(category);
        Product product2 = productRepository.save(product.get());
        return modifyProduct(product2);
    }

    @Override
    public List<String> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<String> categoriesNames = new ArrayList<>();
        for (Category category : categories) {
            categoriesNames.add(category.getName());
        }
        return categoriesNames;

    }

    @Override
    public ResponseDTO UpdateProduct(long id, RequestDTO requestDTO) throws ProductNotFound {
        Optional<Product> product = productRepository.findByisDeletedAndId(false,id);
        if (product.isEmpty()){
            throw new ProductNotFound("Product not Found with id "+id);
        }
        product.get().setName(requestDTO.getTitle() == null ? product.get().getName() : requestDTO.getTitle());
        product.get().setDescription(requestDTO.getDescription() == null ? product.get().getDescription() : requestDTO.getDescription());
        product.get().setImageUrl(requestDTO.getImage() == null ? product.get().getImageUrl() : requestDTO.getImage());
        product.get().setPrice(requestDTO.getPrice() <= 0 ? product.get().getPrice() : requestDTO.getPrice());
        Category category;
        Optional<Category> category1 = categoryRepository.findByName(requestDTO.getCategory());
        if (category1.isEmpty()){
             Category category2 = new Category();
             category2.setName(requestDTO.getCategory());
            category = categoryRepository.save(category2);
        }
        else {
            category = category1.get();
        }
        product.get().setCategory(requestDTO.
                getCategory()==null ? product.get().getCategory() : category);

        Product product2 = productRepository.save(product.get());
        return modifyProduct(product2);
    }

    public ResponseDTO modifyProduct(Product product){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setTitle(product.getName());
        responseDTO.setDescription(product.getDescription());
        responseDTO.setPrice(product.getPrice());
        responseDTO.setImage(product.getImageUrl());
        responseDTO.setId(product.getId());
        responseDTO.setUpdated_At(product.getUpdated_At());
        responseDTO.setUpdated_At(product.getUpdated_At());
        responseDTO.setCategory(product.getCategory().getName());
        return responseDTO;
    }

    @Override
    public List<ResponseDTO> getProductsByCategory(String category) throws ProductNotFound{

        List<Product> products = productRepository.findAllByisDeletedAndcAndCategoryId(category);
        List response = new ArrayList<>();
        for (Product product : products) {
            response.add(modifyProduct(product));
        }
        return response;
    }
    @Override
    public Product addNewProduct(Product product) throws ProductNotFound {

        Optional<Category> category = categoryRepository.findByName(product.getCategory().getName());
        Category savedCategory ;
        if (category.isEmpty()){
            Category newCategory = new Category();
            newCategory.setName(product.getCategory().getName());
            savedCategory = categoryRepository.save(newCategory);
        }
        else {
            savedCategory = category.get();
        }
        product.setCategory(savedCategory);
        product.setUserId(getUserid());
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    private  long getUserid() {
        long userId;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        userId = jwt.getClaim("userId");
        return userId;
    }

    @Override
    public String deleteProduct(long id) throws ProductNotFound{
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()){
           throw new ProductNotFound("Product not Found with id "+id);
        }
        product.get().setDeleted(true);
        productRepository.save(product.get());
        return "Product Deleted Successfully";
    }
}
