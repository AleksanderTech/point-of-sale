package com.sale.point.outputDevices;

public class PrinterImpl implements Printer {

	@Override
	public void print(String text) {
		System.out.println(text);
	}
}
