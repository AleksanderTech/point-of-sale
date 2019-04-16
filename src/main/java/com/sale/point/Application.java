package com.sale.point;

import com.sale.point.data.InMemoryProductDao;
import com.sale.point.devices.BarcodeScanner;
import com.sale.point.devices.FakeBarcodeScanner;
import com.sale.point.devices.FakeDisplay;
import com.sale.point.devices.FakePrinter;
import com.sale.point.model.DefaultBasket;
import com.sale.point.model.DefaultCheckout;
import com.sale.point.model.DefaultReceiptFactory;

public class Application {

	public static void main(String[] args) {

		DefaultCheckout checkoutImpl = new DefaultCheckout(new FakeDisplay(), new DefaultReceiptFactory(), new FakePrinter(),
				new InMemoryProductDao(), new DefaultBasket());
		BarcodeScanner barcodeScanner=new FakeBarcodeScanner();
		for (int i = 0; i < 50; i++) {
			checkoutImpl.manageProductScan(barcodeScanner.readScan());
		}

	}

}
