package com.sale.point.model;

import java.util.Optional;
import com.sale.point.data.ProductDao;
import com.sale.point.devices.Display;
import com.sale.point.devices.Printer;

public class DefaultCheckout implements Checkout {

	private static final String EXIT = "exit";
	private static final String NOT_FOUND_BARCODE = "Product not found";
	private static final String INVALID_BARCODE = "Invalid bar-code";
	private final Display display;
	private final ReceiptFactory receiptFactory;
	private final Printer printer;
	private final ProductDao productDao;
	private final Basket basket;

	public DefaultCheckout(Display display, ReceiptFactory receiptFactory, Printer printer, ProductDao productDao,
			Basket basket) {
		this.display = display;
		this.printer = printer;
		this.receiptFactory = receiptFactory;
		this.productDao = productDao;
		this.basket = basket;
	}

	@Override
	public void manageProductScan(String barcode) {
		if (isInvalidBarcode(barcode)) {
			display.displayMessage(INVALID_BARCODE);
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
			display.displayMessage(NOT_FOUND_BARCODE);
		}
	}

	private boolean isExitBarcode(String barcode) {
		return barcode.equals(EXIT);
	}

	private void manageValidBarcode(String barcode) {
		if (isExitBarcode(barcode)) {
			String receipt = this.receiptFactory.create(basket.getProducts());
			display.displaySum(basket.calculateCosts());
			basket.clear();
			printer.print(receipt);
		} else {
			checkCodeInDatabase(barcode);
		}
	}

	public boolean isInvalidBarcode(String barcode) {
		boolean barcodeStatus = false;
		if (barcode == null || barcode.isEmpty()) {
			barcodeStatus = true;
		}
		return barcodeStatus;
	}
}
