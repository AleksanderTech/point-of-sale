package com.sale.point.inputDevices;

import java.util.Random;

public class BarcodeScannnerExample implements BarcodeScanner {

	public static Random random = new Random();

	@Override
	public String readScan() {
		String[] array = { "123451", null, "exit", null 
				,"12347", "12348", "12349", "12351", "12352", "12353", "12354", "12355"
				,"12356", "12357", "12358", "12359", "12360", "exit", "" };
		int randomInt = random.nextInt(array.length);
		return array[randomInt];
	}

}
