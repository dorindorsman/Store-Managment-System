/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Command;

import Model.Shop;

public class DeleteAllProductsCommand  implements Command{
	
	private Shop shop;
	
	public DeleteAllProductsCommand(Shop shop) {
		this.shop = shop;
	}


	@Override
	public void execute() throws Exception {
		shop.deleteAllProducts();
	}

}
