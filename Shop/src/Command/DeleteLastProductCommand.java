
package Command;


import Model.Shop;


public class DeleteLastProductCommand implements Command{
	
	private Shop shop;
	
	public DeleteLastProductCommand(Shop shop) {
		this.shop = shop;
	}


	@Override
	public void execute() throws Exception {	
		shop.deleteLastProduct();
	}

}
