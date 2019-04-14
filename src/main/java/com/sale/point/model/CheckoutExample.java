package com.sale.point.model;

import java.util.Optional;

import com.sale.point.inputDevices.BarcodeScanner;
import com.sale.point.outputDevices.Display;
import com.sale.point.outputDevices.Printer;
import com.sale.point.outputDevices.ReceiptFactory;

public class CheckoutExample implements Checkout {

	private static final String EXIT_CODE = "exit";
	private static final String PRODUCT_NOT_FOUND = "Product not found";
	private static final String INVALID_BAR_CODE = "Invalid bar-code";
	private final Display display;
	private final ReceiptFactory receipt;
	private final Printer printer;
	private final BarcodeScanner barcodeScanner;
	private final ProductDao productDao;
	private final Basket basket;

	public CheckoutExample(Display display, ReceiptFactory receipt, Printer printer, BarcodeScanner barcodeScanner,
			ProductDao productDao, Basket basket) {
		this.display = display;
		this.printer = printer;
		this.receipt = receipt;
		this.barcodeScanner = barcodeScanner;
		this.productDao = productDao;
		this.basket = basket;
	}

	@Override
	public void manageProductScan() {
		String barcode = barcodeScanner.readScan();
		if (isInvalidBarcode(barcode)) {
			manageInvalidBarcode();
		} else {
			manageValidBarcode(barcode);
		}
	}

	private void checkCodeInDatabase(String barcode) {
		Optional<Product> optional = productDao.findProductsByBarcode(barcode);
		if (optional.isPresent()) {
			display.displayProduct(optional.get());
			basket.addProduct(optional.get());
		} else {
			System.out.println(PRODUCT_NOT_FOUND);
		}
	}

	private boolean isExitBarcode(String barcode) {
		return barcode.equals(EXIT_CODE);
	}

	private void manageValidBarcode(String barcode) {
		if (isExitBarcode(barcode)) {
			String receipt = this.receipt.create(basket.getProducts());
			display.displaySum(basket.calculateCosts());
			basket.clear();
			printer.print(receipt);
		} else {
			checkCodeInDatabase(barcode);
		}
	}

	private void manageInvalidBarcode() {
		System.out.println(INVALID_BAR_CODE);
	}

	private boolean isInvalidBarcode(String barcode) {
		boolean barcodeStatus = false;
		if (barcode == null || barcode.isEmpty()) {
			barcodeStatus = true;
		}
		return barcodeStatus;
	}
}
