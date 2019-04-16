package com.sale.point;

import com.sale.point.database.ProductDaoFake;
import com.sale.point.inputDevices.BarcodeScanner;
import com.sale.point.inputDevices.BarcodeScannnerFake;
import com.sale.point.model.BasketStub;
import com.sale.point.model.CheckoutImpl;
import com.sale.point.model.ReceiptFactoryStub;
import com.sale.point.outputDevices.DisplayFake;
import com.sale.point.outputDevices.PrinterFake;

public class Application {

	public static void main(String[] args) {

		CheckoutImpl groceryCheckout = new CheckoutImpl(new DisplayFake(), new ReceiptFactoryStub(), new PrinterFake(),
				new ProductDaoFake(), new BasketStub());
		BarcodeScanner barcodeScanner=new BarcodeScannnerFake();
		for (int i = 0; i < 50; i++) {
			groceryCheckout.manageProductScan(barcodeScanner.readScan());
		}

	}

}
