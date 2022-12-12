package com.shopbag.service;

import com.shopbag.exception.CartException;
import com.shopbag.model.Cart;
import com.shopbag.model.Product;

public interface CartService {
	
	public Cart addProductToCart(Cart cart, Product product) throws CartException;
	public Cart removeProductFromCart(Cart cart) throws CartException;
	public Cart plusProductQuantity(Cart cart, Product product) throws CartException;
	public Cart removeAllProducts(Cart cart) throws CartException;
	public Cart viewAllProducts(Cart cart) throws CartException;
	
}
