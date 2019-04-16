package com.sale.point.model;

public class Product {

	private final String name;
	private final double cost;
	private final String barcode;

	public Product(String name, double cost, String barcode) {
		this.name = name;
		this.cost = cost;
		this.barcode = barcode;
	}

	@Override
	public String toString() {
		return "name=" + name + ", cost=" + cost + ", barcode=" + barcode + "";
	}

	public String getName() {
		return name;
	}
	
	public double getCost() {
		return cost;
	}

	public String getBarcode() {
		return barcode;
	}
}
