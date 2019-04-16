package com.sale.point.database;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.sale.point.model.Product;

public class ProductDaoInMemory implements ProductDao{
	
	private static final Map<String, Product> PRODUCTS = new HashMap<>();
	
	static {
		PRODUCTS.put("12345", new Product("kawa", 39.0, "12345"));
		PRODUCTS.put("12346", new Product("czekolada", 5.0, "12346"));
		PRODUCTS.put("12347", new Product("chleb", 4.0, "12347"));
		PRODUCTS.put("12348", new Product("ziemniaki", 2.0, "12348"));
		PRODUCTS.put("12349", new Product("kie³basa", 29.0, "12349"));
		PRODUCTS.put("12350", new Product("woda", 2.0, "12350"));
		PRODUCTS.put("12351", new Product("warzywa", 5.0, "12351"));
		PRODUCTS.put("12352", new Product("kasza", 3.0, "12352"));
		PRODUCTS.put("12353", new Product("ryz", 3.0, "12353"));
		PRODUCTS.put("12354", new Product("mleko", 2.5, "12354"));
		PRODUCTS.put("12355", new Product("jajka", 6.0, "12355"));
		PRODUCTS.put("12356", new Product("jogurt", 2.3, "12356"));
		PRODUCTS.put("12357", new Product("ser", 9.0, "12357"));
		PRODUCTS.put("12358", new Product("sledzie", 7.0, "12358"));
	}

	@Override
	public Optional<Product> findProductsByBarcode(String barcode) {
		return Optional.ofNullable(PRODUCTS.get(barcode));
	}
}
