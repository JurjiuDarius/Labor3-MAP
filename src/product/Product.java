package product;

import java.util.ArrayList;

public class Product {

	private Integer id;
	private Integer firstYearSold;
	private Integer secondYearSold;
	private Integer firstYearProduced;
	private Integer secondYearProduced;
	private String name;
	private Trend trend;

	public Product(Integer id, String name, Integer sold1, Integer sold2, Integer produced1, Integer produced2) {
		this.id = id;
		this.name = name;
		this.firstYearSold = sold1;
		this.secondYearSold = sold2;
		this.firstYearProduced = produced1;
		this.secondYearProduced = produced2;
	}

	public static int sortByDemandCallback(Product product1, Product product2) {
		return (int) (product1.getFirstYearDemand() + product1.getSecondYearDemand()) - (int) (product2.getFirstYearDemand() + product2.getSecondYearDemand());
	}

	public static void sortByDemand(ArrayList<Product> products) {
		products.sort(Product::sortByDemandCallback);
	}

	public Double getFirstYearDemand() {
		return (double) firstYearSold / (double) firstYearProduced;
	}

	public Double getSecondYearDemand() {
		return (double) secondYearSold / (double) secondYearProduced;
	}

	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}

	public void setTrend() {
		if (getFirstYearDemand() < getSecondYearDemand()) {
			this.trend = Trend.ASCENDING;
		}
		if (getFirstYearDemand() > getSecondYearDemand()) {
			this.trend = Trend.DESCENDING;
		}
		if (getFirstYearDemand() > getSecondYearDemand() - 0.01 && getFirstYearDemand() < getSecondYearDemand() + 0.01) {
			this.trend = Trend.STAGNATING;
		}
	}

	public Integer getOptimalQuantity() {
		this.setTrend();
		switch (this.trend) {
			case ASCENDING -> {
				return Integer.valueOf((int) (this.firstYearProduced * 1.5));
			}
			case DESCENDING -> {
				return Integer.valueOf((int) (this.firstYearProduced * 0.9));
			}
			case STAGNATING -> {
				return Integer.valueOf((int) (this.firstYearProduced * 1.1));
			}
			default -> {
				return this.firstYearProduced;
			}
		}
	}

}
