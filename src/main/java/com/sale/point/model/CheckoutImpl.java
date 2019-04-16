package com.sale.point.model;

import java.util.Optional;

import com.sale.point.database.ProductDao;
import com.sale.point.outputDevices.Display;
import com.sale.point.outputDevices.Printer;

public class CheckoutImpl implements Checkout {

	private static final String EXIT_CODE = "exit";
	private static final String PRODUCT_NOT_FOUND = "Product not found";
	private static final String INVALID_BAR_CODE = "Invalid bar-code";
	private final Display display;
	private final ReceiptFactory receipt;
	private final Printer printer;
	private final ProductDao productDao;
	private final Basket basket;

	public CheckoutImpl(Display display, ReceiptFactory receipt, Printer printer, ProductDao productDao,
			Basket basket) {
		this.display = display;
		this.printer = printer;
		this.receipt = receipt;
		this.productDao = productDao;
		this.basket = basket;
	}

	@Override
	public void manageProductScan(String barcode) {
		if (isInvalidBarcode(barcode)) {
			display.dispayMessage(INVALID_BAR_CODE);
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
			display.dispayMessage(PRODUCT_NOT_FOUND);
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

	public boolean isInvalidBarcode(String barcode) {
		boolean barcodeStatus = false;
		if (barcode == null || barcode.isEmpty()) {
			barcodeStatus = true;
		}
		return barcodeStatus;
	}
}
