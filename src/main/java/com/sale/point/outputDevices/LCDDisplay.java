package com.sale.point.outputDevices;

import com.sale.point.model.Product;

public class LCDDisplay implements Display {

	@Override
	public void displayProduct(Product product) {
		System.out.println("LCDDisplay: " + product.getName() + " " + product.getCost());
	}

	@Override
	public void displaySum(double sum) {
		if (sum != 0) {
			System.out.println("LCDDisplay SUM: " + sum);
		}
	}

}
