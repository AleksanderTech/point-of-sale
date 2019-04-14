package com.sale.point.model;

import java.util.Optional;

public interface ProductDao {
	Optional<Product> findProductsByBarcode(String barcode);
}
