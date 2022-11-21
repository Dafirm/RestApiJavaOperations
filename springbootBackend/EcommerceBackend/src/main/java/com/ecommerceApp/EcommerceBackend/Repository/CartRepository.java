package com.ecommerceApp.EcommerceBackend.Repository;

import com.ecommerceApp.EcommerceBackend.Models.Cart;
import com.ecommerceApp.EcommerceBackend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
