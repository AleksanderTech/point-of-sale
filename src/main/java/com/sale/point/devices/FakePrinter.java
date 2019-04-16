package com.sale.point.devices;

public class FakePrinter implements Printer {

	@Override
	public void print(String text) {
		System.out.println(text);
	}
}
