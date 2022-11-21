package com.ecommerceApp.EcommerceBackend.Repository;

import com.ecommerceApp.EcommerceBackend.Models.User;
import com.ecommerceApp.EcommerceBackend.Models.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {
    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}
