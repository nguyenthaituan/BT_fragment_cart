package vn.edu.ntu.bt_fragment_cart.controller;

import java.util.List;

import vn.edu.ntu.bt_fragment_cart.model.Product;

public interface ICartController {
    public List<Product> getAProducts();
    public boolean addToCart(Product p);
    public List<Product> getShoppingCart();
    public void clearShoppingCart();
}
