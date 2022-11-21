package com.ecommerceApp.EcommerceBackend.Controller;

import com.ecommerceApp.EcommerceBackend.Models.Category;
import com.ecommerceApp.EcommerceBackend.Service.CategoryService;
import com.ecommerceApp.EcommerceBackend.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping(value ="/create")
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true, "A new category has been created"), HttpStatus.CREATED);
    }

    @GetMapping(value ="/list")
    public List<Category> listCategory(){
        return categoryService.listCategory();
    }


    @PostMapping(value ="/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category){
        if(!categoryService.findById(categoryId)){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exit" ), HttpStatus.NOT_FOUND);
        }
        categoryService.editCategory(categoryId, category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "category has been updated" ), HttpStatus.OK);
    }
}
