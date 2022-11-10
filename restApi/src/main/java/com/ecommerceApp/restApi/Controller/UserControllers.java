package com.ecommerceApp.restApi.Controller;

import com.ecommerceApp.restApi.Models.Product;
import com.ecommerceApp.restApi.Models.User;
import com.ecommerceApp.restApi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserControllers {

    @Autowired
    private UserRepository userRepository;


    @GetMapping(value ="/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping(value ="/saveUser")
    public String saveUser(@RequestBody User user) {
        userRepository.save(user);
        return "successfully saved..";
    }

    @PutMapping(value = "/updateUser/{id}")
    public String saveUser(@PathVariable long id, @RequestBody User user) {
       User updatedUser =  userRepository.findById(id).get();
       updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        userRepository.save(updatedUser);
        return "successfully updated..";
    }

    @DeleteMapping(value ="/deleteUser/{id}")
    public String deleteUser(@PathVariable long id, @RequestBody User user) {
        User deleteUser = userRepository.findById(id).get();
        userRepository.delete(deleteUser);
        return "successfully deleted.." + id;
    }


}
