/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Command;

import Model.Shop;

public class DeleteProductByIDCommand implements Command{
	
	private Shop shop;
	private String serialNumberToDel;
	
	public DeleteProductByIDCommand(Shop shop) {
		this.shop = shop;
	}
	

	public String getSerialNumberToDel() {
		return serialNumberToDel;
	}



	public void setSerialNumberToDel(String serialNumberToDel) {
		this.serialNumberToDel = serialNumberToDel;
	}


	@Override
	public void execute() throws Exception {
		shop.deleteProductByID(serialNumberToDel);
	}
}
