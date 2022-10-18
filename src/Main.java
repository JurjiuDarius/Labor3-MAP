import product.Product;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		ArrayList<Product> products = new ArrayList<>();
		products.add(new Product(1, "laptop", 100, 200, 200, 300));
		products.add(new Product(3, "phone", 100, 200, 110, 250));
		products.add(new Product(2, "tablet", 1, 20, 200, 300));

		//Initial list, then the sorted one
		System.out.println(products);
		Product.sortByDemand(products);
		System.out.println(products);

		//The optimal quantity for each product
		for (Product product : products) {
			System.out.println(product.getOptimalQuantity());
		}

	}

}