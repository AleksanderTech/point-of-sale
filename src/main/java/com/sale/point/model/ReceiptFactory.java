package com.sale.point.model;

import java.util.List;

public interface ReceiptFactory {

	public String create(List<Product> products);

}
