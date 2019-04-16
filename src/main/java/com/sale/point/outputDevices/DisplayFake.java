package com.sale.point.outputDevices;

import com.sale.point.model.Product;

public class DisplayFake implements Display {

	@Override
	public void displayProduct(Product product) {
		System.out.println("LCDDisplay: " + product.getName() + " " + product.getCost());
	}

	@Override
	public void displaySum(double sum) {
		System.out.println("LCDDisplay SUM: " + sum);
	}

	@Override
	public void dispayMessage(String message) {
		System.out.println(message);
	}

}
