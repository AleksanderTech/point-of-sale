package com.sale.point.model;

import java.util.List;

public interface ReceiptFactory {
	String create(List<Product> products);
}
