/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Command;

import Model.Product;
import Model.Shop;

public class AddProductCommand  implements Command {
	private Shop shop;
	private String idOfProduct;
	private Product product;
	
	public AddProductCommand(Shop shop) {
		this.shop = shop;
	}

	public String getIdOfProduct() {
		return idOfProduct;
	}


	public void setIdOfProduct(String idOfProduct) {
		this.idOfProduct = idOfProduct;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	@Override
	public void execute() throws Exception {
		shop.addProduct( idOfProduct, product);
	}

}
