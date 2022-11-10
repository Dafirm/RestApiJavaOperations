package com.ecommerceApp.restApi.Repository;

import com.ecommerceApp.restApi.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
