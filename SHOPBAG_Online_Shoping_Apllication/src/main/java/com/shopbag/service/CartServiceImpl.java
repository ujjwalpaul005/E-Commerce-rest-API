package com.shopbag.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopbag.exception.CartException;
import com.shopbag.model.Cart;
import com.shopbag.model.Customer;
import com.shopbag.model.Product;
import com.shopbag.repository.CartRepo;
import com.shopbag.repository.ProductRepo;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartRepo cartRepo;

	
	
	
	@Override
	public Cart addProductToCart(Cart cart, Product product) throws CartException {
		
		Optional<Cart> opt = cartRepo.findById(cart.getCartId()); // findbyId return optional
		
		
		if(opt.isPresent()) {
			cart.getProducts().add(product);
			return cart;
		} else {
			cart.getProducts().add(product);
			return cartRepo.save(cart);
		}	 
		 
	}

	@Override
	public Cart removeProductFromCart(Cart cart) throws CartException {
		
		Optional<Cart> opt = cartRepo.findById(cart.getCartId()); // findbyId return optional
		
		if(opt.isPresent()) {
			
			cartRepo.delete(cart);
			
			return cart;
			
		} else {
			throw new CartException("Cart details is invalid.");
		}
	}

	@Override
	public Cart plusProductQuantity(Cart cart, Product product) throws CartException {
		
		Optional<Cart> opt = cartRepo.findById(cart.getCartId()); // findbyId return optional
		
		if(opt.isPresent()) {
			
			Cart nCart = opt.get();
			
			nCart.getProducts().add(product);
			return nCart;
			
		} else {
			throw new CartException("Cart details is invalid.");
		}
	}


	@Override
	public Cart removeAllProducts(Cart cart) throws CartException {
		Optional<Cart> opt = cartRepo.findById(cart.getCartId()); // findbyId return optional
		
		if(opt.isPresent()) {
			
			Cart nCart = opt.get();
			nCart.getProducts().clear();
			return nCart;
			
		} else {
			throw new CartException("Cart details is invalid.");
		}
	}

	@Override
	public Cart viewAllProducts(Cart cart) throws CartException {
		
		Optional<Cart> opt = cartRepo.findById(cart.getCartId()); // findbyId return optional
		
		if(opt.isPresent()) {
			
			Cart nCart = opt.get();
			return nCart;
			
		} else {
			throw new CartException("Cart details is invalid.");
		}
	}

}