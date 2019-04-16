package com.sale.point.devices;

import com.sale.point.model.Product;

public class FakeDisplay implements Display {

	@Override
	public void displayProduct(Product product) {
		System.out.println(String.format("Display: %s %.2f", product.getName(), product.getCost()));
	}

	@Override
	public void displaySum(double sum) {
		System.out.println(String.format("Display SUM: %.2f", sum));
	}

	@Override
	public void displayMessage(String message) {
		System.out.println(message);
	}

}
