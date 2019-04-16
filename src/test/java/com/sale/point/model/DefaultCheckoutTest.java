package com.sale.point.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.sale.point.data.InMemoryProductDao;
import com.sale.point.data.ProductDao;
import com.sale.point.devices.Display;
import com.sale.point.devices.Printer;

class DefaultCheckoutTest {

	private Display display;
	private ReceiptFactory receiptFactory;
	private Printer printer;
	private ProductDao productDao;
	private Basket basket;

	@BeforeEach
	void setUp() {
		display = Mockito.mock(Display.class);
		receiptFactory = Mockito.mock(ReceiptFactory.class);
		printer = Mockito.mock(Printer.class);
		productDao = new InMemoryProductDao();
		basket = Mockito.mock(Basket.class);
	}

	@Test
	void managesBarcodeOfExistingProduct() {
		String barcode = "12347";
		List<Product> products = new ArrayList<>();
		Product product = productDao.findProductsByBarcode(barcode).get();
		DefaultCheckout checkout = new DefaultCheckout(display, receiptFactory, printer, productDao, basket);

		doAnswer(invocation -> {
			products.add(product);
			return null;
		}).when(basket).addProduct(product);

		checkout.manageProductScan(barcode);

		verify(display).displayProduct(product);
		verify(basket).addProduct(product);
		assertEquals(barcode, products.get(0).getBarcode());
	}

	@Test
	void managesBarcodeOfNonExistingProduct() {
		String barcode = "notExistingInDatabase";
		String message = "Product not found";
		DefaultCheckout checkout = new DefaultCheckout(display, receiptFactory, printer, productDao, basket);
		checkout.manageProductScan(barcode);
		verify(display).displayMessage(message);
	}

	@Test
	void managesEmptyBarcode() {
		managesInvalidBarcode("");
	}

	private void managesInvalidBarcode(String barcode) {
		String message = "Invalid bar-code";
		DefaultCheckout checkout = new DefaultCheckout(display, receiptFactory, printer, productDao, basket);
		checkout.manageProductScan(barcode);
		verify(display).displayMessage(message); // ok
	}

	@Test
	void managesNullBarcode() {
		managesInvalidBarcode(null);
	}

	@Test
	void managesExitBarcodeAfterScannedProducts() {
		String firstBarcode = "12345";
		String secondBarcode = "12346";
		String exit = "exit";

		Product productOne = productDao.findProductsByBarcode(firstBarcode).get();
		Product productTwo = productDao.findProductsByBarcode(secondBarcode).get();
		List<Product> products = Arrays.asList(productOne, productTwo);
		String receipt = create(products);
		double expectedSum = productOne.getCost() + productTwo.getCost();
		DefaultCheckout checkout = new DefaultCheckout(display, receiptFactory, printer, productDao, basket);

		Mockito.when(basket.calculateCosts()).thenReturn(expectedSum);
		Mockito.when(receiptFactory.create(products)).thenReturn(receipt);
		Mockito.when(basket.getProducts()).thenReturn(products);
		checkout.manageProductScan(firstBarcode);
		checkout.manageProductScan(secondBarcode);
		checkout.manageProductScan(exit);

		verify(display).displayProduct(productOne);
		verify(display).displayProduct(productTwo);
		verify(display).displaySum(expectedSum);
		verify(printer).print(receipt);
	}

	public String create(List<Product> products) {
		StringBuilder builder = new StringBuilder();
		double sum = 0;
		for (Product p : products) {
			builder.append(p.getName()).append(" ").append(p.getCost()).append(" ON RECEIPT").append("-----------")
					.append("\n");
			sum += p.getCost();
		}
		builder.append("TOTAL SUM: ").append(String.format("%.2f", sum)).append(" ON RECEIPT");
		return builder.toString();
	}

	@Test
	void managesOnlyExitBarcode() {
		String exit = "exit";
		double zero = 0;
		List<Product> products = new ArrayList<>();
		String receipt=create(products);
		DefaultCheckout checkout = new DefaultCheckout(display, receiptFactory, printer, productDao, basket);

		Mockito.when(basket.calculateCosts()).thenReturn(zero);
		Mockito.when(receiptFactory.create(products)).thenReturn(receipt);
		Mockito.when(basket.getProducts()).thenReturn(products);
		checkout.manageProductScan(exit);

		verify(display).displaySum(zero);
		verify(printer).print(receipt);
	}
}
