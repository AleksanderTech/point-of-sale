package com.sale.point.model;

import java.util.List;

public interface Basket {

	void addProduct(Product product);
	
	void clear();
	
	List<Product> getProducts();

	double calculateCosts();

}
