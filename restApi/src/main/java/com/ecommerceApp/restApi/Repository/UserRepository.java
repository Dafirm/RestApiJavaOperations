package com.ecommerceApp.restApi.Repository;

import com.ecommerceApp.restApi.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
