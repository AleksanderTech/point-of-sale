package com.sale.point.model;

public class Product {

	private String name;
	private Double cost;
	private String barcode;

	public Product(String name, Double cost, String barCode) {
		this.name = name;
		this.cost = cost;
		this.barcode = barCode;
	}

	@Override
	public String toString() {
		return "name=" + name + ", cost=" + cost + ", barCode=" + barcode + "";
	}

	public String getName() {
		return name;
	}
	
	public Double getCost() {
		return cost;
	}

	public String getBarcode() {
		return barcode;
	}
}
