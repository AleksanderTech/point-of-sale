package com.sale.point.outputDevices;

import java.util.List;

import com.sale.point.model.Product;

public class ReceiptCreator implements ReceiptFactory {

	@Override
	public String create(List<Product> products) {
		if (products.size() != 0) {
			StringBuilder builder = new StringBuilder();
			double sum = 0;
			for (Product p : products) {
				builder.append(p.getName()).append(" ").append(p.getCost()).append(" ON RECEIPT").append("-----------")
						.append("\n");
				sum += p.getCost();
			}
			builder.append("TOTAL SUM: ").append(sum).append(" ON RECEIPT").append("\n");

			return builder.toString();
		} else {
			return "";
		}
	}

}
