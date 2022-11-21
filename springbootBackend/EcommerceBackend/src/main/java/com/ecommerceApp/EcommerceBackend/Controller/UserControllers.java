package com.ecommerceApp.EcommerceBackend.Controller;


import com.ecommerceApp.EcommerceBackend.DTO.user.ResponseDto;
import com.ecommerceApp.EcommerceBackend.DTO.user.SignInDto;
import com.ecommerceApp.EcommerceBackend.DTO.user.SignInResponseDto;
import com.ecommerceApp.EcommerceBackend.DTO.user.SignupDto;
import com.ecommerceApp.EcommerceBackend.Models.User;
import com.ecommerceApp.EcommerceBackend.Repository.UserRepository;
import com.ecommerceApp.EcommerceBackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserControllers {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    UserService userService;


    @GetMapping(value ="/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping(value ="/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto) {
     return userService.signUp(signupDto);
    }

    @PostMapping(value ="/signin")
    public SignInResponseDto signin(@RequestBody SignInDto signInDto) {
        return userService.signIn(signInDto);
    }




}
