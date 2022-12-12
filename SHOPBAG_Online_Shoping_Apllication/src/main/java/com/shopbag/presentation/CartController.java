package com.shopbag.presentation;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopbag.exception.CartException;
import com.shopbag.model.Cart;
import com.shopbag.model.Product;
import com.shopbag.service.CartService;
import com.shopbag.service.CartServiceImpl;

@RestController
public class CartController {
	
	@Autowired
	private CartServiceImpl cartService;
	
	@PostMapping("/addProductToCart")
	public ResponseEntity<Cart> addProductToCart(@RequestBody Cart cart) throws CartException {
		
		Cart nCart = cartService.addProductToCart(cart, cart.getProduct());
		
		return new ResponseEntity<Cart>(nCart, HttpStatus.OK);
		
	}
	
	@DeleteMapping("/removeProductFromCart")
	public ResponseEntity<Cart> removeProductFromCart(@Valid @RequestBody Cart cart) throws CartException {
		
		Cart nCart = cartService.removeProductFromCart(cart);
		
		return new ResponseEntity<Cart>(nCart, HttpStatus.OK);
		
	}
	
	@PutMapping("/updateProductQuantity")
	public ResponseEntity<Cart> updateProductQuantity(@Valid @RequestBody Cart cart) throws CartException{
		
		Cart nCart = cartService.plusProductQuantity(cart, cart.getProduct());
		
		return new ResponseEntity<Cart>(nCart, HttpStatus.ACCEPTED);
		
	}
	
	@DeleteMapping("/removeAllProducts")
	public ResponseEntity<Cart> removeAllProducts(@Valid @RequestBody Cart cart) throws CartException {
		
		Cart nCart = cartService.removeAllProducts(cart);
		
		return new ResponseEntity<Cart>(nCart, HttpStatus.OK);
		
		
	}
	
	@GetMapping("/viewAllProducts")
	public ResponseEntity<Cart> viewAllProducts(@Valid @RequestBody Cart cart) throws CartException {
		
		Cart nCart = cartService.viewAllProducts(cart);
		
		return new ResponseEntity<Cart>(nCart, HttpStatus.OK);
		
	}	
	
}
