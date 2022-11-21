package com.ecommerceApp.EcommerceBackend.Repository;

import com.ecommerceApp.EcommerceBackend.Models.AuthenticationToken;
import com.ecommerceApp.EcommerceBackend.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {

    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String  token);
}
