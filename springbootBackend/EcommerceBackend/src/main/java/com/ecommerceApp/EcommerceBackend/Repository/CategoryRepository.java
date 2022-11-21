package com.ecommerceApp.EcommerceBackend.Repository;

import com.ecommerceApp.EcommerceBackend.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByCategoryName(String categoryName);

}