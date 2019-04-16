package com.sale.point.model;

import java.util.ArrayList;
import java.util.List;

public class BasketFake implements Basket {

	private final List<Product> products = new ArrayList<>();

	@Override
	public void addProduct(Product product) {
		products.add(product);
	}

	@Override
	public double calculateCosts() {
		double costs = 0;
		for (Product p : products) {
			costs += p.getCost();
		}
		return costs;
	}

	@Override
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public void clear() {
		products.clear();
	}
}
