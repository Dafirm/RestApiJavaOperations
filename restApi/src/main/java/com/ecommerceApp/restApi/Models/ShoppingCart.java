package com.ecommerceApp.restApi.Models;

import java.util.ArrayList;

public class ShoppingCart {

    private final ArrayList<Product> cartItems = new ArrayList<>();
    private double totalPrice = 0.00;
    private String paymentMethod = "";
    private boolean isPaid = false;

    public void addToCart(Product product) {
        if (product.getQuantity() > 0) {
            this.cartItems.add(product);
            this.totalPrice += product.getPrice();

            System.out.println("Successfully saved...");
        }
    }

    public void removeFromCart(Product product) {
        this.cartItems.removeIf(item -> item.equals(product));
        this.totalPrice -= product.getPrice();

        System.out.println("Successfully removed.");
    }

    public void emptyCart() {
        this.cartItems.clear();
        this.totalPrice = 0.00;
        this.paymentMethod = "";
        this.isPaid = false;

        System.out.println("Cart is empty.");
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;

        System.out.println("Payment method set.");
    }

    public void pay(double amount) {
        if(amount == this.totalPrice) {
            this.isPaid = true;

            System.out.println("Payment successful.");
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartItems=" + cartItems +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", totalPrice=" + totalPrice +
                ", isPaid=" + isPaid +
                '}';
    }

    public ArrayList<Product> getCartItems() {
        return cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public boolean isPaid() {
        return isPaid;
    }

}
