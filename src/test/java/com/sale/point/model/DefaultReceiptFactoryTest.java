package com.sale.point.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class DefaultReceiptFactoryTest {

	@Test
	void returnsReceiptOfAllScannedProducts() {
		List<Product> products = createListOfProducts("coffee", "chocolate");
		String expected = create(products);
		ReceiptFactory receiptFactory = new DefaultReceiptFactory();
		String actual = receiptFactory.create(products);
		assertEquals(expected, actual);
	}

	private String create(List<Product> products) {
		StringBuilder builder = new StringBuilder();
		double sum = 0;
		for (Product p : products) {
			builder.append(p.getName()).append(" ").append(p.getCost()).append(" ON RECEIPT").append("-----------")
					.append("\n");
			sum += p.getCost();
		}
		builder.append("TOTAL SUM: ").append(String.format("%.2f", sum)).append(" ON RECEIPT");
		return builder.toString();
	}

	private List<Product> createListOfProducts(String... names) {
		List<Product> products = new ArrayList<>();
		for (int i = 0; i < names.length; i++) {
			products.add(new Product(names[i], 0, ""));
		}
		return products;
	}
}
