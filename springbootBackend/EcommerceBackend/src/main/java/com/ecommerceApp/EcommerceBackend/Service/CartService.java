package com.ecommerceApp.EcommerceBackend.Service;

import com.ecommerceApp.EcommerceBackend.DTO.cart.AddToCartDto;
import com.ecommerceApp.EcommerceBackend.DTO.cart.CartDto;
import com.ecommerceApp.EcommerceBackend.DTO.cart.CartItemDto;
import com.ecommerceApp.EcommerceBackend.DTO.exception.CustomException;
import com.ecommerceApp.EcommerceBackend.Models.Cart;
import com.ecommerceApp.EcommerceBackend.Models.Product;
import com.ecommerceApp.EcommerceBackend.Models.User;
import com.ecommerceApp.EcommerceBackend.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    ProductService productService;

    @Autowired
    CartRepository cartRepository;
    public void addToCart(AddToCartDto addToCartDto, User user) {
        //validate the product id

        Product product = productService.findById(addToCartDto.getProductId());
        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addToCartDto.getQuantity());
        cart.setCreateDate(new Date());

        cartRepository.save(cart); //save products to cart

    }

    public CartDto listCartItems(User user) {
         List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);

        List<CartItemDto> cartItems = new ArrayList<>();
        double totalCost = 0;
        for (Cart cart: cartList){
            CartItemDto cartItemDto = new CartItemDto(cart);
            totalCost += cartItemDto.getQuantity() * cart.getProduct().getPrice();
            cartItems.add(cartItemDto);

        }
        CartDto cartDto = new CartDto();
        cartDto.setTotalCost(totalCost);
        cartDto.setCartItems(cartItems);
        return cartDto;

    }

    public void deleteCartItem(Integer cartItemId, User user) {
        //check that item id belong to user

        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);

        if (optionalCart.isEmpty()) {
            throw new CustomException("cart item id is invalid: " + cartItemId);
        }
        Cart cart = optionalCart.get();

        if ( cart.getUser() != user ){
            throw new CustomException("cart item is not for user: " + cartItemId);
        }
        cartRepository.delete(cart);
    }
}
