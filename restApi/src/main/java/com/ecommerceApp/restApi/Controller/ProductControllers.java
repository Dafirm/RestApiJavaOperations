package com.ecommerceApp.restApi.Controller;

import com.ecommerceApp.restApi.Models.Product;
import com.ecommerceApp.restApi.Repository.ProductRepository;
import com.ecommerceApp.restApi.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductControllers {

    @Autowired
    private ProductRepository productRepository;


    @GetMapping(value ="/products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @PostMapping(value ="/saveProduct")
    public String saveProduct(@RequestBody Product product) {
        productRepository.save(product);
        return "successfully saved..";
    }

    @PutMapping(value = "/updateProduct/{id}")
    public String saveProduct(@PathVariable long id, @RequestBody Product product) {
        Product updatedProduct =  productRepository.findById(id).get();
        updatedProduct.setName(product.getName());
        updatedProduct.setQuantity(product.getQuantity());
        updatedProduct.setPrice(product.getPrice());
        productRepository.save(updatedProduct);
        return "successfully updated..";
    }

    @DeleteMapping(value ="/delete/{id}")
    public String deleteProduct(@PathVariable long id, @RequestBody Product product) {
        Product deleteProduct = productRepository.findById(id).get();
        productRepository.delete(deleteProduct);
        return "successfully deleted.." + id;
    }


}