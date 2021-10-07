/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Memento;
import Model.Client;
import Model.Product;

public class ProductMemento {
	private String name;
	private int priceForShop;
	private int priceForClient;
	private Client client;
	private String idOfProduct;
	
	
	public ProductMemento(Product p ,String idOfProduct) {
		this.name = p.getName();
		this.priceForShop = p.getPriceForShop();
		this.priceForClient = p.getPriceForClient();
		this.client = p.getClient();
		this.idOfProduct = idOfProduct;
	}

	public String getName() {
		return name;
	}
	public String getIdOfProduct() {
		return this.idOfProduct;
	}

	public int getStorePrice() {
		return priceForShop;
	}

	public int getCostumerPrice() {
		return priceForClient;
	}

	public Client getCustomer() {
		return client;
	}
	

}
