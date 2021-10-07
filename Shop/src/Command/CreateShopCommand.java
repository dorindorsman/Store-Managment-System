/*
 * Dorin Dorsman-315827014
 * Yehiel Butael-315016774
 */
package Command;

import java.io.IOException;

import Model.FileIterator.savingMethod;
import Model.Shop;

public class CreateShopCommand implements Command{

	private Shop shop;
	private savingMethod save;
	
	
	
	public CreateShopCommand(Shop shop) {
		this.shop = shop;
	}


	@Override
	public void execute() {
		try {
			shop.ChooseDatabaseShop(save);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setSavingMethod(savingMethod save) {
		this.save = save;
	}

}
