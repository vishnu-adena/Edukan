package com.adena.productservice.service;

import com.adena.productservice.Exceptions.ProductNotFound;
import com.adena.productservice.dto.RequestDTO;
import com.adena.productservice.dto.ResponseDTO;
import com.adena.productservice.models.Category;
import com.adena.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Qualifier("fakeStore")
public class FakeStoreProductService implements IFakeStoreProductService{

    RestTemplate restTemplate ;
    ProductService productService;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate, ProductService productService) {
        this.restTemplate = restTemplate;
        this.productService = productService;
    }

    public  Product getSinglefromFakeStore(ResponseDTO responseDTO){
        Product product = new Product();
        product.setId(responseDTO.getId());
        product.setName(responseDTO.getTitle());
        product.setDescription(responseDTO.getDescription());
        product.setPrice(responseDTO.getPrice());
        product.setImageUrl(responseDTO.getImage());
        Category category = new Category();
        category.setName(responseDTO.getCategory());
        product.setCategory(category);

        return product;
    }

    @Override
    public Optional<Product> getProductById(long id) {
        Product product = new Product();
        ResponseDTO responseDTO = restTemplate.getForObject("https://fakestoreapi.com/products/"+id,
                ResponseDTO.class);
        product = getSinglefromFakeStore(responseDTO);

        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> getAllProducts(){
        ResponseDTO[] responseList = restTemplate.getForObject("https://fakestoreapi.com/products",
                ResponseDTO[].class);
        List <Product> products = new ArrayList<>();
        for (ResponseDTO responseDTO:responseList){

            products.add(getSinglefromFakeStore(responseDTO));
        }
        return products;
    }

    @Override
    public Product replaceProduct(Product product) throws ProductNotFound {
        return null;
    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }

    @Override
    public  Product replaceProduct(long id, RequestDTO requestDTO) {

        RequestCallback requestCallback =
                restTemplate.httpEntityCallback(requestDTO,ResponseDTO.class);
        HttpMessageConverterExtractor<ResponseDTO> responseExtractor =
                new HttpMessageConverterExtractor(
                        ResponseDTO.class,
                        restTemplate.getMessageConverters());

        // In single Call, I am able to put Object;
        // and getting the same object as response
        ResponseDTO responseDTO = restTemplate.execute(
                "https://fakestoreapi.com/products/"+id,
                 HttpMethod.PUT,
                requestCallback,
                responseExtractor
        );
        return getSinglefromFakeStore(responseDTO);
    }

    @Override
    public List<String> getAllCategories(){
        String[] responseCategories = restTemplate.getForObject("https://fakestoreapi.com/products/categories",
                String[].class);
        List<String> categories = new ArrayList<>();
        for (String responseCategory:responseCategories){
            categories.add(responseCategory);
        }
        return categories;
    }

    @Override
    public List<Product> getAllProductsByCategory(String category){

        List<Product> products = new ArrayList<>();
        ResponseDTO[] response = restTemplate.getForObject("https://fakestoreapi.com/products/category/"+category,
                ResponseDTO[].class);
        List<Product> productsList = new ArrayList<>();
        for (ResponseDTO responseDTO:response){
            productsList.add(getSinglefromFakeStore(responseDTO));
        }
        return productsList;
    }


    @Override
    public Product addProduct(RequestDTO requestDTO){

        RequestCallback requestCallback =
                restTemplate.httpEntityCallback(requestDTO,ResponseDTO.class);
        HttpMessageConverterExtractor<ResponseDTO> responseExtractor =
                new HttpMessageConverterExtractor(
                        ResponseDTO.class,
                        restTemplate.getMessageConverters());

        // In single Call, I am able to put Object;
        // and getting the same object as response
        ResponseDTO responseDTO = restTemplate.execute(
                "https://fakestoreapi.com/products",
                HttpMethod.POST,
                requestCallback,
                responseExtractor
        );
        Product product = getSinglefromFakeStore(responseDTO);

    return product;
    }

}
