package com.sale.point.outputDevices;

public class PrinterFake implements Printer {

	@Override
	public void print(String text) {
		System.out.println(text);
	}
}
