/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Command;

import Model.Shop;

public class FindProductByIDCommand implements Command{
	
	private Shop shop;
	private String serialNumber;
	
	public FindProductByIDCommand(Shop shop) {
		this.shop = shop;
	}
	
	

	public String getSerialNumber() {
		return serialNumber;
	}



	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}



	@Override
	public void execute() throws Exception {
		shop.findProductByID(serialNumber);
	}

}
