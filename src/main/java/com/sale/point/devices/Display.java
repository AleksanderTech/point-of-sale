package com.sale.point.devices;

import com.sale.point.model.Product;

public interface Display {

	public void displayProduct(Product product);

	public void displayMessage(String message);

	public void displaySum(double sum);

}
