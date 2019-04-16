package com.sale.point;

import com.sale.point.database.ProductDaoInMemory;
import com.sale.point.inputDevices.BarcodeScanner;
import com.sale.point.inputDevices.BarcodeScannnerImpl;
import com.sale.point.model.BasketFake;
import com.sale.point.model.CheckoutImpl;
import com.sale.point.model.ReceiptFactoryImpl;
import com.sale.point.outputDevices.DisplayImpl;
import com.sale.point.outputDevices.PrinterImpl;

public class Application {

	public static void main(String[] args) {

		CheckoutImpl groceryCheckout = new CheckoutImpl(new DisplayImpl(), new ReceiptFactoryImpl(), new PrinterImpl(),
				new ProductDaoInMemory(), new BasketFake());
		BarcodeScanner barcodeScanner=new BarcodeScannnerImpl();
		for (int i = 0; i < 50; i++) {
			groceryCheckout.manageProductScan(barcodeScanner.readScan());
		}

	}

}
