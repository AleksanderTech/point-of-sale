package com.sale.point.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import com.sale.point.database.ProductDao;
import com.sale.point.database.ProductDaoFake;
import com.sale.point.inputDevices.BarcodeScanner;
import com.sale.point.outputDevices.Display;
import com.sale.point.outputDevices.Printer;

class CheckoutImplTest {

	private Display display;
	private ReceiptFactory receiptFactory;
	private Printer printer;
	private BarcodeScanner barcodeScanner;
	private ProductDao productDao;
	private Basket basket;

	@BeforeEach
	void setUp() {
		display = Mockito.mock(Display.class);
		receiptFactory = Mockito.mock(ReceiptFactory.class);
		printer = Mockito.mock(Printer.class);
		barcodeScanner = Mockito.mock(BarcodeScanner.class);
		productDao = new ProductDaoFake(); // -------------------
		basket = Mockito.mock(Basket.class);
	}

	@Test
	@DisplayName("management valid barcode")
	void testCheckCodeInDatabaseCaseOne() {
		// given
		final String barcodeOfProductOne = "12347";
		final List<Product> products = new ArrayList<>();
		Product productOne = productDao.findProductsByBarcode(barcodeOfProductOne).get();
		CheckoutImpl checkoutExample = new CheckoutImpl(display, receiptFactory, printer, productDao, basket);
		doAnswer((Answer<?>) invocation -> {
			products.add(productOne);
			return null;
		}).when(basket).addProduct(productOne);
		// when
		checkoutExample.manageProductScan(barcodeOfProductOne);
		// then
		verify(display).displayProduct(productOne);
		verify(basket).addProduct(productOne);
		assertEquals(barcodeOfProductOne, products.get(0).getBarcode());
	}

	@Test
	@DisplayName("management barcode of product that doesn't exist in database")
	void testCheckCodeInDatabaseCasetwo() {
		// given
		final String barcodeOfNotExistingProduct = "notExistingInDatabase";
		final String productNotFoundMessage = "Product not found";
		CheckoutImpl checkoutExample = new CheckoutImpl(display, receiptFactory, printer, productDao, basket);
		// when
		checkoutExample.manageProductScan(barcodeOfNotExistingProduct);
		// then
		verify(display).dispayMessage(productNotFoundMessage);
	}

	@Test
	@DisplayName("management invalid barcode with empty input")
	void testManageInvalidBarcodeCaseOne() {
		// given
		final String emptyInvalidBarcode = "";
		final String invalidMessage = "Invalid bar-code";
		CheckoutImpl checkoutExample = new CheckoutImpl(display, receiptFactory, printer, productDao, basket);
		// when
		checkoutExample.manageProductScan(emptyInvalidBarcode);
		// then
		verify(display).dispayMessage(invalidMessage);
	}

	@Test
	@DisplayName("management invalid barcode with null input")
	void testManageInvalidBarcodeCaseTwo() {
		// given
		final String nullInvalidBarcode = null;
		final String invalidMessage = "Invalid bar-code";
		CheckoutImpl checkoutExample = new CheckoutImpl(display, receiptFactory, printer, productDao, basket);
		// when
		checkoutExample.manageProductScan(nullInvalidBarcode);
		// then
		verify(display).dispayMessage(invalidMessage);
	}

	@Test
	@DisplayName("management exit barcode preceded by erlier scanned products")
	void testManageExitBarcodeCaseOne() {
		// given
		final String barcodeOfExistingProductOne = "12345";
		final String barcodeOfExistingProductTwo = "12346";
		final String exitBarcode = "exit";
		Product productOne = productDao.findProductsByBarcode(barcodeOfExistingProductOne).get();
		Product productTwo = productDao.findProductsByBarcode(barcodeOfExistingProductTwo).get();
		CheckoutImpl checkoutImpl = new CheckoutImpl(display, receiptFactory, printer, productDao, basket);
		// when
		Mockito.when(basket.calculateCosts()).thenReturn(productOne.getCost() + productTwo.getCost());
		checkoutImpl.manageProductScan(barcodeOfExistingProductOne);
		checkoutImpl.manageProductScan(barcodeOfExistingProductTwo);
		checkoutImpl.manageProductScan(exitBarcode);
		// then
		verify(display).displayProduct(productOne);
		verify(display).displayProduct(productTwo);
		verify(display).displaySum(productOne.getCost() + productTwo.getCost());
		verify(printer).print(receiptFactory.create(Arrays.asList(productOne, productTwo)));
	}

	@Test
	@DisplayName("management exit barcode when not preceded by erlier scanned products")
	void testManageExitBarcodeCaseTwo() {
		// given
		final String exitBarcode = "exit";
		CheckoutImpl checkoutImpl = new CheckoutImpl(display, receiptFactory, printer, productDao, basket);
		// when
		Mockito.when(basket.calculateCosts()).thenReturn(0.0);
		checkoutImpl.manageProductScan(exitBarcode);
		// then
		verify(display).displaySum(0.0);
		verify(printer).print(receiptFactory.create(Arrays.asList()));
	}
}
