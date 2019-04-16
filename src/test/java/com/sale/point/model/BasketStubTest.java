package com.sale.point.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BasketStubTest {

	private Basket basket;

	@BeforeEach
	void setUp() {
		basket = new BasketFake();
	}

	@Test
	public void returnsSumOfCostsOfAllProducts() throws Exception {
		// given
		List<Product> products = new ArrayList<>(createListOfProducts(45.0, 21.1));
		// when
		basket.addProduct(products.get(0));
		basket.addProduct(products.get(1));
		double sumPrice = basket.calculateCosts();
		// then
		assertEquals(66.1, sumPrice);
	}

	private List<Product> createListOfProducts(double... prices) {
		List<Product> products = new ArrayList<>();
		for (int i = 0; i < prices.length; i++) {
			products.add(new Product("", prices[i], ""));
		}
		return products;
	}

}
