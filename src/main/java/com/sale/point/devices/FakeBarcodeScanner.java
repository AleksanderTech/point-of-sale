package com.sale.point.devices;

import java.util.Random;

public class FakeBarcodeScanner implements BarcodeScanner {

	private static final String[] BAR_CODES = { "123451", null, "exit", null, "12347", "12348", "12349", "12351", "12352", "12353", "12354",
			"12355", "12356", "12357", "12358", "12359", "12360", "exit", "" };
	private final Random random;
	
	public FakeBarcodeScanner(Random random) {	
		this.random = random;
	}
	
	public FakeBarcodeScanner() {
		this(new Random());
	}

	@Override
	public String readScan() {
		return BAR_CODES[random.nextInt(BAR_CODES.length)];
	}

}
