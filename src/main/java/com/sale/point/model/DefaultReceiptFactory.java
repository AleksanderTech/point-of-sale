package com.sale.point.model;

import java.util.List;

public class DefaultReceiptFactory implements ReceiptFactory {

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
}
