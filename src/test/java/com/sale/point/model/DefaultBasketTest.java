package com.sale.point.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefaultBasketTest {

	private DefaultBasket basket;

	@BeforeEach
	void setUp() {
		basket = new DefaultBasket();
	}

	@Test
	void returnsSumOfCostsOfAllProducts() {
		List<Product> products = new ArrayList<>(createsListOfProducts(45.0, 21.1));
		basket.addProduct(products.get(0));
		basket.addProduct(products.get(1));
		double sumPrice = basket.calculateCosts();
		assertEquals(66.1, sumPrice);
	}

	@Test
	void addsProductToTheList() {
		Product product = new Product("coffee", 33.0, "12336");
		basket.addProduct(product);
		assertEquals(product, basket.getProducts().get(0));
	}

	@Test
	void clearsListOfProducts() {
		List<Product> products = new ArrayList<>();
		basket.addProduct(new Product("", 0, ""));
		basket.clear();
		assertEquals(products, basket.getProducts());
	}

	private List<Product> createsListOfProducts(double... prices) {
		List<Product> products = new ArrayList<>();
		for (int i = 0; i < prices.length; i++) {
			products.add(new Product("", prices[i], ""));
		}
		return products;
	}
	
}
