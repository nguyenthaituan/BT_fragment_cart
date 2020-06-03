package vn.edu.ntu.bt_fragment_cart.controller;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

import vn.edu.ntu.bt_fragment_cart.model.Product;

public class CartController extends Application implements ICartController {

    List<Product> listProducts = new ArrayList<>();

    //chua san pham dua vao gio hang
    List<Product> shoppingCart = new ArrayList<>();

    public CartController() {
       listProducts.add(new Product("Khoai lang",25000,"khoai to"));
        listProducts.add(new Product("Khoai sọ",25000,"khoai bự"));
        listProducts.add(new Product("Khoai môn",25000,"khoai dài"));
        listProducts.add(new Product("Khoai tím",25000,"khoai dai"));
    }

    @Override
    public List<Product> getAProducts()
    {
        return listProducts;
    }

    @Override
    public boolean addToCart(Product p)
    {
        if(shoppingCart.contains(p))
        return false;
        shoppingCart.add(p);
        return true;
    }

    @Override
    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

    @Override
    public void clearShoppingCart() {
        shoppingCart.clear();
    }

}
