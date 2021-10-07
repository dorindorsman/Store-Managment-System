
package Command;

import Model.Shop;

public class PrintAllProductsCommand implements Command{
	
	private Shop shop;
	
	public PrintAllProductsCommand(Shop shop) {
		this.shop = shop;
	}

	@Override
	public void execute() throws Exception {
		shop.printAllProducts();
	}

}
