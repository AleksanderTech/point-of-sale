package com.sale.point.model;

import java.util.List;

public class ReceiptFactoryFake implements ReceiptFactory {

	@Override
	public String create(List<Product> products) {
			StringBuilder builder = new StringBuilder();
			double sum = 0;
			for (Product p : products) {
				builder.append(p.getName()).append(" ").append(p.getCost()).append(" ON RECEIPT").append("-----------")
						.append("\n");
				sum += p.getCost();
			}
			builder.append("TOTAL SUM: ").append(sum).append(" ON RECEIPT").append("\n");

			return builder.toString();
		}

}
