package com.sale.point.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import com.sale.point.model.Product;

class InMemoryProductDaoTest {

	@Test
	void returnsOptionalIfProductExist() {
		String barcode = "12345";
		InMemoryProductDao inMemoryProductDao = new InMemoryProductDao();
		Optional<Product> expected = findProductsByBarcode(barcode);
		Optional<Product> actual = inMemoryProductDao.findProductsByBarcode(barcode);

		assertEquals(expected.get().getName(), actual.get().getName());
		assertEquals(expected.get().getBarcode(), actual.get().getBarcode());
		assertEquals(expected.get().getCost(), actual.get().getCost());
	}

	@Test
	void returnsEmptyOptionalIfProductDoesntExist() {
		String barcode = "doesn't exist";
		InMemoryProductDao inMemoryProductDao = new InMemoryProductDao();
		Optional<Product> expected = findProductsByBarcode(barcode);
		Optional<Product> actual = inMemoryProductDao.findProductsByBarcode(barcode);

		assertEquals(expected.isEmpty(), actual.isEmpty());
	}

	private static final Map<String, Product> PRODUCTS = new HashMap<>();

	static {
		PRODUCTS.put("12345", new Product("coffee", 39.0, "12345"));
		PRODUCTS.put("12346", new Product("chocolate", 5.0, "12346"));
		PRODUCTS.put("12347", new Product("bread", 4.0, "12347"));
		PRODUCTS.put("12348", new Product("potatoes", 2.0, "12348"));
		PRODUCTS.put("12349", new Product("sausage", 29.0, "12349"));
		PRODUCTS.put("12350", new Product("water", 2.0, "12350"));
		PRODUCTS.put("12351", new Product("vegetables", 5.0, "12351"));
		PRODUCTS.put("12352", new Product("groats", 3.0, "12352"));
		PRODUCTS.put("12353", new Product("rice", 3.0, "12353"));
		PRODUCTS.put("12354", new Product("milk", 2.5, "12354"));
		PRODUCTS.put("12355", new Product("eggs", 6.0, "12355"));
		PRODUCTS.put("12356", new Product("yoghurt", 2.3, "12356"));
		PRODUCTS.put("12357", new Product("cheese", 9.0, "12357"));
		PRODUCTS.put("12358", new Product("sledzie", 7.0, "12358"));
	}

	public Optional<Product> findProductsByBarcode(String barcode) {
		return Optional.ofNullable(PRODUCTS.get(barcode));
	}
}
