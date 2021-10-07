
package Command;

import Model.Shop;

public class PrintProfitsCommand implements Command{
	
	private Shop shop;
	
	public PrintProfitsCommand(Shop shop) {
		this.shop = shop;
	}


	@Override
	public void execute() throws Exception {
		shop.printProfits();
		
	}

}
