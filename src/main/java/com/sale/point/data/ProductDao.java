package com.sale.point.data;

import java.util.Optional;

import com.sale.point.model.Product;

public interface ProductDao {
	Optional<Product> findProductsByBarcode(String barcode);
}
