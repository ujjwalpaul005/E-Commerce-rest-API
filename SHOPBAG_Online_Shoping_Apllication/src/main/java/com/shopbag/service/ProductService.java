package com.shopbag.service;

import java.util.List;

import com.shopbag.exception.ProductException;
import com.shopbag.model.Product;

public interface ProductService {

	public List<Product> viewAllProduct() throws ProductException;
	
	public Product addProduct(Product pdt) throws ProductException;
	
	public Product updateProduct(Product product) throws ProductException;
	
	public Product viewProduct(Integer Id) throws ProductException;
	
	public List<Product> viewProductByCategory(String cname) throws ProductException;
	
	public Product removeProduct(Integer Id) throws ProductException;
}
