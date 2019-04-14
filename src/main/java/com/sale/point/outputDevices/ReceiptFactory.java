package com.sale.point.outputDevices;

import java.util.List;

import com.sale.point.model.Product;

public interface ReceiptFactory {

	public String create(List<Product> products);

}
