package com.sale.point.main;

import com.sale.point.inputDevices.BarcodeScannnerExample;
import com.sale.point.model.BasketExample;
import com.sale.point.model.CheckoutExample;
import com.sale.point.model.InMemoryProductDao;
import com.sale.point.outputDevices.HPPrinter;
import com.sale.point.outputDevices.LCDDisplay;
import com.sale.point.outputDevices.ReceiptCreator;

public class Application {

	public static void main(String[] args) {
		
		CheckoutExample groceryCheckout = new CheckoutExample(new LCDDisplay(), new ReceiptCreator(), new HPPrinter(),
				new BarcodeScannnerExample(), new InMemoryProductDao(), new BasketExample());
		for (int i = 0; i < 50; i++) {
			groceryCheckout.manageProductScan();
		}

	}

}
