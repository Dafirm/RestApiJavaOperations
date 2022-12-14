package com.ecommerceApp.EcommerceBackend.Controller;

import com.ecommerceApp.EcommerceBackend.DTO.ProductDto;
import com.ecommerceApp.EcommerceBackend.Models.Category;
import com.ecommerceApp.EcommerceBackend.Repository.CategoryRepository;
import com.ecommerceApp.EcommerceBackend.Service.ProductService;
import com.ecommerceApp.EcommerceBackend.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    CategoryRepository categoryRepository;


    @PostMapping(value ="/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto){
      Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
      if (!optionalCategory.isPresent()){
          return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.BAD_REQUEST);
      }
      productService.createProduct(productDto, optionalCategory.get());
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been added"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping(value ="/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDto productDto) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (!optionalCategory.isPresent()){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.BAD_REQUEST);
        }
        productService.updateProduct(productDto, productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been updated"), HttpStatus.OK);
    }

}
