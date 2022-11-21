package com.ecommerceApp.EcommerceBackend.Controller;

import com.ecommerceApp.EcommerceBackend.DTO.cart.AddToCartDto;
import com.ecommerceApp.EcommerceBackend.DTO.cart.CartDto;
import com.ecommerceApp.EcommerceBackend.Models.User;
import com.ecommerceApp.EcommerceBackend.Service.AuthenticationService;
import com.ecommerceApp.EcommerceBackend.Service.CartService;

import com.ecommerceApp.EcommerceBackend.Service.ProductService;
import com.ecommerceApp.EcommerceBackend.common.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value ="/addtocart")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam("token") String token){

        //authenticate the token
        authenticationService.authenticate(token);

        // find the user
        User user = authenticationService.getUser(token);



        cartService.addToCart(addToCartDto, user);

        return new ResponseEntity<>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token){

        //authenticate the token
        authenticationService.authenticate(token);

        // find the user
        User user = authenticationService.getUser(token);

        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);

    }
    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Integer itemId,
                                                      @RequestParam("token") String token){
        //authenticate the token
        authenticationService.authenticate(token);

        // find the user
        User user = authenticationService.getUser(token);

        cartService.deleteCartItem(itemId, user);
        return new ResponseEntity<>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }

}
