package com.ecommerceApp.EcommerceBackend.Controller;

import com.ecommerceApp.EcommerceBackend.DTO.ProductDto;
import com.ecommerceApp.EcommerceBackend.Models.Product;
import com.ecommerceApp.EcommerceBackend.Models.User;
import com.ecommerceApp.EcommerceBackend.Models.WishList;
import com.ecommerceApp.EcommerceBackend.Service.AuthenticationService;
import com.ecommerceApp.EcommerceBackend.Service.WishListService;
import com.ecommerceApp.EcommerceBackend.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListController {

    @Autowired
    private WishListService wishListService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable("token")String token){
        //authenticate the token
        authenticationService.authenticate(token);

        // find the user
        User user = authenticationService.getUser(token);
        List<ProductDto> productDtos =  wishListService.getWishListForUser(user);

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    // save product into wishlist
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product,
                                                     @RequestParam("token") String token) {

        //authenticate the token
        authenticationService.authenticate(token);

        // find the user
        User user = authenticationService.getUser(token);

        // save wishlist items..
        WishList wishList = new WishList(user, product);
        wishListService.createWishlist(wishList);

        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to wishlist"), HttpStatus.CREATED);

    }
}
