package com.adena.productservice.repositories;

import com.adena.productservice.models.Category;
import com.adena.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRespository extends JpaRepository<Product, Long> {
    Product findByName(String name);
    boolean findByCategory(Category category);
    @Query("select p from Product p where p.id=1 ")
    Product doSomething();
    @Query("SELECT p from Product p where p.isDeleted=?1 order by p.id")
    List<Product> findAllByisDeleted(boolean isDeleted);
    @Query("SELECT p FROM Product p JOIN p.category c WHERE c.name = :categoryName and p.isDeleted=false order by p.id")
    List<Product> findAllByisDeletedAndcAndCategoryId(String categoryName);

    @Modifying
    @Query("update Product p set p.isDeleted= ?1 where p.id =?2")
    Product deleteProduct(boolean Bool, long id);

    Optional<Product> findByisDeletedAndId( boolean b,long id);
}
