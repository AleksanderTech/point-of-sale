package com.sale.point;

import com.sale.point.database.ProductDaoInMemory;
import com.sale.point.inputDevices.BarcodeScanner;
import com.sale.point.inputDevices.BarcodeScannnerFake;
import com.sale.point.model.BasketStub;
import com.sale.point.model.CheckoutImpl;
import com.sale.point.model.ReceiptFactoryFake;
import com.sale.point.outputDevices.DisplayFake;
import com.sale.point.outputDevices.PrinterFake;

public class Application {

	public static void main(String[] args) {

		CheckoutImpl groceryCheckout = new CheckoutImpl(new DisplayFake(), new ReceiptFactoryFake(), new PrinterFake(),
				new ProductDaoInMemory(), new BasketStub());
		BarcodeScanner barcodeScanner=new BarcodeScannnerFake();
		for (int i = 0; i < 50; i++) {
			groceryCheckout.manageProductScan(barcodeScanner.readScan());
		}

	}

}
