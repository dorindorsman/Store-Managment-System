/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Model;

public class Product {
	
	private String name;
	private int priceForShop;
	private int priceForClient;
	private Client client;
	
	public Product(String name, int priceForShop, int priceForClient, Client client) {
		this.name = name;
		this.priceForShop = priceForShop;
		this.priceForClient = priceForClient;
		this.client = client;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPriceForShop() {
		return priceForShop;
	}

	public void setPriceForShop(int priceForShop) {
		this.priceForShop = priceForShop;
	}

	public int getPriceForClient() {
		return priceForClient;
	}

	public void setPriceForClient(int priceForClient) {
		this.priceForClient = priceForClient;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public boolean equals(Product p) {
		return this.name.equals(p.getName()) && this.priceForClient == p.getPriceForClient() &&
				this.priceForShop == p.getPriceForShop() && this.client.getSaleUpdates() == p.getClient().getSaleUpdates()
				&& this.client.getName().equals(p.getClient().getName()) && this.client.getPhone().equals(p.getClient().getPhone());
	}
	
	public int profitOfShopForProduct() {
		int profitPerProduct;
		profitPerProduct = priceForClient - priceForShop;
		if (profitPerProduct < 0)
			profitPerProduct = 0;
		return profitPerProduct;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", priceForShop=" + priceForShop + ", priceForClient=" + priceForClient
				+ ", client=" + client + "]";
	}

}
