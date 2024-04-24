package com.syqu.shop.repository;

import com.syqu.shop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//write ProductRepository interface which extends JpaRepository
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryId(long id);
    Product findByName(String name);
    List<Product> findAllByOrderByIdAsc();
    List<Product> findAllByCategoryId(long categoryId);
}
